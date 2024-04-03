package net.java.forest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.java.forest.model.Author;
import net.java.forest.model.Book;
import net.java.forest.service.BookService;
import reactor.core.publisher.Mono;

@Slf4j

public class BookController {

    @Autowired
    private BookService bookService;

    @MutationMapping("addBook")
    Mono<Book> addBook(@Argument BookInput bookInput) {
        Book b = new Book();
        b.setTitle(bookInput.getTitle());
        b.setIsbn(bookInput.getIsbn());
        b.setPageCount(bookInput.getPageCount());
        b.setAuthor(bookInput.getAuthor());
        log.info("Add player using 'addPlayer' mutation");
        return bookService.addBook(b);
    }

}
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class BookInput {
    private String title;

    private String isbn;

    private int pageCount;

    private Author author;

}
