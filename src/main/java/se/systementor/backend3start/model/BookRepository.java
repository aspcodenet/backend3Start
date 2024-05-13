package se.systementor.backend3start.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    Optional<Book> findByExternalSystemId(String id);
}
