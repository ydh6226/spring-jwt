package com.jwt.controller;

import com.jwt.aws.S3Uploader;
import com.jwt.entity.Member;
import com.jwt.entity.UserMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class HelloController {

    private final S3Uploader s3Uploader;

    @GetMapping("/hello")
    public ResponseEntity<String> hello(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member) {
        System.out.println(member);
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        if (file.isEmpty() || !contentType.startsWith("image")) {
            return new ResponseEntity<>("이미지 파일을 선택하세요", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(s3Uploader.upload(file));
    }
}
