package br.com.caelum.yodaslackbot.slackbot;

import br.com.caelum.yodaslackbot.caelumweb.RoomRepository;
import br.com.caelum.yodaslackbot.shared.exception.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/slack")
public class SlackBotController {

    @Autowired
    private SlackBotService slackBotService;

    @Autowired
    private RoomRepository roomRepository;

    @PostMapping("/usandosala")
    public String usingRoom(NewRoomRequest newRoomRequest) {
        slackBotService.updateUsingRoom(newRoomRequest);

        ResponseEntity<String> response = slackBotService.
                sendMessage(newRoomRequest);

        return response.getBody();
    }

    @PostMapping("/deixandosala")
    public String leavingRoom(NewRoomRequest newRoomRequest) {
        Optional<Room> possibleRoom = roomRepository.findByName(newRoomRequest.getText());

        if (possibleRoom.isPresent()) {
            possibleRoom.get().leavingRoom();
            roomRepository.save(possibleRoom.get());

            ResponseEntity<String> response = slackBotService
                    .sendMessage(newRoomRequest);

            return response.getBody();
        }

        throw new RoomNotFoundException();
    }

    @PostMapping
    public void sendMessage(@RequestBody SlackContent slackContent) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> slackContentHttpEntity = new HttpEntity(slackContent, headers);

        try {
            ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(new URI("https://api.caelum.com.br/slack/send"),
                    slackContentHttpEntity, String.class);

            if (stringResponseEntity.getStatusCode().is2xxSuccessful()) {
                System.out.println("Deu certo");
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}