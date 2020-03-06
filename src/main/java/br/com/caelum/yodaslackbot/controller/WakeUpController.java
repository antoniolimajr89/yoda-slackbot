package br.com.caelum.yodaslackbot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wakeup")
public class WakeUpController {

    @GetMapping
    public void wakeUp() {
        System.out.println("Oie");
    }
}
