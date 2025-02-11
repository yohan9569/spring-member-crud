package com.example.spring_member_crud.repository;

import com.example.spring_member_crud.exception.MemberNotFoundException;
import com.example.spring_member_crud.repository.entity.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class MemberRepository implements IMemberRepository {
    private final Map<Integer, Member> memberMap;
    private Integer nextId;

    public MemberRepository() {
        this.memberMap = new HashMap<>();
        this.nextId = 0;
    }

    public Integer generateId() {
        nextId = memberMap.size() + 1;
        log.info(String.valueOf(nextId));
        return nextId;
    }

    public Member create(Member member) {
        Integer id = member.getId();
        memberMap.put(id, member);
        return memberMap.get(id);
    }

    public Member readMember(Integer id) {
        return Optional.ofNullable(memberMap.get(id))
            .orElseThrow(() -> new MemberNotFoundException(id));
    }

    public List<Member> readAllMembers() {
        return memberMap.values().stream().toList();
    }

    public Member update(Member member) {
        Integer id = member.getId();
        memberMap.put(id, member);
        return memberMap.get(id);
    }

    public void delete(Integer id) {
        memberMap.remove(id);
    }
}
