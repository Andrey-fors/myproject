package com.project.Wicket.User;

import com.project.Wicket.MainPage;
import com.project.Wicket.Product.ProductsList;
import com.project.hibernate.entity.User.Departmen;
import com.project.hibernate.entity.User.Users;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigatorLabel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class UsersList extends WebPage {
    private static final long serialVersionUID = 7374348576582339245L;
    private static SessionFactory factory;

    public UsersList(boolean sort) {

        String propertyModel1 = "";
        String value1 = "";
        String propertyModel2 = "";
        String value2 = "";
        String propertyModel3 = "";
        String value3 = "";

        ArrayList list = getUsersList(sort);

        Page(list, sort, propertyModel1, value1, propertyModel2, value2, propertyModel3, value3);
    }

    public UsersList(String propertyModel1, String value1) {

        String propertyModel2 = "";
        String value2 = "";
        String propertyModel3 = "";
        String value3 = "";

        ArrayList list = getUsersList(propertyModel1, value1);

        Page(list, false, propertyModel1, value1, propertyModel2, value2, propertyModel3, value3);
    }

    public UsersList(String propertyModel1, String value1, String propertyModel2, String value2) {

        String propertyModel3 = "";
        String value3 = "";

        ArrayList list = getUsersList(propertyModel1, value1, propertyModel2, value2);

        Page(list, false, propertyModel1, value1, propertyModel2, value2, propertyModel3, value3);
    }

    public UsersList(String propertyModel1, String value1, String propertyModel2, String value2,
                     String propertyModel3, String value3) {

        ArrayList list = getUsersList(propertyModel1, value1, propertyModel2, value2, propertyModel3, value3);

        Page(list, false, propertyModel1, value1, propertyModel2, value2, propertyModel3, value3);
    }

    public void Page(ArrayList list, final boolean sort, String propertyModel1, String value1,
                     String propertyModel2, String value2, String propertyModel3, String value3) {

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

        Form form = new Form<>("form");

        form.add(new Link("addUserLink") {

            private static final long serialVersionUID = -5933847884424480251L;

            @Override
            public void onClick() {
                setResponsePage(new AddUserPage());
            }
        });
        final DataView<Users> dataView = new DataView<Users>("simple", new ListDataProvider(
                list)) {

            private static final long serialVersionUID = -6695851133568979831L;

            public void populateItem(final Item<Users> item) {
                final Users user = item.getModelObject();

                item.add(new Link("userPageLink") {

                    private static final long serialVersionUID = 6214102757096759504L;

                    @Override
                    public void onClick() {
                        setResponsePage(new UserPage(user.getId()));
                    }
                });
                item.add(new Label("name", user.getName()));
                item.add(new Label("secondName", user.getSecondname()));
                item.add(new Label("patr", user.getPatr()));
                item.add(new Label("userPosition", user.getUserposition()));
                item.add(new Label("userSub", user.getUsersub()));
            }
        };

        dataView.setItemsPerPage(50);

        form.add(new PagingNavigator("navigatorTop", dataView));
        form.add(new NavigatorLabel("lblTop", dataView));
        form.add(new PagingNavigator("navigatorBot", dataView));
        form.add(new NavigatorLabel("lblBot", dataView));

        form.add(dataView);

        form.add(new Link("sort") {
            private static final long serialVersionUID = -8247067492640051612L;

            @Override
            public void onClick() {
                if (sort)
                    setResponsePage(new UsersList(false));
                else
                    setResponsePage(new UsersList(true));
            }
        });

        Form formSearch = new Form<>("search");

        String secondname = null;
        if (propertyModel1 == "secondname") {
            secondname = value1;
        }
        final Model<String> userSecondname = new Model<String>(secondname);
        final TextField textFieldUserSecondname = new TextField<>("textfieldUserSecondname", userSecondname, String.class);
        formSearch.add(textFieldUserSecondname);

        String userposition = null;
        if (propertyModel1 == "userposition"){
            userposition = value1;
        }else if (propertyModel2 == "userposition"){
            userposition = value2;
        }else if (propertyModel3 == "userposition"){
            userposition = value3;
        }
        final Model<String> userPosition = new Model<String>(userposition);
        Departmen subdivision = new Departmen();
        List<String> subChoicesPosition = subdivision.returnPosition();
        final DropDownChoice<String> position = new DropDownChoice<String>("choicePosition", userPosition, subChoicesPosition);
        formSearch.add(position);

        String usersub = null;
        if (propertyModel1 == "usersub"){
            usersub = value1;
        }else if (propertyModel2 == "usersub"){
            usersub = value2;
        }else if (propertyModel3 == "usersub"){
            usersub = value3;
        }
        final Model<String> userSub = new Model<String>(usersub);
        List<String> subChoicesSub = subdivision.returnSubdivision();
        DropDownChoice<String> sub = new DropDownChoice<String>("choiceSub", userSub, subChoicesSub);
        formSearch.add(sub);

        formSearch.add(new Button("resetSearch") {

            private static final long serialVersionUID = -2883162266508015735L;

            @Override
            public void onSubmit() {
                setResponsePage(new UsersList(true));
            }
        });

        formSearch.add(new Button("searchSecondname") {
            private static final long serialVersionUID = 2285212311331663536L;

            @Override
            public void onSubmit() {
                if (userSecondname.getObject() == null && userPosition.getObject() == null && userSub.getObject() == null)
                    setResponsePage(new UsersList(true));
                else if (userPosition.getObject() == null && userSub.getObject() == null)
                    setResponsePage(new UsersList("secondname", userSecondname.getObject()));
                else if (userSecondname.getObject() == null && userSub.getObject() == null)
                    setResponsePage(new UsersList("userposition", userPosition.getObject()));
                else if (userSecondname.getObject() == null && userPosition.getObject() == null)
                    setResponsePage(new UsersList("usersub", userSub.getObject()));
                else if (userSub.getObject() == null)
                    setResponsePage(new UsersList("secondname", userSecondname.getObject(), "userposition", userPosition.getObject()));
                else if (userPosition.getObject() == null)
                    setResponsePage(new UsersList("secondname", userSecondname.getObject(), "usersub", userSub.getObject()));
                else if (userSecondname.getObject() == null)
                    setResponsePage(new UsersList("userposition", userPosition.getObject(), "usersub", userSub.getObject()));
                else
                    setResponsePage(new UsersList("secondname", userSecondname.getObject(),
                            "usersub", userSub.getObject(), "userposition", userPosition.getObject()));

            }
        });
        form.add(formSearch);


        add(form);
    }

    public ArrayList getUsersList(boolean sort) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        ArrayList list = new ArrayList();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Users.class);
            if (sort)
                cr.addOrder(Order.asc("secondname"));
            else
                cr.addOrder(Order.desc("secondname"));
            List users = cr.list();
            for (Iterator iterator =
                 users.iterator(); iterator.hasNext(); ) {
                Users usersEntity = (Users) iterator.next();
                list.add(usersEntity);
            }
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

        return list;
    }

    public ArrayList getUsersList(String propertyModel, String value) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        ArrayList list = new ArrayList();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Users.class);
            cr.add(Restrictions.ilike(propertyModel, value + "%"));
            List users = cr.list();
            for (Iterator iterator =
                 users.iterator(); iterator.hasNext(); ) {
                Users usersEntity = (Users) iterator.next();
                list.add(usersEntity);

            }
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

        return list;
    }

    public ArrayList getUsersList(String propertyModel1, String value1, String propertyModel2, String value2) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        ArrayList list = new ArrayList();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Users.class);
            Criterion first = Restrictions.ilike(propertyModel1, value1 + "%");
            Criterion second = Restrictions.ilike(propertyModel2, value2 + "%");
            LogicalExpression andExp = Restrictions.and(first, second);
            cr.add(andExp);
            List users = cr.list();
//            List users = session.createQuery("FROM Users").list();
            for (Iterator iterator =
                 users.iterator(); iterator.hasNext(); ) {
                Users usersEntity = (Users) iterator.next();
                list.add(usersEntity);

            }
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

        return list;
    }

    public ArrayList getUsersList(String propertyModel1, String value1, String propertyModel2, String value2,
                                  String propertyModel3, String value3) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        ArrayList list = new ArrayList();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Users.class);
            Criterion first = Restrictions.like(propertyModel1, value1 + "%");
            Criterion second = Restrictions.like(propertyModel2, value2 + "%");
            Criterion third = Restrictions.like(propertyModel3, value3 + "%");
            LogicalExpression and = Restrictions.and(first, second);
            LogicalExpression andExp = Restrictions.and(and, third);
            cr.add(andExp);
            List users = cr.list();
//            List users = session.createQuery("FROM Users").list();
            for (Iterator iterator =
                 users.iterator(); iterator.hasNext(); ) {
                Users usersEntity = (Users) iterator.next();
                list.add(usersEntity);

            }
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

        return list;
    }
}
