package com.se.gk.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.se.gk.entity.Book;

@Repository
public interface BookService  extends  JpaRepository<Book,Integer>{

}
