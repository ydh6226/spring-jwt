package com.jwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @JsonIgnore
    @Id @GeneratedValue
    private Long id;

    private String name;

    @JsonIgnore
    private String password;

    private String nickname;

    private boolean activated;

    @ManyToMany
    private Set<Authority> authorities = new HashSet<>();
}
