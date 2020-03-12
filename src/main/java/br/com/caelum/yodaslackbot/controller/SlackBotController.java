package br.com.caelum.yodaslackbot.controller;

import br.com.caelum.yodaslackbot.caelumweb.RoomRepository;
import br.com.caelum.yodaslackbot.model.NewRoomRequest;
import br.com.caelum.yodaslackbot.model.Room;
import br.com.caelum.yodaslackbot.model.SlackResponseDto;
import br.com.caelum.yodaslackbot.service.SlackBotService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/usandosala1")
    public SlackResponseDto usingRoom(NewRoomRequest newRoomRequest) {
        slackBotService.updateUsingRoom(newRoomRequest);

        return new SlackResponseDto(slackBotService.buildMessage(),
                newRoomRequest.getChannel_name());
    }

    @PostMapping("/deixandosala")
    public SlackResponseDto leavingRoom(NewRoomRequest newRoomRequest) {
        Optional<Room> possibleRoom = roomRepository.findByName(newRoomRequest.getText());

        if (possibleRoom.isPresent()) {
            possibleRoom.get().leavingRoom();
            roomRepository.save(possibleRoom.get());
            return new SlackResponseDto(slackBotService.buildMessage(),
                    newRoomRequest.getChannel_name());
        }

        throw new RuntimeException();
    }
}
