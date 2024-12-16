package com.project.shopapp.common;

import java.util.Arrays;
import java.util.List;

public enum IMAGE_FORMAT {JPEG("jpeg", "JPEG Image", "image/jpeg"),
    PNG("png", "PNG Image", "image/png"),
    GIF("gif", "GIF Image", "image/gif"),
    BMP("bmp", "Bitmap Image", "image/bmp"),
    TIFF("tiff", "TIFF Image", "image/tiff"),
    WEBP("webp", "WebP Image", "image/webp"),
    HEIF("heif", "HEIF Image", "image/heif"),
    SVG("svg", "SVG Image", "image/svg+xml"),
    EPS("eps", "EPS Image", "application/postscript"),
    PDF("pdf", "PDF Document", "application/pdf");

    private final String extension;  // extension file
    private final String description;  // description image
    private final String mimeType;  // MIME type of image

    IMAGE_FORMAT(String extension, String description, String mimeType) {
        this.extension = extension;
        this.description = description;
        this.mimeType = mimeType;
    }

    public String getExtension() {
        return extension;
    }

    public String getDescription() {
        return description;
    }

    public String getMimeType() {
        return mimeType;
    }

    public static List<String> getExtensions() {
        return Arrays.stream(IMAGE_FORMAT.values())
                .map(IMAGE_FORMAT::getExtension)
                .toList();
    }

    public static IMAGE_FORMAT fromExtension(String extension) {
        for (IMAGE_FORMAT format : IMAGE_FORMAT.values()) {
            if (format.getExtension().equalsIgnoreCase(extension)) {
                return format;
            }
        }
        throw new IllegalArgumentException("Invalid image format: " + extension);
    }

    public static IMAGE_FORMAT fromMimeType(String mimeType) {
        for (IMAGE_FORMAT format : IMAGE_FORMAT.values()) {
            if (format.getMimeType().equalsIgnoreCase(mimeType)) {
                return format;
            }
        }
        throw new IllegalArgumentException("Invalid MIME type: " + mimeType);
    }
}