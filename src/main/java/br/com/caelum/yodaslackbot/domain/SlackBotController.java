package br.com.caelum.yodaslackbot.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;


@RestController
@RequestMapping("/slack")
public class SlackBotController {

    @Value("${slack.uri}")
    private String SLACK_URI;
    private Logger LOG = LoggerFactory.getLogger(SlackBotController.class);

    @PostMapping
    public void sendMessage(@RequestBody Slack slack) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = this.prepareHeader();
        HttpEntity<String> slackContentHttpEntity = new HttpEntity(slack, headers);

        try {
            ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(new URI(SLACK_URI),
                    slackContentHttpEntity, String.class);

            if (stringResponseEntity.getStatusCode().is2xxSuccessful()) {
                LOG.info("Enviado! \\o/");
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException("Não foi possivel enviar ao slack! :(", e);
        }
    }

    private HttpHeaders prepareHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        return headers;
    }
}