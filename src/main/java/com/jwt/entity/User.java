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
public class User {

    @JsonIgnore
    @Id @GeneratedValue
    private Long userId;

    private String username;

    @JsonIgnore
    private String password;

    private String nickname;;

    private boolean activated;

    @ManyToMany
    private Set<Authority> authorities = new HashSet<>();
}
