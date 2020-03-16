package br.com.caelum.yodaslackbot.slackbot;

import br.com.caelum.yodaslackbot.caelumweb.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
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

    public ResponseEntity<String> sendMessage(NewRoomRequest newRoomRequest) {
        RestTemplate restTemplate = new RestTemplate();
        SlackResponseDto slackResponseDto = new SlackResponseDto(this.buildMessage(),
                newRoomRequest.getChannel_name());
        try {
            return restTemplate.postForEntity(new URI(newRoomRequest.getResponse_url()),
                    slackResponseDto, String.class);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildMessage() {
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
