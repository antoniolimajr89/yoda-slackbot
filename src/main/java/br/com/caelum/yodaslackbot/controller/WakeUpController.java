package br.com.caelum.yodaslackbot.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/wakeup")
public class WakeUpController {

    @GetMapping
    public void wakeUp() {
        System.out.println("Oie");
    }
}
