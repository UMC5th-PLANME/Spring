package com.planme.main.domain;

import com.planme.main.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.DynamicUpdate;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String loginType;

    private String socialId;

    private String email;

    private String profileImage;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean status; //  0: 비활성화, 1: 활성

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Category> categoryList = new ArrayList<>();

}
