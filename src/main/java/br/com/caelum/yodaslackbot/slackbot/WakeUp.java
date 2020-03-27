package br.com.caelum.yodaslackbot.slackbot;

public class WakeUp {

    private String text;
    private String channel;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "WakeUp{" +
                "text='" + text + '\'' +
                ", channel='" + channel + '\'' +
                '}';
    }
}
