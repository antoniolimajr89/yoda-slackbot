package br.com.caelum.yodaslackbot.controller;

import br.com.caelum.yodaslackbot.service.SlackBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slack")
public class SlackBotController {

    @Autowired
    private SlackBotService slackBotService;

    @PostMapping("/usandosala1")
    public String usingRoom(NewRoomRequest newRoomRequest) {
        slackBotService.updateUsingRoom(newRoomRequest);

        System.out.println(slackBotService.buildResponse());
        return "";
    }

    @PostMapping("/deixandosala1")
    public void leavingRoom(NewRoomRequest newRoomRequest) {
        System.out.println(newRoomRequest);
    }
}
