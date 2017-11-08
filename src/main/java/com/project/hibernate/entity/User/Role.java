package com.project.hibernate.entity.User;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="role", schema = "public", catalog = "fors")
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @ManyToMany(mappedBy="roles")
    private Set<Users> users = new HashSet<Users>();

    public Role() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

}

