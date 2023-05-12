package com.example.backendapi.SeedData;

import com.example.backendapi.Model.Book;
import com.example.backendapi.Model.User;
import com.example.backendapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SeedUserDemo implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        seedUser();
    }

    public void seedUser() {
        if (userRepository.count() == 0) {
            UUID id1 = UUID.randomUUID();
            UUID id2 = UUID.randomUUID();
            UUID id3 = UUID.fromString("00000000-0000-0000-0000-000000000001");
            UUID id4 = UUID.fromString("00000000-0000-0000-0000-000000000002");
            User userPostBook = new User(id3, "demouserpost", "userpost@gmail.com" , "123456", true, "Le", "Khiem", "TP. HCM", "0123456789");
            User userexchanged = new User(id4, "demouserexchanged", "userexchanged@gmail.com" , "123456", true, "Le", "Khiem", "TP. HCM", "0123456789");
            List<User> listUser = List.of(userPostBook, userexchanged);
            Book book = new Book();
            userRepository.saveAll(listUser);
        }
    }
}
