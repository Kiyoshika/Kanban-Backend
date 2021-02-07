package com.zweaver.firstvue.firstvue.projectlist;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.node.TextNode;
import com.zweaver.firstvue.firstvue.tasks.Task;
import com.zweaver.firstvue.firstvue.tasks.TaskController;
import com.zweaver.firstvue.firstvue.users.User;
import com.zweaver.firstvue.firstvue.users.UsersController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "https://vanillaprojectmanager-dev.netlify.app"})
public class ProjectListController {
    public static HashMap<String, ProjectList> projectListMap = new HashMap<>();
    private ProjectList projectList = new ProjectList();

    /*
    * Fetch list of projects at the user level
    */
    @RequestMapping(value = "projectList", method = RequestMethod.POST)
    public List<String> fetchProjectList(@RequestBody User authUser) {

        if (authUser.getUsername().equals(UsersController.getUsername(authUser.getUsername()))
            && authUser.getPassword().equals(UsersController.getPassword(authUser.getUsername()))) {
            return projectListMap.get(authUser.getUsername()).getProjectList();
        } else {
            System.out.println("Failed authentication for request -- projectList/");
        }
        return null;
    }

    /*
    * Add new project at the user level
    */
    @RequestMapping(value = "projectList/add/{newProjectName}", method = RequestMethod.POST)
    public void addNewProject(@PathVariable("newProjectName") String newProjectName,
            @RequestBody User authUser) {
        if (authUser.getUsername().equals(UsersController.getUsername(authUser.getUsername()))
            && authUser.getPassword().equals(UsersController.getPassword(authUser.getUsername()))) {
            projectListMap.get(authUser.getUsername()).addProject(newProjectName);
        } else {
            System.out.println("Failed authentication for request -- projectList/add/"+newProjectName);
        }
    }

    /*
    * Remove project at the user level
    */
    @RequestMapping(value = "projectList/remove/{projectName}", method = RequestMethod.POST)
    public void removeProject(@PathVariable("projectName") String projectName,
            @RequestBody User authUser) {
                if (authUser.getUsername().equals(UsersController.getUsername(authUser.getUsername()))
                    && authUser.getPassword().equals(UsersController.getPassword(authUser.getUsername()))) {
                    try {
                        projectListMap.get(authUser.getUsername()).removeProject(projectName);
                        // remove all tasks associated with that project
                        List<Task> associatedTasks = TaskController.taskList.stream().filter(t -> t.getProjectName().equals(projectName)).collect(Collectors.toList());
                        TaskController.taskList.removeAll(associatedTasks);
                    } catch (Exception e) {
                        System.out.println("Tried removing project that doesn't exist.");
                    }
                } else {
                    System.out.println("Failed authentication for request -- projectList/remove/"+projectName);
                }
            }
}
