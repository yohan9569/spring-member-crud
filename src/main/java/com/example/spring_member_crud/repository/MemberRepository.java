package com.example.spring_member_crud.repository;

import com.example.spring_member_crud.controller.dto.JobType;
import com.example.spring_member_crud.exception.MemberNotFoundException;
import com.example.spring_member_crud.repository.entity.Member;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class MemberRepository implements IMemberRepository {
    private static final Map<Integer, Member> memberMap;
    private Integer nextId = 4;

//    public MemberRepository() {
//        this.memberMap = new HashMap<>();
//        this.nextId = 0;
//    }

    static {
        memberMap = new HashMap<>();
        memberMap.put(1, new Member(1, "Aaron", 10, JobType.DEVELOPER, "Backend", LocalDateTime.now().plusMinutes(10)));
        memberMap.put(2, new Member(2, "Baron", 20, JobType.ENGINEER, "Frontend", LocalDateTime.now().plusMinutes(20)));
        memberMap.put(3,
            new Member(3, "Caron", 30, JobType.DEVELOPER, "DevOps/SRE", LocalDateTime.now().plusMinutes(30)));
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
