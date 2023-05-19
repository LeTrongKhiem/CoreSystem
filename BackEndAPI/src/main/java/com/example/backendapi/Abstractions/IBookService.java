package com.example.backendapi.Abstractions;

import com.example.backendapi.Model.Book;
import com.example.backendapi.Model.User;
import com.example.backendapi.ModelMapping.BookModel;
import com.example.backendapi.ModelMapping.PagingModel;

import java.util.List;
import java.util.UUID;

public interface IBookService {
    boolean postBook(UUID useId, BookModel book);
    PagingModel<BookModel> getAllBook(String keyword, int page, int size, String sortType, String sortBy, String mostRecent);
    BookModel getBookByID(UUID id);
    String exchangedBook(UUID bookId, User user);
}
