package com.example.demo;


import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
        Book demoBook = new Book("demoBook " + date.toString(), "bogdan", 2020);
        Book savedBook = this.bookRepository.save(demoBook);


        logger.log(Level.INFO, "Hello from logger: DemoBook id = " + demoBook.getId());
        return demoBook;
    }

}