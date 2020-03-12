package br.com.caelum.yodaslackbot.slackbot;

public class SlackResponseDto {

    private String text;
    private String channel;

    public SlackResponseDto(String text, String channel) {
        this.text = text;
        this.channel = channel;
    }

    public String getText() {
        return text;
    }

    public String getChannel() {
        return channel;
    }
}
