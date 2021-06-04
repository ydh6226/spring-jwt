package com.jwt.entity;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.stream.Collectors;

@Getter
public class UserMember extends User {

    private Member member;

    public UserMember(Member member) {
        super(member.getName(), member.getPassword(), member.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toSet()));
        this.member = member;
    }
}
