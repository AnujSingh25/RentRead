package com.crio.rentread.repositoryServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.rentread.dto.Book;
import com.crio.rentread.exceptions.BookNotFoundException;
import com.crio.rentread.mapper.Mapper;
import com.crio.rentread.models.BookEntity;
import com.crio.rentread.repositories.BookRepository;

@Service
public class BookRepositoryServiceImpl implements BookRepositoryService {

    @Autowired
    private BookRepository bookRepository; 

    @Override
    public Book createBook(String title, String author, String genre, String availabilityStatus) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(title);
        bookEntity.setAuthor(author);
        bookEntity.setGenre(genre);
        bookEntity.setAvailabilityStatus(availabilityStatus);

        Book book = Mapper.mapToBook(bookRepository.save(bookEntity));
        return book;
    }

    @Override
    public Book saveBook(Book book) {
        BookEntity bookEntity = Mapper.mapToBookEntity(book);
        return Mapper.mapToBook(bookRepository.save(bookEntity));
    }

    @Override
    public Book findBookById(int bookId) throws BookNotFoundException {
        String message = "Could not find book with this ID: " + String.valueOf(bookId);
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(message));
        Book book = Mapper.mapToBook(bookEntity);
        return book;
    }

    @Override
    public Book updateBook(int bookId, String title, String author, String genre, String availabilityStatus) throws BookNotFoundException {
        String message = "Could not find book with this ID: " + String.valueOf(bookId);

        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(message));
        bookEntity.setTitle(title);
        bookEntity.setAuthor(author);
        bookEntity.setGenre(genre);
        bookEntity.setAvailabilityStatus(availabilityStatus);

        Book updatedBook = Mapper.mapToBook(bookRepository.save(bookEntity));
        return updatedBook;
    }

    @Override
    public List<Book> findAllBooks() {
        List<BookEntity> bookEntities = bookRepository.findAll();
        List<Book> books = Mapper.mapToBookList(bookEntities);
        return books;
    }

    @Override
    public void deleteBook(int bookId) throws BookNotFoundException {
        String message = "Could not find book with this ID: " + String.valueOf(bookId);
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(message));
        bookRepository.delete(bookEntity);
    }
    
}
