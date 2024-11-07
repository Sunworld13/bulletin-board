package org.example.fileservice.services;

import lombok.RequiredArgsConstructor;
import org.bson.types.Binary;
import org.example.fileservice.dto.FileResponseDto;
import org.example.fileservice.exception.FileException;
import org.example.fileservice.repository.FileRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.example.fileservice.domain.FileEntity;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final FileRepository fileRepository;

    public List<FileResponseDto> upload(MultipartFile[] files) {
        if (files.length < 1 || files.length > 10) {
            throw new IllegalArgumentException("Вы можете добавить от 1 до 10 файлов.");
        }
        List<FileResponseDto> fileResponseDtos = new ArrayList<>();

        for (MultipartFile file : files) {
        try {
            FileEntity fileEntity = FileEntity.builder()
                    .fileName(file.getOriginalFilename())
                    .uploadDate(LocalDateTime.now())
                    .bytes(new Binary(file.getBytes())                    )
                    .build();

            fileEntity = fileRepository.save(fileEntity);

            FileResponseDto fileResponseDto = FileResponseDto.builder()
                    ._id(fileEntity.get_id())
                    .fileName(fileEntity.getFileName())
                    .build();
            fileResponseDtos.add(fileResponseDto);
        } catch (IOException e) {
            throw new FileException("Could not upload the file");
        }
    }
        return fileResponseDtos;
    }

    public List<Resource> download(String[] ids) {
        if (ids.length < 1 || ids.length > 10) {
            throw new IllegalArgumentException("Вы можете запросить от 1 до 10 файлов.");
        }

        List<Resource> resources = new ArrayList<>();
        for (String id : ids) {

            FileEntity file = fileRepository.findById(id)
                    .orElseThrow(() -> new FileException(String.format("File with id: %s not found", id)));

            Resource resource = new ByteArrayResource(file.getBytes().getData());
            resources.add(resource);
        }
        return resources;
    }

    public  List<String> delete(String[] ids)  {
        if (ids.length < 1 || ids.length > 10) {
            throw new IllegalArgumentException("Вы можете удалить от 1 до 10 файлов.");
        }

        List<String> responseMessages = new ArrayList<>();

        for (String id : ids) {
            FileEntity file = fileRepository.findById(id)
                    .orElseThrow(() -> new FileException(String.format("File with id: %s not found", id)));
            fileRepository.delete(file);
            responseMessages.add(String.format("File with id: %s deleted", id));
        }
        return responseMessages;
    }
}
