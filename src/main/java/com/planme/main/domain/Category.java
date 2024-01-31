package com.planme.main.domain;

import com.planme.main.domain.common.BaseEntity;
import com.planme.main.web.dto.CategoryDTO.CategoryRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String emoticon;

    private Integer color;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean meStoryHidden;    //  0: 숨기지 않음, 1: 숨김

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder.Default
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Schedule> scheduleList = new ArrayList<>();

    @OneToOne(mappedBy = "category", cascade = CascadeType.ALL)
    private Focus focus;

    @Builder.Default
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<MeStoryFocus> meStoryFocusList = new ArrayList<>();

    public void setMember(Member member) {
        if (member != null) {
            member.getCategoryList().remove(this);
        }
        this.member = member;
        member.getCategoryList().add(this);
    }

}
