package com.zweaver.firstvue.firstvue.users;

import java.util.HashMap;

import com.zweaver.firstvue.firstvue.users.User;

import com.zweaver.firstvue.firstvue.projectlist.ProjectList;
import com.zweaver.firstvue.firstvue.projectlist.ProjectListController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://vanillaprojectmanager-dev.netlify.app")
public class UsersController {
    private HashMap<String, User> usersMap = new HashMap<>();

    @RequestMapping(value = "users/createUser", method = RequestMethod.POST)
    public void createNewUser(@RequestBody User newUser) {
        if (usersMap.get(newUser.getUsername()) != null) {
            System.out.println("That user already exists.");
        } else {
            usersMap.put(newUser.getUsername(), newUser);
            ProjectListController.projectListMap.put(newUser.getUsername(), new ProjectList());
        }
    }
}