package com.zweaver.firstvue.firstvue.tasks;

import java.util.List;
import java.util.stream.Collectors;

import com.zweaver.firstvue.firstvue.users.User;
import com.zweaver.firstvue.firstvue.users.UsersController;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "https://vanillaprojectmanager-dev.netlify.app"})
public class TaskController {
    public static List<Task> taskList = new ArrayList<>();

    /*
     * Create new tasks
     */
    @RequestMapping(value = "tasks/create", method = RequestMethod.POST)
    public void createNewTask(@RequestBody Task newTask) {

        // authenticate request
        if (newTask.getUsername().equals(UsersController.getUsername(newTask.getUsername()))
                && newTask.getPassword().equals(UsersController.getPassword(newTask.getUsername()))) {
            // check if task name already exists
            List<Task> filteredTaskList = taskList.stream()
                    .filter(t -> t.getTaskName().toLowerCase().equals(newTask.getTaskName().toLowerCase()))
                    .collect(Collectors.toList());
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
    @RequestMapping(value = "tasks/fetch", method = RequestMethod.POST)
    public List<Task> fetchTasks(@RequestBody User authUser) {

        // authenticate request
        if (authUser.getUsername().equals(UsersController.getUsername(authUser.getUsername()))
                && authUser.getPassword().equals(UsersController.getPassword(authUser.getUsername()))) {
            // filter for specific username
            List<Task> filteredTaskList = taskList.stream().filter(t -> t.getUsername().equals(authUser.getUsername()))
                    .collect(Collectors.toList());
            return filteredTaskList;
        } else {
            System.out.println("Authentication failed for this request -- tasks/fetch");
        }

        return null;
    }

    /*
     * Update task
     */
    @RequestMapping(value = "tasks/update", method = RequestMethod.POST)
    public void updateTask(@RequestBody Task updatedTask) {

        // authenticate request
        if (updatedTask.getUsername().equals(UsersController.getUsername(updatedTask.getUsername()))
                && updatedTask.getPassword().equals(UsersController.getPassword(updatedTask.getUsername()))) {
            // filter for the selected task name and update it in original list
            List<Task> filteredTaskList = taskList.stream()
                    .filter(t -> t.getTaskName().equals(updatedTask.getTaskName())).collect(Collectors.toList());
            taskList.set(taskList.indexOf(filteredTaskList.get(0)), updatedTask);
        } else {
            System.out.println("Username does not match passed username.");
        }
    }
}
