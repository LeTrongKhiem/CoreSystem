package com.example.backendapi.Service;

import com.example.backendapi.Abstractions.IBookService;
import com.example.backendapi.Abstractions.IFileStorageService;
import com.example.backendapi.Model.Book;
import com.example.backendapi.Model.BookImage;
import com.example.backendapi.Model.PartFileModel;
import com.example.backendapi.ModelMapping.BookModel;
import com.example.backendapi.Repository.BookImageRepository;
import com.example.backendapi.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Component
@Service
@RequiredArgsConstructor
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private IFileStorageService fileStorageService;
    @Autowired
    private BookImageRepository bookImageRepository;
    @Override
    public boolean postBook(BookModel bookModel) {
        Book book = new Book();
        if (book != null) {
            book.setId(UUID.randomUUID());
            book.setCreatedDate(new Date(System.currentTimeMillis()));
            if (bookModel.getProductImages() != null) {
                addImage(book.getId(), bookModel.getProductImages());
            }
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    public void addImage(UUID bookId, List<PartFileModel> productImage) {
        Book product = bookRepository.findById(bookId).get();
        if (productImage == null) {
            return;
        }
        for (PartFileModel file : productImage) {
            String path = fileStorageService.saveImage(file.getFile());
            BookImage bookImage = new BookImage();
            bookImage.setBook(product);
            bookImage.setImage(path);
            bookImage.setCreatedDate(new Date(System.currentTimeMillis()));
            bookImage.setId(UUID.randomUUID());
            bookImageRepository.save(bookImage);
        }
    }
}
