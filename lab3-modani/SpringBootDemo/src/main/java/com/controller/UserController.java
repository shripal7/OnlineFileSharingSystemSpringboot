package com.controller;


import antlr.collections.List;

import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.entity.CreateShareFolder;
import com.entity.User;
import com.service.ShareFolderService;
import com.service.UserService;
import com.entity.Groups;
import javax.servlet.http.HttpSession;

import java.io.File;

@Controller    // This means that this class is a Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/user") // This means URL's start with /demo (after Application path)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ShareFolderService sharefolderservice;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody String user, HttpSession session) {

     JSONObject jsonObject = new JSONObject(user);
        session.setAttribute("name", jsonObject.getString("username"));
        return new ResponseEntity(userService.login(jsonObject.getString("username"), jsonObject.getString("password")), HttpStatus.OK);
    }

    //list of files
    @PostMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    File[] getAllUsers1(HttpSession session) {
        return userService.getAllUsers1(session.getAttribute("name").toString());

    }

    @PostMapping("/uploadfiles")
    public ResponseEntity<?> singleFileUpload(@RequestParam("file") MultipartFile file, HttpSession session) {
        String userfolder = session.getAttribute("name").toString();
        userService.uploader(file, userfolder);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletefile(@RequestBody String filename, HttpSession session) {
        userService.deletefile(filename,session.getAttribute("name").toString());
        return new ResponseEntity(HttpStatus.OK);

    }



    @PostMapping(path = "/createsharefolder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createsharefolder(@RequestBody CreateShareFolder createsharefolder, HttpSession session) {
        userService.createShareFolder(createsharefolder, session.getAttribute("name").toString());
        return new ResponseEntity(HttpStatus.OK);

    }

    @PostMapping(path = "/createfolder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createfolder(@RequestBody CreateShareFolder createsharefolder, HttpSession session) {
      userService.createFolder(createsharefolder, session.getAttribute("name").toString());
        return new ResponseEntity(HttpStatus.OK);

    }





    @PostMapping("/createGroup")
    public ResponseEntity<?> createGroup(@RequestBody String user,
                                         HttpSession session) {
        String userEmail = session.getAttribute("name").toString();
        JSONObject userObj = new JSONObject(user);
        boolean checker = sharefolderservice.createGroup(userObj.getString("groupname"), userEmail);
        if (checker) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/listGroupMembers")
    public ResponseEntity<?> listGroupMembers(@RequestBody String user,
                                              HttpSession session) {
        String userEmail = session.getAttribute("name").toString();
        JSONObject userObj = new JSONObject(user);
        System.out.println(userObj);

        User[] memberList = sharefolderservice.listGroupMembers((userObj.getInt("groupId")));
    if (memberList.length > 0) {
            return new ResponseEntity(memberList, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addMembersToGroup")
    public ResponseEntity<?> addMembersToGroup(@RequestBody String user,
                                               HttpSession session) {
        String userEmail = session.getAttribute("name").toString();
        JSONObject userObj = new JSONObject(user);
        boolean checker = sharefolderservice.addMembersToGroup(Integer.parseInt(userObj.getString("groupId")), userObj.getString("memberEmail"));

        if (checker) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/listUserGroups")
    public ResponseEntity<?> listUserGroups(@RequestBody String user,
                                            HttpSession session) {
        String userEmail = session.getAttribute("name").toString();
        JSONObject userObj = new JSONObject(user);
        Groups[] groupList = sharefolderservice.listUserGroups(userEmail);
         if (groupList.length > 0) {
            return new ResponseEntity(groupList, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }



}