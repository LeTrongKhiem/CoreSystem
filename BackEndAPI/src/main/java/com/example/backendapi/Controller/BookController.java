package com.example.backendapi.Controller;

import com.example.backendapi.Abstractions.IBookService;
import com.example.backendapi.Abstractions.IFileStorageService;
import com.example.backendapi.Extensions.FileUploadModelConverter;
import com.example.backendapi.Model.Book;
import com.example.backendapi.Model.PartFileModel;
import com.example.backendapi.ModelMapping.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private IBookService bookService;
    @Autowired
    private IFileStorageService fileStorageService;

    @PostMapping("/post")
    public ResponseEntity<Boolean> post(@ModelAttribute BookModel book) {
        boolean result = bookService.postBook(book);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
