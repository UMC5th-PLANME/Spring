package com.planme.main.domain;

import com.planme.main.domain.common.BaseEntity;
import com.planme.main.domain.mapping.TermsAgreement;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Terms extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean required;  //  0: 선택, 1: 필수

}
