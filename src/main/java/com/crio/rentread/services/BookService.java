package com.crio.rentread.services;

import org.springframework.security.core.userdetails.UserDetails;

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

public interface BookService {

    Book createBook(CreateBookRequest createBookRequest);

    RentBookResponse rentBook(int bookId, UserDetails userDetails) throws UserNotFoundException, BookNotFoundException, BookNotAvailableException, RentalException;

    ReturnBookResponse returnBook(int bookId, UserDetails userDetails) throws UserNotFoundException, BookNotFoundException, BookNotRentedException;

    Book updateBook(int bookId, UpdateBookRequest updateBookRequest) throws BookNotFoundException;

    GetAllBooksResponse findAllBooks();

    String deleteBook(int bookId) throws BookNotFoundException;
    
}
