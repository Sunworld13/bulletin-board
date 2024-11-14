package com.example.filter.controller;

import com.example.filter.domain.Advertisement;
import com.example.filter.domain.Category;
import com.example.filter.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/filterAdvertisement")
@RestController
@RequiredArgsConstructor
public class AdvertisimentController {

    private final AdvertisementService advertisementService;
    @GetMapping
    public ResponseEntity<List<Advertisement>> getAdvertisementFilter(
        @RequestParam(value = "user_id", required = false) Long user_id,
        @RequestParam(value = "category", required = false) Category category,
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "description", required = false) String description,
        @RequestParam(value = "location", required = false) String location
        ) {
        return ResponseEntity.ok(
                advertisementService.getAdvertisementById(user_id,
                        category, name, description, location)
        );
    }

}
