package br.com.caelum.yodaslackbot.repository;

import br.com.caelum.yodaslackbot.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    Optional<Room> findByName(String name);
}
