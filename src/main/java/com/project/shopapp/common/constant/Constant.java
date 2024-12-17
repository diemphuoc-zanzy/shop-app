package com.project.shopapp.common.constant;


import java.util.List;

public final class Constant {

    public static final class CHARACTER {
        public static final String EMPTY = "";
        public static final String SPACE = " ";
        public static final String COMMA = ",";
        public static final String COLON = ":";
        public static final String SEMICOLON = ";";
        public static final String PERIOD = ".";
        public static final String SLASH = "/";
        public static final String UNDERSCORE = "_";
        public static final String QUOTE = "'";
        public static final String DOUBLE_QUOTE = "";
    }

    public static final class UPLOAD {
        public static final String UPLOAD_DIR = "uploads/images/";
        public static final int MAX_UPLOAD_SIZE = 10 * 1024 * 1024; // 10MB
        public static final int MIN_UPLOAD_SIZE = 0; // 0KB
        public static final int MAX_UPLOAD_IMAGE_PER_PRODUCT = 5;

        public static final class MESSAGE {
            public static final String ERROR_FILE_EXTENSION = "Invalid file extension. Only %s are allowed.";
            public static final String ERROR_MAX_UPLOAD_SIZE = "File size must be less than 10MB.";
            public static final String ERROR_MIN_UPLOAD_SIZE = "File size must be greater than 0KB.";
            public static final String ERROR_IMAGE_UPLOAD = "Image upload failed.";
            public static final String ERROR_IMAGE_DELETE = "Image delete failed.";
            public static final String ERROR_IMAGE_DELETE_FILE = "Image File %s delete failed.";
            public static final String ERROR_MAX_UPLOAD_IMAGE_PER_PRODUCT = "Image files can't be greater than 5";
            public static final String ERROR_PRODUCT_NOT_FOUND = "Product not found.";
            public static final String ERROR_PRODUCT_ID_REQUIRED = "Product ID is required.";

            public static final String REMAINING_UPLOAD_IMAGE_PER_PRODUCT = "Remaining upload image files: ";
        }
    }

    public static final class HEADER {
        public static final String AUTHORIZATION = "Authorization";
        public static final String BEARER = "Bearer ";
        public static final List<String> ALLOWED =
                List.of(
                        "authorization",
                        "content-type",
                        "application/json",
                        "accept",
                        "x-auth-token"
                );
    }

    public static final class METHOD {
        public static final List<String> ALLOWED =
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE"
                );
    }
}
