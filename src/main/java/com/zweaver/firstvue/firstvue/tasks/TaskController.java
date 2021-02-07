package com.zweaver.firstvue.firstvue.tasks;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class TaskController {
    List<Task> taskList = new ArrayList<>();

    /*
    * Create new tasks
    */
    @RequestMapping(value = "users/{username}/tasks/create", method = RequestMethod.POST)
    public void createNewTask(@PathVariable("username") String username,
            @RequestBody Task newTask) {
        
        if (newTask.getUsername().equals(username)) {
            // check if task name already exists
            List<Task> filteredTaskList = taskList.stream().filter(t -> t.getTaskName().equals(newTask.getTaskName())).collect(Collectors.toList());
            if (filteredTaskList.size() > 0) {
                System.out.println("Task already exists.");
            } else {
                taskList.add(newTask);
            }
        } else {
            System.out.println("Username does not match passed username.");
        }
    }

    /*
    * Fetch tasks
    */
    @RequestMapping(value = "users/{username}/tasks/fetch", method = RequestMethod.POST)
    public List<Task> fetchTasks(@PathVariable("username") String username,
            @RequestBody String passedUserName) {

        if (passedUserName.equals(username)) {
            // filter for specific username
            List<Task> filteredTaskList = taskList.stream().filter(t -> t.getUsername().equals(passedUserName)).collect(Collectors.toList());
            return filteredTaskList;
        } else {
            System.out.println("Username does not match passed username.");
        }

        return null;
    }
}
