package br.com.caelum.yodaslackbot.domain;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WakeUpTask {

    @Scheduled(cron = "0 0/15 7-23 * * MON-SAT")
    public void wakeUp() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForEntity("https://caelum-yoda-slackbot.herokuapp.com/wakeup",
                String.class, Object.class);
    }
}
