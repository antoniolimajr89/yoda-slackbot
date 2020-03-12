package br.com.caelum.yodaslackbot.slackbot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String period;
    private String course;
    private String username = "";

    @Deprecated
    public Room() {
    }

    public Room(String name, String period, String course) {
        this.name = name;
        this.period = period;
        this.course = course;
    }

    public void usingRoom(String userText) {
        this.username = userText;
    }

    public void leavingRoom() { this.username = ""; }

    public boolean hasCourse() {
        return !this.course.isEmpty();
    }

    public String getName() {
        return name;
    }

    public String getPeriod() {
        return period;
    }

    public String getCourse() {
        return course;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int compareTo(Object o) {
        Room anotherRoom = (Room) o;
        return Integer.parseInt(this.name) - Integer.parseInt(anotherRoom.getName());
    }
}
