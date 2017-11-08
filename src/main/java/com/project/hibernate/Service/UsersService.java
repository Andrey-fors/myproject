package com.project.hibernate.Service;

import com.project.hibernate.dao.UsersDAO;
import com.project.hibernate.entity.User.Users;
import com.project.hibernate.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;


public class UsersService extends SessionUtil implements UsersDAO{

    public void add(Users users) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(users);

        //close session with a transaction
        closeTransactionSesstion();
    }

    public List<Users> getAll() throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM USERS";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Users.class);
        List<Users> usersList = query.list();

        //close session with a transaction
        closeTransactionSesstion();

        return usersList;
    }

    public Users getById(Long id) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM USERS WHERE ID = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Users.class);
        query.setParameter("id", id);

        Users users = (Users) query.getSingleResult();

        //close session with a transaction
        closeTransactionSesstion();

        return users;
    }

    public void update(Users users) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.update(users);

        //close session with a transaction
        closeTransactionSesstion();
    }

    public void remove(Users users) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.remove(users);

        //close session with a transaction
        closeTransactionSesstion();
    }
}
