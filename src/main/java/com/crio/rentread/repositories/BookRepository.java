package com.crio.rentread.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.rentread.models.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    
}
