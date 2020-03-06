package br.com.caelum.yodaslackbot.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WakeUpTask {

    @Scheduled(cron = "0 0/1 7-23 * * MON-SAT")
    public void wakeUp() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForEntity("http://localhost:8080/wakeup", String.class, Object.class);
    }
}
