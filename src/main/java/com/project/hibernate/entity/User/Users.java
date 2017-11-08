package com.project.hibernate.entity.User;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "users", schema = "public", catalog = "fors")
public class Users implements Serializable {

    private static final long serialVersionUID = -7142451095293273287L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name", nullable = false, length = 60)
    private String name;
    @Column(name = "patr", nullable = true, length = 60)
    private String patr;
    @Column(name = "secondname", nullable = false, length = 60)
    private String secondname;
    @Column(name = "userbranch", nullable = true, length = 60)
    private String userbranch;
    @Column(name = "useradress", nullable = false, length = 80)
    private String useradress;
    @Column(name = "userconf", nullable = false, length = 60)
    private String userconf;
    @Column(name = "userdate", nullable = true, length = 60)
    private String userdate;
    @Column(name = "userimg", nullable = true, length = 60)
    private String userimg;
    @Column(name = "usermobile", nullable = true, length = 60)
    private String usermobile;
    @Column(name = "userofficialmobile", nullable = true, length = 60)
    private String userofficialmobile;
    @Column(name = "userposition", nullable = false, length = 60)
    private String userposition;

    @ManyToMany
    @JoinTable(name="user_role")
    private Set<Role> roles = new HashSet<Role>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Column(name = "usersub", nullable = false, length = 60)
    private String usersub;

    public Users() {

    }

    public Users(int id) {
        this.id = id;
    }

    public Users(String name, String patr, String secondname, String useradress,
                 String userconf, String userdate, String userimg,
                 String usermobile, String userofficialmobile, String userposition,
                 String usersub) {
        this.name = name;
        this.patr = patr;
        this.secondname = secondname;
        this.useradress = useradress;
        this.userconf = userconf;
        this.userdate = userdate;
        this.userimg = userimg;
        this.usermobile = usermobile;
        this.userofficialmobile = userofficialmobile;
        this.userposition = userposition;
        this.usersub = usersub;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPatr() {
        return patr;
    }

    public void setPatr(String patr) {
        this.patr = patr;
    }


    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }


    public String getUseradress() {
        return useradress;
    }

    public void setUseradress(String useradress) {
        this.useradress = useradress;
    }


    public String getUserconf() {
        return userconf;
    }

    public void setUserconf(String userconf) {
        this.userconf = userconf;
    }


    public String getUserdate() {
        return userdate;
    }

    public void setUserdate(String userdate) {
        this.userdate = userdate;
    }


    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }


    public String getUsermobile() {
        return usermobile;
    }

    public void setUsermobile(String usermobile) {
        this.usermobile = usermobile;
    }


    public String getUserofficialmobile() {
        return userofficialmobile;
    }

    public void setUserofficialmobile(String userofficialmobile) {
        this.userofficialmobile = userofficialmobile;
    }


    public String getUserposition() {
        return userposition;
    }

    public void setUserposition(String userposition) {
        this.userposition = userposition;
    }


    public String getUsersub() {
        return usersub;
    }

    public void setUsersub(String usersub) {
        this.usersub = usersub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users that = (Users) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (patr != null ? !patr.equals(that.patr) : that.patr != null) return false;
        if (secondname != null ? !secondname.equals(that.secondname) : that.secondname != null) return false;
        if (userbranch != null ? !userbranch.equals(that.userbranch) : that.userbranch != null) return false;
        if (useradress != null ? !useradress.equals(that.useradress) : that.useradress != null) return false;
        if (userconf != null ? !userconf.equals(that.userconf) : that.userconf != null) return false;
        if (userdate != null ? !userdate.equals(that.userdate) : that.userdate != null) return false;
        if (userimg != null ? !userimg.equals(that.userimg) : that.userimg != null) return false;
        if (usermobile != null ? !usermobile.equals(that.usermobile) : that.usermobile != null) return false;
        if (userofficialmobile != null ? !userofficialmobile.equals(that.userofficialmobile) : that.userofficialmobile != null)
            return false;
        if (userposition != null ? !userposition.equals(that.userposition) : that.userposition != null) return false;
        if (usersub != null ? !usersub.equals(that.usersub) : that.usersub != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (patr != null ? patr.hashCode() : 0);
        result = 31 * result + (secondname != null ? secondname.hashCode() : 0);
        result = 31 * result + (userbranch != null ? userbranch.hashCode() : 0);
        result = 31 * result + (useradress != null ? useradress.hashCode() : 0);
        result = 31 * result + (userconf != null ? userconf.hashCode() : 0);
        result = 31 * result + (userdate != null ? userdate.hashCode() : 0);
        result = 31 * result + (userimg != null ? userimg.hashCode() : 0);
        result = 31 * result + (usermobile != null ? usermobile.hashCode() : 0);
        result = 31 * result + (userofficialmobile != null ? userofficialmobile.hashCode() : 0);
        result = 31 * result + (userposition != null ? userposition.hashCode() : 0);
        result = 31 * result + (usersub != null ? usersub.hashCode() : 0);
        return result;
    }

    public String getUserbranch() {
        return userbranch;
    }

    public void setUserbranch(String userbranch) {
        this.userbranch = userbranch;
    }
}
