package com.example.bulletinboard.dto;

import com.example.bulletinboard.domain.Category;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AdvertisementDto {
    private String title;
    private String description;
    private String location;
    private Category category;
//    private String image;
//    private String uploadDate;
}
