package br.com.caelum.yodaslackbot.caelumweb;

import br.com.caelum.yodaslackbot.slackbot.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Collectors;

@RestController
public class ImportRoomsController {

    @Autowired
    private RoomRepository roomRepository;

    @Value("${caelumweb.import.rooms.url}")
    private String CAELUMWEB_ENDPOINT;

    @GetMapping("/magic/anlsdjkdahsljkasdhaegityujnadlksjhg")
    @Scheduled(cron = "0 15 7 * * MON-SAT")
    public void importRooms() {
        roomRepository.deleteAll();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ImportedRoomsDto[]> importedRooms = restTemplate.getForEntity(CAELUMWEB_ENDPOINT,
                ImportedRoomsDto[].class);

        TreeSet<Room> rooms = Arrays.stream(importedRooms.getBody())
                .map(roomDto -> roomDto.toModel()).collect(Collectors.toCollection(() -> new TreeSet<>()));

        this.roomRepository.saveAll(rooms);
    }
}
