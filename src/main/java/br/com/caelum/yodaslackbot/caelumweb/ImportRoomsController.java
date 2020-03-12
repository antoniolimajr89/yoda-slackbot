package br.com.caelum.yodaslackbot.caelumweb;

import br.com.caelum.yodaslackbot.model.Room;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class ImportRoomsController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/magic/anlsdjkdahsljkasdhaegityujnadlksjhg")
    @Scheduled(cron = "0 15 7 * * MON-SAT")
    public void importRooms() {
        roomRepository.deleteAll();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("turmas.json");
        try {
            List<ImportedRoomsDto> importedRooms = objectMapper.readValue(file, new TypeReference<>() {
            });
            Set<Room> rooms = importedRooms.stream().map(importedRoomsDto -> importedRoomsDto.toModel())
                    .collect(Collectors.toSet());

            this.roomRepository.saveAll(rooms);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
