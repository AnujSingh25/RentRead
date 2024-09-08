package com.crio.rentread.exchanges;

import java.util.List;

import com.crio.rentread.dto.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllBooksResponse {
    
    private List<Book> books;
    
}
