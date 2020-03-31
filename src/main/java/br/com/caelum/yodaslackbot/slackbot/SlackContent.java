package br.com.caelum.yodaslackbot.slackbot;

public class SlackContent {

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

    @Override
    public String toString() {
        return "WakeUp{" +
                "token='" + token + '\'' +
                ", text='" + text + '\'' +
                ", channel='" + channel + '\'' +
                ", bot_name='" + username + '\'' +
                ", bot_icon='" + icon_emoji + '\'' +
                '}';
    }
}
