package com.project.hibernate.dao;

import com.project.hibernate.entity.User.Users;

import java.sql.SQLException;
import java.util.List;


public interface UsersDAO {
    //create
    void add(Users users) throws SQLException;

    //read
    List<Users> getAll() throws SQLException;

    Users getById(Long id) throws SQLException;

    //update
    void update(Users users) throws SQLException;

    //delete
    void remove(Users users) throws SQLException;
}

