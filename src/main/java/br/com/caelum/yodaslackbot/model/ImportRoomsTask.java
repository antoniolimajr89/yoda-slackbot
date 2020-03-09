package br.com.caelum.yodaslackbot.model;

import br.com.caelum.yodaslackbot.repository.RoomRepository;
import br.com.caelum.yodaslackbot.task.ImportedRoomsDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ImportRoomsTask {

    @Autowired
    private RoomRepository roomRepository;

    //    @Scheduled(cron = "0 15 7 * * MON-SAT")
    @Scheduled(cron = "*/10 * * * * *")
    public void importRooms() {
        roomRepository.deleteAll();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("turmas.json");
        try {
            List<ImportedRoomsDto> importedRooms = objectMapper.readValue(file, new TypeReference<>(){});
            Set<Room> rooms = importedRooms.stream().map(importedRoomsDto -> importedRoomsDto.toModel())
                    .collect(Collectors.toSet());

            this.roomRepository.saveAll(rooms);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
