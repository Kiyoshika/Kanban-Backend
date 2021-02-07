package com.zweaver.firstvue.firstvue.tasks;

public class Task {
    private String projectName;
    private String taskName;
    private String taskStage;
    private String taskType;
    private String taskPriority;
    private String taskDescription;

    // "authentication" purposes
    private String username;
    private String password;

    public Task() {}
    
    // Getters

    public String getProjectName() {
        return this.projectName;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getTaskStage() {
        return this.taskStage;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public String getTaskPriority() {
        return this.taskPriority;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public String getUsername() {
        return this.username.toLowerCase();
    }

    public String getPassword() {
        return this.password;
    }

    // Setters

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskStage(String taskStage) {
        this.taskStage = taskStage;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
