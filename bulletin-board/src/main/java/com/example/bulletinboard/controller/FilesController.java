package com.example.bulletinboard.controller;

import com.example.bulletinboard.domain.Files;
import com.example.bulletinboard.repository.FilesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FilesController {

    private final FilesRepository filesRepository;

    @GetMapping("/files")
    public List<Files> getAdvertisements() {
        return filesRepository.findAll();
    }

    @PostMapping("/create-files")
    public Files createAdvertisement(@RequestBody Files advertisement){
        return filesRepository.save(advertisement);
    }
}
