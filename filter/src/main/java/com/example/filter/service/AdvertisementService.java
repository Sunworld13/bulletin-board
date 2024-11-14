package com.example.filter.service;

import com.example.filter.domain.Advertisement;
import com.example.filter.domain.Category;
import com.example.filter.repository.AdvertisementRepository;
import com.example.filter.repository.AdvertisementSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    public List<Advertisement> getAdvertisementById(
            Long user_id, Category category, String name, String description, String location
    ) {
        Specification<Advertisement> advertisementSpecification = AdvertisementSpecification.build();

        if (user_id != null){
            advertisementSpecification = advertisementSpecification.and(AdvertisementSpecification.findByUsrAdver(user_id));
        }

        if (category != null){
            advertisementSpecification = advertisementSpecification.and(AdvertisementSpecification.findByCategory(category));
        }
        if (name != null){
            advertisementSpecification = advertisementSpecification.and(AdvertisementSpecification.findByName(name));
        }
        if (description != null){
            advertisementSpecification = advertisementSpecification.and(AdvertisementSpecification.findByDescription(description));
        }
        if (location != null){
            advertisementSpecification = advertisementSpecification.and(AdvertisementSpecification.findByLocation(location));
        }


        return advertisementRepository.findAll(advertisementSpecification);
    }
}