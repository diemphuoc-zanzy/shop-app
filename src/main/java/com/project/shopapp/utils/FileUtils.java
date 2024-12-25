package com.project.shopapp.utils;

import com.project.shopapp.common.RECORD_STATUS;
import com.project.shopapp.common.RESPONSE_STATUS;
import com.project.shopapp.common.base.ResponseWrapper;
import com.project.shopapp.common.constant.Constant;
import com.project.shopapp.confiuration.exception.BadRequestException;
import com.project.shopapp.models.ProductImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtils {
    private final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public String getFileExtension(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (filename != null && filename.contains(Constant.CHARACTER.PERIOD)) {
            // return extension of file
            return filename.substring(filename.lastIndexOf(Constant.CHARACTER.PERIOD) + 1);
        }
        return Constant.CHARACTER.EMPTY; // return if empty
    }

    public String getUniqueFileName(String fileName) {
        String fileNameCleanPath = StringUtils.cleanPath(fileName);
        return UUID.randomUUID() + Constant.CHARACTER.UNDERSCORE + fileNameCleanPath;
    }

    public String storeFile(MultipartFile file, String uniqueFileName) {
        try {
            Path uploadDir = Paths.get(Constant.UPLOAD.UPLOAD_DIR);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            Path filePath = Paths.get(uploadDir.toString(), uniqueFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return filePath.toString();
        } catch (Exception ex) {
            logger.error("Error save storing file: {}", ex.getMessage());
            throw new BadRequestException(ex.getMessage());
        }
    }


    public void removeImage(ProductImage imageExist, List<ResponseWrapper> errors) {
        try {
            Path filePath = Paths.get(imageExist.getImageUrl());
            Files.deleteIfExists(filePath);
            imageExist.updateRecordStatus(RECORD_STATUS.DELETED);
        } catch (Exception e) {
            String message = String.format(Constant.UPLOAD.MESSAGE.ERROR_IMAGE_DELETE_FILE, imageExist.getImageUrl());
            ResponseWrapper error = new ResponseWrapper(
                    RESPONSE_STATUS.ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    message
            );
            errors.add(error);
        }
    }
}
