package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

import java.util.List;


public interface BookRepository extends DatastoreRepository<Book, Long> {

    List<Book> findByAuthor(String author);

    List<Book> findByYearGreaterThan(int year);

    List<Book> findByAuthorAndYear(String author, int year);

}