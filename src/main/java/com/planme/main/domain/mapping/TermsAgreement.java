package com.planme.main.domain.mapping;

import com.planme.main.domain.Member;
import com.planme.main.domain.Terms;
import com.planme.main.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TermsAgreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean agree;  //  0: 비동의, 1: 동의

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_id")
    private Terms terms;

    //==연관 관계 메서드==//
    public void setMember(Member member){
        if(this.member != null){ // 이미 있다면
            member.getTermsAgreementList().remove(this);
        }
        this.member = member;
        member.getTermsAgreementList().add(this);
    }

}
