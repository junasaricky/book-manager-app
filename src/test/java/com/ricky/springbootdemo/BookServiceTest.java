package com.ricky.springbootdemo;

import com.ricky.springbootdemo.model.Book;
import com.ricky.springbootdemo.repository.BookRepository;
import com.ricky.springbootdemo.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private BookRepository bookRepo;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepo = mock(BookRepository.class);
        bookService = new BookService(bookRepo);
    }

    @Test
    void testGetAllBooks() {
        Book b1 = new Book(); b1.setTitle("Book 1");
        Book b2 = new Book(); b2.setTitle("Book 2");

        when(bookRepo.findAll()).thenReturn(Arrays.asList(b1, b2));

        List<Book> books = bookService.getAll();
        assertEquals(2, books.size());
        assertEquals("Book 1", books.get(0).getTitle());
    }

    // @Test
    // void testGetAllBooks_shouldFail() {
    //    Book book1 = new Book();
    //    book1.setTitle("Book A");

    //    Book book2 = new Book();
    //    book2.setTitle("Book B");

    //    List<Book> books = Arrays.asList(book1, book2);

    //    when(bookRepo.findAll()).thenReturn(books);

    //    List<Book> result = bookService.getAll();

    //    assertEquals(3, result.size());
    //}

    @Test
    void testGetBookById() {
        Book b = new Book();
        b.setId(1L);
        b.setTitle("Sample Book");

        when(bookRepo.findById(1L)).thenReturn(Optional.of(b));

        Book result = bookService.getById(1L);
        assertNotNull(result);
        assertEquals("Sample Book", result.getTitle());
    }

    // @Test
    // void testGetBookById_shouldFail() {
    //     Book b = new Book();
    //     b.setId(1L);
    //     b.setTitle("Sample Book");

    //     when(bookRepo.findById(1L)).thenReturn(Optional.of(b));

    //     Book result = bookService.getById(1L);
    //     assertNotNull(result);
    //     assertEquals("Wrong Title", result.getTitle()); 
    // }

    @Test
    void testSaveBook() {
        Book b = new Book();
        b.setTitle("To Save");

        when(bookRepo.save(b)).thenReturn(b);

        Book saved = bookService.save(b);
        assertEquals("To Save", saved.getTitle());
    }

    // @Test
    // void testSaveBook_shouldFail() {
    //     Book bookToSave = new Book();
    //     bookToSave.setTitle("Correct Title");

    //     Book savedBook = new Book();
    //     savedBook.setId(1L);
    //     savedBook.setTitle("Correct Title");

    //     when(bookRepo.save(bookToSave)).thenReturn(savedBook);

    //     Book result = bookService.save(bookToSave);

    //     assertEquals("Wrong Title", result.getTitle());
    // }

    @Test
    void testDeleteBook() {
        Long bookId = 1L;
        doNothing().when(bookRepo).deleteById(bookId);

        bookService.delete(bookId);

        verify(bookRepo, times(1)).deleteById(bookId);
    }

    // @Test
    // void testDeleteBook_shouldFail() {
    //     Long bookId = 1L;

    //     bookService.delete(bookId);

    //     verify(bookRepo, times(1)).deleteById(999L);
    // }

}
