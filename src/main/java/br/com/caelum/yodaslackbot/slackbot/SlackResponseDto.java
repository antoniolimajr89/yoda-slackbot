package br.com.caelum.yodaslackbot.slackbot;

public class SlackResponseDto {

    private String text;
    private String channel;
    private String response_type;

    public SlackResponseDto(String text, String channel) {
        this.text = text;
        this.channel = channel;
        this.response_type = "in_channel";
    }

    public String getText() {
        return text;
    }

    public String getChannel() {
        return channel;
    }

    public String getResponse_type() {
        return response_type;
    }
}
