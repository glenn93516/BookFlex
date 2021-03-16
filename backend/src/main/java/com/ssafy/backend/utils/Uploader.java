package com.ssafy.backend.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface Uploader {
    String upload(MultipartFile multipartFile, String dirName, String uniqueName) throws IOException;
}
