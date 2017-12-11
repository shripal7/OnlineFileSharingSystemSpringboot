package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import com.controller.UserController;
import com.entity.User;
import com.repository.GroupsRepository;
import com.repository.UserRepository;
import com.service.ShareFolderService;
import com.service.UserService;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class Shripal-unit {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private GroupsRepository groupsRepository;

    @MockBean
    private ShareFolderService sharedFolderService;

    @MockBean
    private User user;

//    @Test
//    public void deleteUsers() throws Exception {
//        mvc.perform(get("/user/delete").accept(MediaType.APPLICATION_JSON));
//    }
//


//    @Test
//    public void addUsers() throws Exception {
//        mvc.perform(get("/user/add").accept(MediaType.APPLICATION_JSON));
//    }
//

//    @Test
//    public void upload() throws Exception {
//        mvc.perform(get("/user/upload").accept(MediaType.APPLICATION_JSON));
//    }

//    @Test
//    public void logoutUsers() throws Exception {
//        mvc.perform(get("/user/logout").accept(MediaType.APPLICATION_JSON));
//    }

//    @Test
//    public void createsharefolder() throws Exception {
//        mvc.perform(get("/user/createsharefolder").accept(MediaType.APPLICATION_JSON));
//    }
//
    @Test
    public void uploadfilesUsers() throws Exception {
        mvc.perform(get("/user/uploadfiles").accept(MediaType.APPLICATION_JSON));
    }
}