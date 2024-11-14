package org.example.adsservice.dto;


import org.example.adsservice.domain.Category;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class AdvertisementResponseDto {
    private Long id;
    private String title;
    private String description;
    private String location;
    private Category category;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String image;
    private String uploadDate;;
}
