package br.com.caelum.yodaslackbot.controller;

public class NewRoomRequest {
    
    private String token;
    private String channel_name;
    private String user_name;
    private String text;

    public void setToken(String token) {
        this.token = token;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "NewRoomRequest{" +
                "token='" + token + '\'' +
                ", channel_name='" + channel_name + '\'' +
                ", user_name='" + user_name + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
