package net.java.forest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.java.forest.model.Author;
import net.java.forest.repository.AuthorRepository;
import reactor.core.publisher.Mono;
@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorrepository;
    public Mono<Author> addAuthor(Author author)
    {
        return authorrepository.save(author);
    }

}
