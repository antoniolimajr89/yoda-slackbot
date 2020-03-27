package br.com.caelum.yodaslackbot.slackbot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wakeup")
public class WakeUpController {

    @PostMapping
    public void wakeUp(@RequestBody WakeUp wakeUp) {
        System.out.println(wakeUp);
        System.out.println("Oie");
    }
}
