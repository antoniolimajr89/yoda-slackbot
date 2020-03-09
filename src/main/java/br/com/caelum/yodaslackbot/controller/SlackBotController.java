package br.com.caelum.yodaslackbot.controller;

import br.com.caelum.yodaslackbot.model.Room;
import br.com.caelum.yodaslackbot.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/slack")
public class SlackBotController {

    @Autowired
    private RoomRepository roomRepository;

    @PostMapping("/usandosala1")
    public void usingRoom(NewRoomRequest newRoomRequest) {

        if (!newRoomRequest.getText().isEmpty()) {
            String[] parameters = newRoomRequest.getText().split(" ");
            Optional<Room> possibleRoom = roomRepository.findByName(parameters[0]);

            if (possibleRoom.isPresent()) {
                Room room = possibleRoom.get();
                if (parameters.length > 1) {
                    room.usingRoom(parameters[1]);
                } else {
                    room.usingRoom(newRoomRequest.getUser_name());
                }
                roomRepository.save(room);
            }

        }
        System.out.println(newRoomRequest);
    }

    @PostMapping("/deixandosala1")
    public void leavingRoom(NewRoomRequest newRoomRequest) {
        System.out.println(newRoomRequest);
    }
}
