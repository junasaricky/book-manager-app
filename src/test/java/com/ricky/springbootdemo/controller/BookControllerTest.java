package com.ricky.springbootdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricky.springbootdemo.model.Book;
import com.ricky.springbootdemo.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;

@WebMvcTest(BookController.class)
@Import(BookControllerTest.Config.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookService bookService;

    @TestConfiguration
    static class Config {
        @Bean
        public BookService bookService() {
            return Mockito.mock(BookService.class); 
        }
    }

    @Test
    void testGetAllBooks() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Spring Boot in Action");

        when(bookService.getAll()).thenReturn(Arrays.asList(book));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Spring Boot in Action"));
    }

    @Test
    void testCreateBook() throws Exception {
        Book book = new Book();
        book.setTitle("New Book");
        book.setAuthor("Ricky");
        book.setPublisher("Self");
        book.setIsbn("1234567890");

        when(bookService.save(Mockito.any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Book"));
    }

    @Test
    void testGetBookById() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Sample Book");

        when(bookService.getById(1L)).thenReturn(book);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sample Book"));
    }

    // @Test
    // void testUpdateBook() throws Exception {
    //     Book existingBook = new Book();
    //     existingBook.setId(1L);
    //     existingBook.setTitle("Old Title");

    //     Book updatedBook = new Book();
    //     updatedBook.setId(1L);
    //     updatedBook.setTitle("Updated Title");

    //     when(bookService.getById(1L)).thenReturn(existingBook);
    //     when(bookService.save(Mockito.any(Book.class))).thenReturn(updatedBook);

    //     mockMvc.perform(put("/books/1")
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content(objectMapper.writeValueAsString(updatedBook)))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.title").value("Updated Title"));
    // }

    @Test
    void testDeleteBook() throws Exception {

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent());
    }

}
