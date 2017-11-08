package com.project.Wicket.User;

import com.project.Wicket.MainPage;
import com.project.Wicket.Product.ProductsList;
import com.project.hibernate.entity.User.Users;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.PropertyModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserPage extends WebPage {

    private static final long serialVersionUID = -5223126205489216801L;

    private static SessionFactory factory;

    private String IMG_FOLDER = "../../../home/user/FilesSpace/productFiles/";
//    private String IMG_FOLDER = "../../FilesSpace/images/";


    public UserPage(Integer usersID) {

        readUser(usersID);
    }

    public UserPage() {

        readUser(1);
    }

    private void readUser(Integer usersID){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Users user =
                    (Users) session.get(Users.class, usersID);
            addComponents(user);
            session.flush();
            session.clear();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();

        }
    }


    private void addComponents(final Users user) {
        Link link = new Link("mainPage") {
            private static final long serialVersionUID = -3046076558422615941L;

            @Override
            public void onClick() {
                setResponsePage(new MainPage());
            }
        };

        link.add(new Image("logo", "../../CSS/logo.png"));

        add(link);
        Form formTop = new Form("formTop");

        formTop.add(new Link("UsersListLink") {

            private static final long serialVersionUID = -6107821220040654170L;

            @Override
            public void onClick() {
                setResponsePage(new UsersList(true));
            }
        });
        formTop.add(new Link("UsersMobileListLink") {

            private static final long serialVersionUID = 3633854653413839458L;

            @Override
            public void onClick() {
                setResponsePage(new UsersMobileList(true, "secondname"));
            }
        });
        formTop.add(new Link("ProductsListLink") {

            private static final long serialVersionUID = 1113536144920154737L;

            @Override
            public void onClick() {
                setResponsePage(new ProductsList(true, "name"));
            }
        });

        add(formTop);


        Form<?> form = new Form<Object>("form");
        Fragment fragment;

        fragment = new Fragment("container", "viewFragment", this);

        fragment.add(new Label("name", new PropertyModel<String>(user, "name")));
        fragment.add(new Label("secondName", new PropertyModel<>(user, "secondname")));
        fragment.add(new Label("patr", new PropertyModel<>(user, "patr")));

        if (user.getUserimg() != null)
            fragment.add(new Image("cat", IMG_FOLDER + user.getUserimg()));
        else
            fragment.add(new Image("cat", IMG_FOLDER + "cat.png"));

        fragment.add(new Label("userConf", new PropertyModel<>(user, "userconf")));

        fragment.add(new Label("userDate", new PropertyModel<>(user, "userdate")));

        fragment.add(new Label("userPosition", new PropertyModel<>(user, "userposition")));

        fragment.add(new Label("userSub", new PropertyModel<>(user, "usersub")));

        fragment.add(new Label("userBranch", new PropertyModel<>(user, "userbranch")));
        fragment.add(new Label("userAdress", new PropertyModel<>(user, "useradress")));

        fragment.add(new Label("userOfficialMobile", new PropertyModel<>(user, "userofficialmobile")));

        fragment.add(new Label("userMobile", new PropertyModel<>(user, "usermobile")));

        fragment.add(new Label("userRole", "Администратор"));


        fragment.add(new Link("UserChangeLink") {

            private static final long serialVersionUID = -6333813484693440606L;

            @Override
            public void onClick() {
                setResponsePage(new EditUserPage(user.getId()));
            }
        });

        fragment.add(new Link("UsersListLinkBack") {

            private static final long serialVersionUID = -6107821220040654170L;

            @Override
            public void onClick() {
                setResponsePage(new UsersList(true));
            }
        });

        form.add(fragment);

        add(form);
    }
}
