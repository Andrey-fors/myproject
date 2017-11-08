package com.project.hibernate.entity.Project;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "projectsfiles", schema = "public", catalog = "fors")
public class ProjectsFiles implements Serializable {
    private static final long serialVersionUID = 3650093635327146301L;
    @Id
    @Column(name = "projectsfiles_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int projectfilesID;
    @Column(name = "name", nullable = false, length = 60)
    private String name;
    @Column(name = "type", nullable = false, length = 60)
    private String type;

    public ProjectsFiles(){

    }

    public ProjectsFiles (String name, String type){
        this.name = name;
        this.type = type;
    }



    public int getProjectsfilesID() {
        return projectfilesID;
    }

    public void setProjectsfilesID(int projectfilesID) {
        this.projectfilesID = projectfilesID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
