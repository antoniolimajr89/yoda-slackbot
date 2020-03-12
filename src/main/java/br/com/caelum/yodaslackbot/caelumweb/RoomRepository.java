package br.com.caelum.yodaslackbot.caelumweb;

import br.com.caelum.yodaslackbot.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    @Override
    List<Room> findAll();
    Optional<Room> findByName(String name);
}
