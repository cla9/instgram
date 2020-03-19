package com.instagram.member.repository;

import static com.instagram.member.model.aggregate.QMember.member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existsByUserName(String name) {
        long result = queryFactory.selectOne()
                                       .from(member)
                                      .where(member.name.userName.eq(name)).fetchCount();
        return result > 0;
    }
}
