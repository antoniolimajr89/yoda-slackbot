package br.com.caelum.yodaslackbot.service;

import br.com.caelum.yodaslackbot.controller.NewRoomRequest;
import br.com.caelum.yodaslackbot.model.Room;
import br.com.caelum.yodaslackbot.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SlackBotService {

    @Autowired
    private RoomRepository roomRepository;

    public void updateUsingRoom(NewRoomRequest newRoomRequest) {
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
    }


    public String buildResponse() {
        List<Room> rooms = (List<Room>) this.roomRepository.findAll();
        StringBuilder builder = new StringBuilder();
        builder.append("Salas Livres estao: ```");
        for (Room room : rooms) {
            builder.append(room.getName() + " | ");

            if (room.hasCourse()) {
                builder.append(room.getUserText() + " (" + room.getCourse() + "|" + room.getPeriod() + ")");
            } else {
                builder.append(room.getUserText());
            }

            builder.append("\n");

        }
        builder.append("```\n");
        return builder.toString();
    }
}
