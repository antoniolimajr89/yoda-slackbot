package br.com.caelum.yodaslackbot.slackbot;

import br.com.caelum.yodaslackbot.caelumweb.RoomRepository;
import br.com.caelum.yodaslackbot.shared.exception.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
