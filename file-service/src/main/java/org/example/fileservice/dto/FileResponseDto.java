package org.example.fileservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class FileResponseDto {
    private String _id;
    private String fileName;
}
