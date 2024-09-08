package com.crio.rentread.exchanges;

import java.util.Set;

import com.crio.rentread.dto.Book;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnBookResponse {
    private int id;

    private String firstName;

    private String lastName;

    private String role;

    private Set<Book> rentedBooks;
}
