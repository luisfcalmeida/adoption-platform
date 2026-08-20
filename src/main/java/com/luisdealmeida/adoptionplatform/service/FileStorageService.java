package com.luisdealmeida.adoptionplatform.service;

import com.luisdealmeida.adoptionplatform.exception.InvalidFileException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.UUID;

@Service
public class FileStorageService {

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of("image/jpeg", "image/png", "image/webp");

    private final Path uploadDir;

    public FileStorageService(@Value("${file.upload-dir}") String uploadDir) {
        this.uploadDir = Path.of(uploadDir);
        try {
            Files.createDirectories(this.uploadDir);
        } catch (IOException e) {
            throw new UncheckedIOException("Could not create upload directory: " + this.uploadDir, e);
        }
    }

    public String store(MultipartFile file) {
        if (file.isEmpty()) {
            throw new InvalidFileException("Uploaded file is empty");
        }

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType)) {
            throw new InvalidFileException("Unsupported file type: " + contentType
                    + ". Allowed types: " + ALLOWED_CONTENT_TYPES);
        }

        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String fileName = UUID.randomUUID() + (extension != null ? "." + extension : "");

        try {
            Files.copy(file.getInputStream(), uploadDir.resolve(fileName));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to store file: " + fileName, e);
        }

        return fileName;
    }

    public void delete(String fileName) {
        try {
            Files.deleteIfExists(uploadDir.resolve(fileName));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to delete file: " + fileName, e);
        }
    }

    public Path resolve(String fileName) {
        return uploadDir.resolve(fileName);
    }
}
