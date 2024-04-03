package net.java.forest.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import net.java.forest.model.Book;

public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
    
}