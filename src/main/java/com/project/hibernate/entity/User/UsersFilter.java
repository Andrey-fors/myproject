package com.project.hibernate.entity.User;

import java.io.Serializable;

public class UsersFilter implements Serializable
{
    private static final long serialVersionUID = 5932288959759585586L;
    String secondname;

    public String getSecondname()
    {
        return secondname;
    }

    public void setSecondname(String secondName)
    {
        this.secondname = secondName;
    }

    public boolean match(Users user)
    {
        boolean ret=true;

        if (secondname!=null)
        {
            if (!user.getSecondname().startsWith(secondname)) ret=false;
        }

        return ret;
    }
}

