//package com.example.bulletinboard.controller;
//
//
//import com.example.bulletinboard.service.UrlService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.net.URI;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/redirect")
//public class UrlController {
//
//    @Autowired
//    private UrlService urlService;
//
//    @GetMapping("/{shortUrl}")
//    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
//        Optional<String> longUrl = urlService.findLongUrl(shortUrl);
//
//        if (longUrl.isPresent()) {
//            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(longUrl.get())).build();
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<String> createUrl(@RequestParam String shortUrl, @RequestParam String longUrl) {
//        urlService.createShortUrl(shortUrl, longUrl);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Short URL created successfully");
//    }
//}