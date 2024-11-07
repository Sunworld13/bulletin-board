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


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<List<FileResponseDto>> upload(@RequestParam("files") MultipartFile[] files) {
        List<FileResponseDto> fileResponseDtos = imageService.upload(files);
        return ResponseEntity.ok(fileResponseDtos);
    }
    @GetMapping("/{_id}")
    public ResponseEntity<List<Resource>> download(@RequestParam("ids") String[] ids) {
        return ResponseEntity.ok(imageService.download(ids));
    }

    @DeleteMapping("/{_id}")
    public ResponseEntity<List<String>> delete(@RequestParam("ids") String[] ids) {
        List<String> responseMessages = imageService.delete(ids);
        return ResponseEntity.ok(responseMessages);
    }

}
