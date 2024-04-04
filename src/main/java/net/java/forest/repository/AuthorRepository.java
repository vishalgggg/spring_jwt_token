package net.java.forest.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import net.java.forest.model.Author;
@Repository
public interface AuthorRepository extends ReactiveCrudRepository<Author, Long>
{
    
}