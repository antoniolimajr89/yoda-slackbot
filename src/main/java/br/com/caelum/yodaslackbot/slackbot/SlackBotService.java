package br.com.caelum.yodaslackbot.slackbot;

import br.com.caelum.yodaslackbot.caelumweb.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.SortedSet;

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

    public String buildMessage() {
        SortedSet<Room> rooms = this.roomRepository.findAll();
        StringBuilder builder = new StringBuilder();
        builder.append("Salas Livres estão: ```");
        for (Room room : rooms) {
            if (room.getName().length() == 2) {
                builder.append(room.getName() + "  | ");
            } else {
                builder.append(room.getName() + " | ");
            }

            if (room.hasCourse()) {
                builder.append(room.getUsername() + " (" + room.getCourse() + "|" + room.getPeriod() + ")");
            } else {
                builder.append(room.getUsername());
            }

            builder.append("\n");
        }
        builder.append("```\n");
        return builder.toString();
    }
}
