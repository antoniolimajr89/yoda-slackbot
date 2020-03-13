package br.com.caelum.yodaslackbot.slackbot;

import br.com.caelum.yodaslackbot.caelumweb.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/slack")
public class SlackBotController {

    @Autowired
    private SlackBotService slackBotService;

    @Autowired
    private RoomRepository roomRepository;

    @PostMapping("/usandosala")
    public String usingRoom(NewRoomRequest newRoomRequest) throws URISyntaxException {
        slackBotService.updateUsingRoom(newRoomRequest);

        RestTemplate restTemplate = new RestTemplate();
        SlackResponseDto slackResponseDto = new SlackResponseDto(slackBotService.buildMessage(),
                newRoomRequest.getChannel_name());
        ResponseEntity<String> stringResponseEntity = restTemplate
                .postForEntity(new URI(newRoomRequest.getResponse_url()), slackResponseDto, String.class);

        return stringResponseEntity.getBody();
    }

    @PostMapping("/deixandosala")
    public String leavingRoom(NewRoomRequest newRoomRequest) throws URISyntaxException {
        Optional<Room> possibleRoom = roomRepository.findByName(newRoomRequest.getText());

        if (possibleRoom.isPresent()) {
            possibleRoom.get().leavingRoom();
            roomRepository.save(possibleRoom.get());

            RestTemplate restTemplate = new RestTemplate();
            SlackResponseDto slackResponseDto = new SlackResponseDto(slackBotService.buildMessage(),
                    newRoomRequest.getChannel_name());
            ResponseEntity<String> stringResponseEntity = restTemplate
                    .postForEntity(new URI(newRoomRequest.getResponse_url()), slackResponseDto, String.class);

            return stringResponseEntity.getBody();
        }

        throw new RuntimeException();
    }
}
