package com.instagram.member.repository;

import com.instagram.member.model.Email;
import com.instagram.member.model.aggregate.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, String> , MemberRepositoryCustom {
    boolean existsByEmail(Email email);
}
