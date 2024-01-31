package com.planme.main.repository;

import com.planme.main.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByNickname(String nickname);
    boolean existsBySocialId(String socialId);
    boolean existsByEmail(String email);
    Optional<Member> findByEmail(String email);
    Member findByNickname(String nickname);
}
