package com.zweaver.firstvue.firstvue.projectlist;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ProjectListController {
    public static HashMap<String, ProjectList> projectListMap = new HashMap<>();
    private ProjectList projectList = new ProjectList();

    /*
    * Fetch list of projects at the user level
    */
    @RequestMapping(value = "users/{username}/projectList", method = RequestMethod.POST)
    public List<String> fetchProjectList(@PathVariable("username") String username,
            @RequestBody String passedUserName) {

                System.out.println("Passed username: " + passedUserName);

        if (passedUserName.equals(username)) {
            return projectListMap.get(username).getProjectList();
        } else {
            System.out.println("Username does not match passed username.");
        }
        return null;
    }

    /*
    * Add new project at the user level
    */
    @RequestMapping(value = "users/{username}/projectList/add/{newProjectName}", method = RequestMethod.POST)
    public void addNewProject(@PathVariable("username") String username,
            @PathVariable("newProjectName") String newProjectName,
            @RequestBody String passedUserName) {

        if (username.equals(passedUserName)) {
            projectListMap.get(passedUserName).addProject(newProjectName);
        } else {
            System.out.println("Username does not match passed username.");
        }
    }
}
