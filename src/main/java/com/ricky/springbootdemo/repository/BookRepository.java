package com.ricky.springbootdemo.repository;

import com.ricky.springbootdemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Long> {
}
