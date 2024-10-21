package org.example.fileservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.example.fileservice.domain.FileEntity;

public interface FileRepository extends MongoRepository<FileEntity, String> {
}
