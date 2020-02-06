package com.example.demo;


import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class HelloController {

    private static Logger logger = Logger.getLogger(HelloController.class.getName());

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/testBookCreateReadAndReturn")
    public @ResponseBody
    Book testBookCreateReadAndReturn() {
        Date date = new Date();
        Book demoBook = new Book("demoBook " + date.toString(), "cezar", 2020);
        Book savedBook = this.bookRepository.save(demoBook);


        logger.log(Level.INFO, "Hello from logger: DemoBook id = " + demoBook.getId());

        Iterable<Book> books = this.bookRepository.findAll();
        int i = 0;
        logger.log(Level.INFO, "All Books");
        for (Book book : books) {
            logger.log(Level.INFO, "" + ++i + book);
        }

        logger.log(Level.INFO, "Found books");
        List<Book> foundBooks = this.bookRepository.findByAuthor("cezar");
        foundBooks.stream().forEach(book -> logger.log(Level.INFO, book.toString()));

        List<Book> publishedAfterYer = this.bookRepository.findByYearGreaterThan(2000);
        logger.log(Level.INFO, "Books published after year 2000");
        publishedAfterYer.stream().forEach(book -> logger.log(Level.INFO, book.toString()));

        List<Book> byAuthorAndYear = this.bookRepository.findByAuthorAndYear("cezar", 2020);
        logger.log(Level.INFO, "Books published by cezar in 2000");
        byAuthorAndYear.stream().forEach(book -> logger.log(Level.INFO, book.toString()));

        //delete example
        //this.bookRepository.deleteAll();

        return demoBook;
    }

}