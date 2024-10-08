package com.example.bulletinboard.controller;

import com.example.bulletinboard.domain.Advertisement;
import com.example.bulletinboard.dto.AdvertisementDto;
import com.example.bulletinboard.dto.AdvertisementResponseDto;
import com.example.bulletinboard.repository.AdvertisementRepository;
import com.example.bulletinboard.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advertisement/{userId}")
@RequiredArgsConstructor
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @PostMapping()
    public ResponseEntity<AdvertisementResponseDto> createAdvertisement(@PathVariable("userId") Long userId,
            @RequestBody AdvertisementDto advertisementDto) {
        return ResponseEntity.ok(advertisementService.create(userId, advertisementDto));
    }

    @GetMapping()
    public ResponseEntity<List<AdvertisementResponseDto>> getAlladvertisement(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(advertisementService.getAllAdvertisement(userId));
    }

    @GetMapping("/{advertisementId}")
    public ResponseEntity<AdvertisementResponseDto> getOneAdvertisement(
            @PathVariable("userId") Long userId,
            @PathVariable("advertisementId") Long advertisementId){
        return ResponseEntity.ok(advertisementService.getOneAdvertisement(userId, advertisementId));
    }

    @PutMapping("/{advertisementId}")
    public ResponseEntity<AdvertisementResponseDto>  updateAdvertisement(
            @PathVariable("userId") Long userId,
            @PathVariable("advertisementId") Long advertisementId,
            @RequestBody AdvertisementDto advertisementDto
    ){
        return ResponseEntity.ok(advertisementService.update(userId, advertisementId, advertisementDto));
    }

    @DeleteMapping("/{advertisementId}")
    public ResponseEntity<String> deleteAdvertisement(
            @PathVariable("userId") Long userId,
            @PathVariable("advertisementId") Long advertisementId
    ){
        return ResponseEntity.ok(advertisementService.deleteAdvertisement(userId, advertisementId));
    }
}
