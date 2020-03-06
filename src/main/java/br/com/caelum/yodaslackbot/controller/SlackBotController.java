package br.com.caelum.yodaslackbot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("/slack")
public class SlackBotController {

    @PostMapping("/usandosala1")
    public void usingRoom(NewRoomRequest newRoomRequest) {
        System.out.println(newRoomRequest);
    }

    @GetMapping
    public void test() {
        LocalTime now = LocalTime.now();
        LocalTime elevenPM = LocalTime.of(23, 30, 00);
        LocalTime sevenAM = LocalTime.of(07, 00, 00);
        if (now.isAfter(elevenPM) || now.isBefore(sevenAM)) {
            System.out.println("To dormindo!");
        }
    }
}
