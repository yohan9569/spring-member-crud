package com.example.spring_member_crud.service;

import com.example.spring_member_crud.controller.dto.MemberCreateRequestDto;
import com.example.spring_member_crud.controller.dto.MemberPatchRequestDto;
import com.example.spring_member_crud.controller.dto.MemberResponseDto;
import com.example.spring_member_crud.repository.entity.Member;
import com.example.spring_member_crud.repository.IMemberRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private IMemberRepository repository;

    public MemberService(IMemberRepository repository) {
        this.repository = repository;
    }

    public MemberResponseDto create(MemberCreateRequestDto request) {
        Integer id = repository.generateId();
        Member requested = request.toEntity(id);
        Member created = repository.create(requested);
        return MemberResponseDto.from(created);
    }

    public MemberResponseDto getMemberById(Integer id) {
        Member member = repository.readMember(id);
        return MemberResponseDto.from(member);
    }

    public List<MemberResponseDto> getAllMembers() {
        List<Member> allMembers = repository.readAllMembers();
        return allMembers.stream().map(MemberResponseDto::from).toList();
    }

    public MemberResponseDto updateMember(Integer id, MemberPatchRequestDto request) {
        Member previous = repository.readMember(id);
        Member combination = request.updateEntity(previous);
        Member updated = repository.update(combination);
        return MemberResponseDto.from(updated);
    }

    public boolean deleteMember(Integer id) {
        if (repository.readMember(id) != null) {
            repository.delete(id);
            return true;
        } else {
            return false;
        }
    }
}
