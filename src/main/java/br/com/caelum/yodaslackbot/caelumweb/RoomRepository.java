package br.com.caelum.yodaslackbot.caelumweb;

import br.com.caelum.yodaslackbot.slackbot.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.SortedSet;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    @Override
    SortedSet<Room> findAll();
    Optional<Room> findByName(String name);
}
