package br.com.caelum.yodaslackbot.domain;

public class Slack {

    private String token;
    private String text;
    private String channel;
    private String username;
    private String icon_emoji;

    public String getUsername() {
        return username;
    }

    public String getIcon_emoji() {
        return icon_emoji;
    }

    public String getToken() {
        return token;
    }

    public String getText() {
        return text;
    }

    public String getChannel() {
        return channel;
    }

}