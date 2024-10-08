//package com.example.bulletinboard.service;
//
//import com.example.bulletinboard.domain.Url;
//import com.example.bulletinboard.repository.UrlRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UrlService {
//
//    @Autowired
//    private UrlRepository urlRepository;
//
//
//    public Optional<String> findLongUrl(String shortUrl) {
//        Optional<Url> url = urlRepository.findByShortUrl(shortUrl);
//        return url.map(Url::getLongUrl);
//    }
//
//    public Url createShortUrl(String shortUrl, String longUrl) {
//        Url newUrl = new Url(shortUrl, longUrl);
//        return urlRepository.save(newUrl);
//    }
//}