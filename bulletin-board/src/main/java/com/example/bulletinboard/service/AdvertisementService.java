package com.example.bulletinboard.service;

import com.example.bulletinboard.domain.Advertisement;
import com.example.bulletinboard.domain.Users;
import com.example.bulletinboard.dto.AdvertisementDto;
import com.example.bulletinboard.dto.AdvertisementResponseDto;
import com.example.bulletinboard.repository.AdvertisementRepository;
import com.example.bulletinboard.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private  final AdvertisementRepository advertisementRepository;
    private final UsersRepository usersRepository;

    public AdvertisementResponseDto create(Long userId, AdvertisementDto advertisementDto) {

        Optional<Users> usersOptional = usersRepository.findById(userId);

        if (usersOptional.isEmpty()) {
            throw new IllegalArgumentException(userId + " not found");
        }

        Users users = usersOptional.get();

        Advertisement advertisement = Advertisement.builder()
                .title(advertisementDto.getTitle())
                .description(advertisementDto.getDescription())
                .location(advertisementDto.getLocation())
                .category(advertisementDto.getCategory())
                .users(users)
                .build();

        advertisement = advertisementRepository.save(advertisement);

        AdvertisementResponseDto advertisementResponseDto = AdvertisementResponseDto.builder()
                .id(advertisement.getId())
                .title(advertisementDto.getTitle())
                .description(advertisementDto.getDescription())
                .location(advertisementDto.getLocation())
                .category(advertisementDto.getCategory())
                .firstName(users.getFirstName())
                .lastName(users.getLastName())
                .email(users.getEmail())
                .phone(users.getPhone())
                .build();

        return advertisementResponseDto;
    }

    public List<AdvertisementResponseDto> getAllAdvertisement(Long userId) {
        List<AdvertisementResponseDto> correctAdvertisements = new ArrayList<>();
        Optional<Users> usersOptional = usersRepository.findById(userId);

        if (usersOptional.isEmpty()) {
            throw new IllegalArgumentException(userId + " not found");
        }

        Users users = usersOptional.get();

        List<Advertisement> advertisements = advertisementRepository.getAdvertisementByUsers(users);

        for (Advertisement advertisement : advertisements) {
            AdvertisementResponseDto advertisementResponseDto = AdvertisementResponseDto.builder()
                    .id(advertisement.getId())
                    .title(advertisement.getTitle())
                    .description(advertisement.getDescription())
                    .location(advertisement.getLocation())
                    .category(advertisement.getCategory())
                    .firstName(users.getFirstName())
                    .lastName(users.getLastName())
                    .email(users.getEmail())
                    .phone(users.getPhone())
                    .build();
            correctAdvertisements.add(advertisementResponseDto);
        }
        return correctAdvertisements;
    }

    public AdvertisementResponseDto getOneAdvertisement(Long userId, Long advertisementId) {
        Optional<Users> usersOptional = usersRepository.findById(userId);

        if (usersOptional.isEmpty()) {
            throw new IllegalArgumentException(userId + " not found");
        }

        Users users = usersOptional.get();

        Optional<Advertisement> advertisementOptional = advertisementRepository.findById(advertisementId);


        if(advertisementOptional.isEmpty() || advertisementOptional.get().getUsers().getId() != users.getId()) {
            throw new IllegalArgumentException(advertisementId + " not found");
        }

        Advertisement advertisement = advertisementOptional.get();
        AdvertisementResponseDto advertisementResponseDto = AdvertisementResponseDto.builder()
                .id(advertisement.getId())
                .title(advertisement.getTitle())
                .description(advertisement.getDescription())
                .location(advertisement.getLocation())
                .category(advertisement.getCategory())
                .firstName(users.getFirstName())
                .lastName(users.getLastName())
                .email(users.getEmail())
                .phone(users.getPhone())
                .build();

        return advertisementResponseDto;
    }

    public AdvertisementResponseDto update(Long userId, Long advertisementId, AdvertisementDto advertisementDto) {
        Optional<Users> usersOptional = usersRepository.findById(userId);

        if (usersOptional.isEmpty()) {
            throw new IllegalArgumentException(userId + " not found");
        }

        Users users = usersOptional.get();

        Optional<Advertisement> advertisementOptional = advertisementRepository.findById(advertisementId);


        if(advertisementOptional.isEmpty() || advertisementOptional.get().getUsers().getId() != users.getId()) {
            throw new IllegalArgumentException(advertisementId + " not found");
        }

        Advertisement advertisement = advertisementOptional.get();

        advertisement.setTitle(advertisementDto.getTitle());
        advertisement.setDescription(advertisementDto.getDescription());
        advertisement.setLocation(advertisementDto.getLocation());
        advertisement.setCategory(advertisementDto.getCategory());

        advertisement = advertisementRepository.save(advertisement);

        AdvertisementResponseDto advertisementResponseDto = AdvertisementResponseDto.builder()
                .id(advertisement.getId())
                .title(advertisementDto.getTitle())
                .description(advertisementDto.getDescription())
                .location(advertisementDto.getLocation())
                .category(advertisementDto.getCategory())
                .firstName(users.getFirstName())
                .lastName(users.getLastName())
                .email(users.getEmail())
                .phone(users.getPhone())
                .build();
        return advertisementResponseDto;
    }

    public String deleteAdvertisement(Long userId, Long advertisementId) {
        Optional<Users> usersOptional = usersRepository.findById(userId);

        if (usersOptional.isEmpty()) {
            throw new IllegalArgumentException(userId + " not found");
        }

        Users users = usersOptional.get();

        Optional<Advertisement> advertisementOptional = advertisementRepository.findById(advertisementId);


        if(advertisementOptional.isEmpty() || advertisementOptional.get().getUsers().getId() != users.getId()) {
            throw new IllegalArgumentException(advertisementId + " not found");
        }

        Advertisement advertisement = advertisementOptional.get();

        advertisementRepository.delete(advertisement);

        return "Advertisement deleted " + advertisement.getTitle();
    }
}
