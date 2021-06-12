package com.jwt.controller;

import com.jwt.aws.S3Uploader;
import com.jwt.entity.Member;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HelloController {

    private final S3Uploader s3Uploader;

    @GetMapping("/hello")
    public ResponseEntity<String> hello(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member) {
        System.out.println(member);
        return ResponseEntity.ok("hello");
    }

    @ApiResponses(value = @ApiResponse(code = 400, message = "파일이 없거나 이미지 파일이 아닌 경우"))
    @PostMapping("/upload")
    public ResponseEntity<String> upload(MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        if (file.isEmpty() || !contentType.startsWith("image")) {
            return new ResponseEntity<>("이미지 파일을 선택하세요", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(s3Uploader.upload(file));
    }

    @GetMapping("/say-hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("hello");
    }
}
