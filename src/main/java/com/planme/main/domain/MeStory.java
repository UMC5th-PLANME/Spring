package com.planme.main.domain;

import com.planme.main.domain.common.BaseEntity;
import com.planme.main.domain.mapping.MeContent;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MeStory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "meStory", cascade = CascadeType.ALL)
    private List<MeContent> meContentList = new ArrayList<>();
}
