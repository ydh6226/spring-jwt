package com.jwt.controller;

import com.jwt.dto.MemberDto;
import com.jwt.entity.Member;
import com.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@Valid @RequestBody MemberDto memberDto) {
        return ResponseEntity.ok(userService.signUp(memberDto));
    }

    @GetMapping("/user")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Member> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    @GetMapping("/user/{username}")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Member> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username).get());
    }


}
