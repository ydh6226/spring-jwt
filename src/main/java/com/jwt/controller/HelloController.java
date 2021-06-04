package com.jwt.controller;

import com.jwt.entity.Member;
import com.jwt.entity.UserMember;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member) {
        System.out.println(member);
        return ResponseEntity.ok("hello");
    }
}
