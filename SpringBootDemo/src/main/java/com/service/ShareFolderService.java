package com.service;

import com.entity.Groups;
import com.entity.User;
import com.repository.GroupsRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.CreateShareFolder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ShareFolderService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupsRepository groupsRepository;

    public boolean createShareFolder(CreateShareFolder createsharefolder) {


        return false;

    }


    public Groups[] listUserGroups(String userEmail) {

        if (userEmail.equals("")) {
            return new Groups[]{};
        } else {
            User user = userRepository.findByEmail(userEmail);
            Set<Groups> groupSet = user.getGroups();
            return  groupSet.toArray(new Groups[groupSet.size()]);
        }
    }

    public boolean createGroup(String groupName, String memberEmail) {

        if (groupName.equals("") && memberEmail.equals("")) {
            return false;
        } else {
            Groups groups = new Groups();
            groups.setGroup_name(groupName);
            User user = userRepository.findByEmail(memberEmail);
            groups.setOwner_id(user.getId());
            groupsRepository.save(groups);
            boolean x = new File("./" + groups.getGroupId()).mkdir();
            if (x) return true;
            else return false;
        }
    }

    public boolean addMembersToGroup(Integer group_id, String memberEmail) {

        if (memberEmail.equals("") && group_id == null) {
            return false;
        } else {
            Groups group = groupsRepository.findByGroupId(group_id);
            User groupMemberUser = userRepository.findByEmail(memberEmail);

            Set<User> memberSet = group.getUser();
            memberSet.add(groupMemberUser);
            group.setUser(memberSet);

            Set<Groups> groupSet = groupMemberUser.getGroups();
            groupSet.add(group);
            groupMemberUser.setGroupt(groupSet);

            userRepository.save(groupMemberUser);
            groupsRepository.save(group);

        }

        return true;

    }


    public User[] listGroupMembers(Integer group_id) {

        if (group_id == null) {
            return new User[]{};
        } else {

            Groups groups = groupsRepository.findByGroupId(group_id);
            Set<User> memberSet = groups.getUser();
            return (User[]) memberSet.toArray(new User[memberSet.size()]);
        }
    }

    public void uploader(MultipartFile file, Integer group_id) {

        try {
            byte[] bytes = file.getBytes();

            Path path = Paths.get("./" + group_id);
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
