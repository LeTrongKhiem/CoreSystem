package com.example.backendapi.Abstractions;

import com.example.backendapi.Model.Book;
import com.example.backendapi.ModelMapping.BookModel;
import com.example.backendapi.ModelMapping.PagingModel;

import java.util.List;

public interface IBookService {
    boolean postBook(BookModel book);
    PagingModel<BookModel> getAllBook(String keyword, int page, int size, String sortType, String sortBy, String mostRecent);
}
