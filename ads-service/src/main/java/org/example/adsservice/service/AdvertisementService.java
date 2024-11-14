package org.example.adsservice.service;

import org.example.adsservice.domain.Advertisement;
import org.example.adsservice.domain.User;
import org.example.adsservice.dto.AdvertisementRequestDto;
import org.example.adsservice.dto.AdvertisementResponseDto;
import org.example.adsservice.repository.AdvertisementRepository;
import org.example.adsservice.repository.UserRepository;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private  final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;

    public AdvertisementResponseDto create(Long userId, AdvertisementRequestDto advertisementDto) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with id: " + userId + " not found");
        }

        User user = userOptional.get();

        Advertisement advertisement = Advertisement.builder()
                .title(advertisementDto.getTitle())
                .description(advertisementDto.getDescription())
                .location(advertisementDto.getLocation())
                .category(advertisementDto.getCategory())
                .user(user)
                .build();

        advertisement = advertisementRepository.save(advertisement);

        AdvertisementResponseDto advertisementResponseDto = AdvertisementResponseDto.builder()
                .id(advertisement.getId())
                .title(advertisementDto.getTitle())
                .description(advertisementDto.getDescription())
                .location(advertisementDto.getLocation())
                .category(advertisementDto.getCategory())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();

        return advertisementResponseDto;
    }

    public List<AdvertisementResponseDto> getAllAdvertisement(Long userId) {
        List<AdvertisementResponseDto> correctAdvertisements = new ArrayList<>();
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with id: " + userId + " not found");
        }
        User user = userOptional.get();

        List<Advertisement> advertisements = advertisementRepository.getAdvertisementsByUser(user);

        for (Advertisement advertisement : advertisements) {
            AdvertisementResponseDto advertisementResponseDto = AdvertisementResponseDto.builder()
                    .id(advertisement.getId())
                    .title(advertisement.getTitle())
                    .description(advertisement.getDescription())
                    .location(advertisement.getLocation())
                    .category(advertisement.getCategory())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .build();
            correctAdvertisements.add(advertisementResponseDto);
        }
        return correctAdvertisements;
    }

    public AdvertisementResponseDto getOneAdvertisement(Long userId, Long advertisementId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with id: " + userId + " not found");
        }
        User user = userOptional.get();

        Optional<Advertisement> advertisementOptional = advertisementRepository.findById(advertisementId);


        if(advertisementOptional.isEmpty() || advertisementOptional.get().getUser().getId() != user.getId()) {
            throw new IllegalArgumentException("Advertisement with id: " + advertisementId + " not found");
        }

        Advertisement advertisement = advertisementOptional.get();

        AdvertisementResponseDto advertisementResponseDto = AdvertisementResponseDto.builder()
                .id(advertisement.getId())
                .title(advertisement.getTitle())
                .description(advertisement.getDescription())
                .location(advertisement.getLocation())
                .category(advertisement.getCategory())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();

        return advertisementResponseDto;
    }

    public AdvertisementResponseDto updateAdvertisement(Long userId, Long advertisementId, AdvertisementRequestDto advertisementDto) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with id: " + userId + " not found");
        }

        User user = userOptional.get();

        Optional<Advertisement> advertisementOptional = advertisementRepository.findById(advertisementId);

        if(advertisementOptional.isEmpty() || advertisementOptional.get().getUser().getId() != user.getId()) {
            throw new IllegalArgumentException("Advertisement with id: " +advertisementId + " not found or user not equals");
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
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
        return advertisementResponseDto;
    }

    public String deleteAdvertisement(Long userId, Long advertisementId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with id: " + userId + " not found");
        }

        User user = userOptional.get();

        Optional<Advertisement> advertisementOptional = advertisementRepository.findById(advertisementId);


        if(advertisementOptional.isEmpty() || advertisementOptional.get().getUser().getId() != user.getId()) {
            throw new IllegalArgumentException("Advertisement with id: " +advertisementId + " not found or user not equals");
        }

        Advertisement advertisement = advertisementOptional.get();

        advertisementRepository.delete(advertisement);

        return "Advertisement " + advertisement.getTitle() + " deleted ";
    }
}
