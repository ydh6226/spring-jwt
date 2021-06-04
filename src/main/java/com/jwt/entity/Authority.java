package com.jwt.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
public class Authority {

    @Id
    private String authorityName;

    public Authority(String authorityName) {
        this.authorityName = authorityName;
    }
}
