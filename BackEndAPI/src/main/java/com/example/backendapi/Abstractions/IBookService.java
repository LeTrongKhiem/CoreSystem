package com.example.backendapi.Abstractions;

import com.example.backendapi.Model.Book;
import com.example.backendapi.ModelMapping.BookModel;

public interface IBookService {
    boolean postBook(BookModel book);
}
