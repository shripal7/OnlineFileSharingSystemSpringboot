package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.entity.Groups;

public interface GroupsRepository extends CrudRepository<Groups, Long> {
    // List<GroupT> find
    // List<User> find
    //List<GroupT> findBy(Integer group_id);

    Groups findByGroupId(Integer group_id);
}
