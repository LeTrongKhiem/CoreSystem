package com.example.backendapi.ModelMapping;

import com.example.backendapi.Model.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public class BookMapping {
    public static Book toBookModel(BookModel model) {
        Book book = new Book();
        book.setName(model.getName());
        book.setAuthor(model.getAuthor());
        book.setCreatedDate(model.getCreatedDate());
        book.setDescription(model.getDescription());
        book.setContactPhone(model.getContactPhone());
        return book;
    }

    public static BookModel toBook(Book book) {
        BookModel model = new BookModel();
        model.setId(book.getId());
        model.setName(book.getName());
        model.setAuthor(book.getAuthor());
        model.setCreatedDate(book.getCreatedDate());
        model.setDescription(book.getDescription());
        model.setContactPhone(book.getContactPhone());
        model.setProductImagesUrl(book.getBookImages().stream().map(x -> x.getImage()).toList());
        return model;
    }

    public static List<BookModel> toListBook(List<Book> models) {
        return models.stream().map(BookMapping::toBook).toList();
    }

    public static Page<BookModel> getPageProducts(Page<Book> products) {
        return products.map(BookMapping::toBook);
    }
}
