package com.example.backendapi.Service;

import com.example.backendapi.Abstractions.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@RequiredArgsConstructor
public class BookService implements IBookService {
}
