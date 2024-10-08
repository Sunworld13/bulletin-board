package com.example.bulletinboard.controller;

import com.example.bulletinboard.domain.Users;
import com.example.bulletinboard.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UsersRepository usersRepository;

    @GetMapping("/users")
    public List<Users> getUsers(){
        return usersRepository.findAll();
    }

    @PostMapping("/create-users")
    public Users createUser(@RequestBody Users user){
        return usersRepository.save(user);
    }
}
