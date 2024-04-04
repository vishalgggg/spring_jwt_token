package net.java.forest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import net.java.forest.model.Author;
import net.java.forest.service.AuthorService;
import reactor.core.publisher.Mono;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
@Slf4j

public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @MutationMapping("addAuthor")
    Mono<Author> addAuthor(@Argument AuthorInput authorInput)
    {
        log.info("Add author using 'addAuthor' mutation");
        Author author = new Author();
        author.setFirstName(authorInput.getFirstName());
        author.setLastName(authorInput.getLastName());
        return authorService.addAuthor(author);
    }

}
@Getter
@Setter
class AuthorInput{
    private String firstName;

    private String lastName;
}
