package org.example.fileservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.fileservice.dto.FileResponseDto;
import org.example.fileservice.services.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
/*
import simbirsoft.mgu.ozon.fileservice.dto.FileResponseDto;
import simbirsoft.mgu.ozon.fileservice.services.ImageService;
*/

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<FileResponseDto> upload(MultipartFile file) {
        return ResponseEntity.ok(imageService.upload(file));
    }

    @GetMapping("/{_id}")
    public ResponseEntity<Resource> download(@PathVariable("_id") String _id) {
        return ResponseEntity.ok(imageService.download(_id));
    }

    @DeleteMapping("/{_id}")
    public ResponseEntity<String> delete(@PathVariable("_id") String _id) {
        return ResponseEntity.ok(imageService.delete(_id));
    }

}
