package org.example.fileservice.domain;

import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "files")
public class FileEntity {
    private String _id;
    private String fileName;
    private LocalDateTime uploadDate;
    private Binary bytes;
}
