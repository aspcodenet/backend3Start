package se.systementor.backend3start.model;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QueueRepository extends CrudRepository<Queue, UUID> {

}
