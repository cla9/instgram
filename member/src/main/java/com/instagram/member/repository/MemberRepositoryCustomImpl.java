package com.instagram.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existsByUserName(String name) {
        long result = 0;

//        long result = queryFactory.selectOne()
//                                       .from(member)
//                                      .where(member.name.userName.eq(name)).fetchCount();
        return result > 0;
    }
}
