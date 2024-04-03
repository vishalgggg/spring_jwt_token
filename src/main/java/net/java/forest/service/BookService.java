package net.java.forest.service;

import org.springframework.beans.factory.annotation.Autowired;

import net.java.forest.model.Book;
import net.java.forest.repository.BookRepository;
import reactor.core.publisher.Mono;

public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public Mono<Book> addBook(Book book)
    {
        return bookRepository.save(book);
    }
}
