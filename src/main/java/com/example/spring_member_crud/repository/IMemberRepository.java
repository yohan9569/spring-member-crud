package com.example.spring_member_crud.repository;

import com.example.spring_member_crud.repository.entity.Member;
import java.util.List;

public interface IMemberRepository extends IRepository<Member, Integer> {
    Integer generateId();
    Member create(Member member);
    Member readMember(Integer id);
    List<Member> readAllMembers();
    Member update(Member member);
    void delete(Integer id);
}
