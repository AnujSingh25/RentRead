package com.crio.rentread.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crio.rentread.dto.Book;
import com.crio.rentread.exceptions.BookNotAvailableException;
import com.crio.rentread.exceptions.BookNotFoundException;
import com.crio.rentread.exceptions.BookNotRentedException;
import com.crio.rentread.exceptions.RentalException;
import com.crio.rentread.exceptions.UserNotFoundException;
import com.crio.rentread.exchanges.CreateBookRequest;
import com.crio.rentread.exchanges.GetAllBooksResponse;
import com.crio.rentread.exchanges.RentBookResponse;
import com.crio.rentread.exchanges.ReturnBookResponse;
import com.crio.rentread.exchanges.UpdateBookRequest;
import com.crio.rentread.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(BookController.BOOK_API_ENDPOINT)
public class BookController {
    public static final String BOOK_API_ENDPOINT = "/books";

    @Autowired
    private BookService bookService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> createBook(@Valid @RequestBody CreateBookRequest createBookRequest) {
        Book book = bookService.createBook(createBookRequest);
        return ResponseEntity.ok().body(book);
    }

    @PostMapping("/{bookId}/rent") 
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<RentBookResponse> rentBook(@PathVariable (value = "bookId") int bookId, @AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException, BookNotFoundException, BookNotAvailableException, RentalException {
        RentBookResponse rentBookResponse = bookService.rentBook(bookId, userDetails);
        return ResponseEntity.ok().body(rentBookResponse);
    }

    @PostMapping("/{bookId}/return")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReturnBookResponse> returnBook(@PathVariable (value = "bookId") int bookId, @AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException, BookNotFoundException, BookNotRentedException {
        ReturnBookResponse returnBookResponse = bookService.returnBook(bookId, userDetails);
        return ResponseEntity.ok().body(returnBookResponse);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<GetAllBooksResponse> getAllBooks() {
        GetAllBooksResponse getAllBooksResponse = bookService.findAllBooks();
        return ResponseEntity.ok().body(getAllBooksResponse);
    }

    @PutMapping("/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "bookId") int bookId, @Valid @RequestBody UpdateBookRequest updateBookRequest) throws BookNotFoundException {
        Book book = bookService.updateBook(bookId, updateBookRequest);
        return ResponseEntity.ok().body(book);
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteBook(@PathVariable(value = "bookId") int bookId) throws BookNotFoundException {
        String response = bookService.deleteBook(bookId);
        return ResponseEntity.ok().body(response);
    }

}
