package com.jwt;

import com.jwt.entity.Authority;
import com.jwt.entity.Member;
import com.jwt.repository.AuthorityRepository;
import com.jwt.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final MemberRepository memberRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initDb() {
        Authority role_user = new Authority("ROLE_USER");
        Authority role_admin = new Authority("ROLE_ADMIN");
        authorityRepository.save(role_user);
        authorityRepository.save(role_admin);


        HashSet<Authority> authorities = new HashSet<>() {{
            add(role_user);
            add(role_admin);
        }};

        Member member1 = Member.builder()
                .name("admin")
                .password(passwordEncoder.encode("admin"))
                .nickname("admin")
                .activated(true)
                .authorities(authorities)
                .build();
        memberRepository.save(member1);

        Member member2 = Member.builder()
                .name("user")
                .password(passwordEncoder.encode("user"))
                .nickname("user")
                .activated(true)
                .authorities(Set.of(role_user))
                .build();
        memberRepository.save(member2);
    }
}
