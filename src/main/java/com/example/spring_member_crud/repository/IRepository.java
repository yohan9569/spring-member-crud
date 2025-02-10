package com.example.spring_member_crud.repository;

import java.util.List;

public interface IRepository<T, id> {
    id generateId();
    T create(T member);
    T readMember(id id);
    List<T> readAllMembers();
    T update(T member);
    void delete(id id);
}
