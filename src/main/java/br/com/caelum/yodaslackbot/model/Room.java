package br.com.caelum.yodaslackbot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String period;
    private String course;
    private String userText;

    @Deprecated
    public Room() {
    }

    public Room(String name, String period, String course) {
        this.name = name;
        this.period = period;
        this.course = course;
    }

    public void usingRoom(String userText) {
        this.userText = userText;
    }
}
