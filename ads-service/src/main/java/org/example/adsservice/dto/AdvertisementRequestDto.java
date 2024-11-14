package org.example.adsservice.dto;

import org.example.adsservice.domain.Category;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AdvertisementRequestDto {
    private String title;
    private String description;
    private String location;
    private Category category;
//    private String image;
//    private String uploadDate;
}
