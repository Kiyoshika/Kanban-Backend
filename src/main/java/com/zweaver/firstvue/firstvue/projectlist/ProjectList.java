package com.zweaver.firstvue.firstvue.projectlist;

import java.util.ArrayList;
import java.util.List;

public class ProjectList {
    private List<String> projectList = new ArrayList<>();
    
    public ProjectList() {}
    public ProjectList(List<String> projectList) {
        this.projectList = projectList;
    }

    public List<String> getProjectList() {
        return this.projectList;
    }

    public void setProjectList(List<String> projectList) {
        this.projectList = projectList;
    }

    public void addProject(String projectName) {
        this.projectList.add(projectName);
    }

    public void removeProject(String projectName) {
        this.projectList.remove(projectName);
    }
}
