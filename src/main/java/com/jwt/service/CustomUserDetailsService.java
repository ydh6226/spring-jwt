package com.jwt.service;

import com.jwt.entity.Member;
import com.jwt.entity.UserMember;
import com.jwt.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "UserDetailsService")
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(final String name) throws UsernameNotFoundException {
        Member member = memberRepository.findOneWithAuthoritiesByName(name)
                .orElseThrow(() -> new UsernameNotFoundException(name + " -> 등록되지 않은 유저입니다."));

        return new UserMember(member);
    }
}
