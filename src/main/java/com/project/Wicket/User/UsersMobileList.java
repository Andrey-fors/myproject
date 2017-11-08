package com.project.Wicket.User;

import com.project.Wicket.MainPage;
import com.project.Wicket.Product.ProductsList;
import com.project.hibernate.entity.User.Departmen;
import com.project.hibernate.entity.User.Users;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
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
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsersMobileList extends WebPage {
    private static final long serialVersionUID = -9040165063498817279L;

    private static SessionFactory factory;


    public UsersMobileList(boolean sort, String parameter) {

        String propertyModel1 = null;
        String value1 = null;
        String propertyModel2 = null;
        String value2 = null;
        String propertyModel3 = null;
        String value3 = null;

        ArrayList list = getList(sort, parameter);

        Page(list, sort, parameter, propertyModel1, value1, propertyModel2, value2, propertyModel3, value3);
    }

    public UsersMobileList(String propertyModel1, String value1) {

        String parameter = null;
        String propertyModel2 = null;
        String value2 = null;
        String propertyModel3 = null;
        String value3 = null;

        ArrayList list = getList(propertyModel1, value1);

        Page(list, false, parameter, propertyModel1, value1, propertyModel2, value2, propertyModel3, value3);
    }

    public UsersMobileList(String propertyModel1, String value1, String propertyModel2, String value2) {

        String parameter = null;
        String propertyModel3 = null;
        String value3 = null;

        ArrayList list = getList(propertyModel1, value1, propertyModel2, value2);

        Page(list, false, parameter, propertyModel1, value1, propertyModel2, value2, propertyModel3, value3);
    }

    public UsersMobileList(String propertyModel1, String value1, String propertyModel2, String value2,
                           String propertyModel3, String value3) {

        String parameter = null;

        ArrayList list = getList(propertyModel1, value1, propertyModel2, value2, propertyModel3, value3);

        Page(list, false, parameter, propertyModel1, value1, propertyModel2, value2, propertyModel3, value3);
    }

    public ArrayList getList(boolean sort, String parameter) {
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
                cr.addOrder(Order.asc(parameter));
            else
                cr.addOrder(Order.desc(parameter));
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

    public ArrayList getList(String propertyModel, String value) {
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

    public ArrayList getList(String propertyModel1, String value1, String propertyModel2, String value2) {
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

    public ArrayList getList(String propertyModel1, String value1, String propertyModel2, String value2,
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

    public void Page(ArrayList list, final boolean sort, final String parameter, final String propertyModel1, final String value1,
                     final String propertyModel2, final String value2, final String propertyModel3, final String value3) {
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

            private static final long serialVersionUID = -3046076558422615941L;

            @Override
            public void onClick() {
                setResponsePage(new UsersList(true));
            }
        });
        formTop.add(new Link("UsersMobileListLink") {

            private static final long serialVersionUID = 5754515592939979826L;

            @Override
            public void onClick() {
                setResponsePage(new UsersMobileList(true, "secondname"));
            }
        });
        formTop.add(new Link("ProductsListLink") {

            private static final long serialVersionUID = -4915086494923514047L;

            @Override
            public void onClick() {
                setResponsePage(new ProductsList(true, "name"));
            }
        });

        add(formTop);

        Form form = new Form<>("form");

        final DataView<Users> dataView = new DataView<Users>("simple", new ListDataProvider(
                list)) {
            public void populateItem(final Item item) {
                final Users user = (Users) item.getModelObject();
                final ModalWindow modalWindowEditUserMobile;
                modalWindowEditUserMobile = new ModalWindow("modalWindowEditUserMobile");
                modalWindowEditUserMobile.setPageCreator(new ModalWindow.PageCreator() {
                    private static final long serialVersionUID = 1016582771002360861L;

                    @Override
                    public Page createPage() {
                        return new EditUserMobile(user.getId(), UsersMobileList.this.getPageReference(), modalWindowEditUserMobile);
                    }
                });

                modalWindowEditUserMobile.setTitle("Мобильный телефон");
                modalWindowEditUserMobile.setResizable(false);
                modalWindowEditUserMobile.setInitialWidth(16);
                modalWindowEditUserMobile.setInitialHeight(7);
                modalWindowEditUserMobile.setWidthUnit("em");
                modalWindowEditUserMobile.setHeightUnit("em");

                modalWindowEditUserMobile.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
                    private static final long serialVersionUID = 3009749640511658082L;

                    @Override
                    public void onClose(AjaxRequestTarget ajaxRequestTarget) {
                        if (value1 == null && value2 == null && value3 == null) {
                            modalWindowEditUserMobile.setResponsePage(new UsersMobileList(sort, parameter));
                        }
                        else if (value1 == null && value2 == null)
                            setResponsePage(new UsersMobileList(propertyModel3, value3));
                        else if (value2 == null && value3 == null) {
                            setResponsePage(new UsersMobileList(propertyModel1, value1));
                        }
                        else if (value1 == null && value3 == null)
                            setResponsePage(new UsersMobileList(propertyModel2, value2));
                        else if (value3 == null)
                            setResponsePage(new UsersMobileList(propertyModel1, value1, propertyModel2, value2));
                        else if (value2 == null)
                            setResponsePage(new UsersMobileList(propertyModel1, value1, propertyModel3, value3));
                        else if (value1 == null)
                            setResponsePage(new UsersMobileList(propertyModel2, value2, propertyModel3, value3));
                        else
                            setResponsePage(new UsersMobileList(propertyModel1, value1, propertyModel2, value2, propertyModel3, value3));
                    }
                });
                AjaxLink<String> editUserMobilePage = new AjaxLink<String>("editUserMobileLink") {
                    private static final long serialVersionUID = 759806455941671669L;

                    @Override
                    public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                        modalWindowEditUserMobile.show(ajaxRequestTarget);
                    }
                };

                if (user.getUsermobile() != null)
                    editUserMobilePage.add(new Label("userMobile", user.getUsermobile()));
                else
                    editUserMobilePage.add(new Label("userMobile", "Отсутствует"));

                item.add(editUserMobilePage);

                final ModalWindow modalWindowEditUserOfficialMobile;
                modalWindowEditUserOfficialMobile = new ModalWindow("modalWindowEditUserOfficialMobile");
                modalWindowEditUserOfficialMobile.setPageCreator(new ModalWindow.PageCreator() {
                    private static final long serialVersionUID = 1016582771002360861L;

                    @Override
                    public Page createPage() {
                        return new EditUserOfficialMobile(user.getId(), UsersMobileList.this.getPageReference(), modalWindowEditUserOfficialMobile);
                    }
                });

                modalWindowEditUserOfficialMobile.setTitle("Служебный телефон");
                modalWindowEditUserOfficialMobile.setResizable(false);
                modalWindowEditUserOfficialMobile.setInitialWidth(16);
                modalWindowEditUserOfficialMobile.setInitialHeight(7);
                modalWindowEditUserOfficialMobile.setWidthUnit("em");
                modalWindowEditUserOfficialMobile.setHeightUnit("em");

                modalWindowEditUserOfficialMobile.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
                    private static final long serialVersionUID = 3009749640511658082L;

                    @Override
                    public void onClose(AjaxRequestTarget target) {
                        if (value1 == null && value2 == null && value3 == null) {
                            modalWindowEditUserOfficialMobile.setResponsePage(new UsersMobileList(sort, parameter));
                        }
                        else if (value1 == null && value2 == null)
                            setResponsePage(new UsersMobileList(propertyModel3, value3));
                        else if (value2 == null && value3 == null) {
                            setResponsePage(new UsersMobileList(propertyModel1, value1));
                        }
                        else if (value1 == null && value3 == null)
                            setResponsePage(new UsersMobileList(propertyModel2, value2));
                        else if (value3 == null)
                            setResponsePage(new UsersMobileList(propertyModel1, value1, propertyModel2, value2));
                        else if (value2 == null)
                            setResponsePage(new UsersMobileList(propertyModel1, value1, propertyModel3, value3));
                        else if (value1 == null)
                            setResponsePage(new UsersMobileList(propertyModel2, value2, propertyModel3, value3));
                        else
                            setResponsePage(new UsersMobileList(propertyModel1, value1, propertyModel2, value2, propertyModel3, value3));

                    }
                });

                AjaxLink<String> editUserOfficialMobilePage = new AjaxLink<String>("editUserOfficialMobileLink") {
                    private static final long serialVersionUID = 759806455941671669L;

                    @Override
                    public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                        modalWindowEditUserOfficialMobile.show(ajaxRequestTarget);
                    }
                };

                if (user.getUserofficialmobile() != null)
                    editUserOfficialMobilePage.add(new Label("userOfficialMobile", user.getUserofficialmobile()));
                else
                    editUserOfficialMobilePage.add(new Label("userOfficialMobile", "Отсутствует"));

                item.add(editUserOfficialMobilePage);

                item.add(modalWindowEditUserMobile);
                item.add(modalWindowEditUserOfficialMobile);
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

        Form formSearch = new Form<>("search");

        String secondname = null;
        if (propertyModel1 == "secondname") {
            secondname = value1;
        }
        final Model<String> userSecondname = new Model<String>(secondname);
        final TextField textFieldUserSecondname = new TextField<>("textfieldUserSecondname", userSecondname, String.class);
        formSearch.add(textFieldUserSecondname);

        Departmen subdivision = new Departmen();

        String userposition = null;
        if (propertyModel1 == "userposition") {
            userposition = value1;
        } else if (propertyModel2 == "userposition") {
            userposition = value2;
        } else if (propertyModel3 == "userposition") {
            userposition = value3;
        }
        final Model<String> userPosition = new Model<String>(userposition);
        List<String> subChoicesPosition = subdivision.returnPosition();
        final DropDownChoice<String> position = new DropDownChoice<String>("choicePosition", userPosition, subChoicesPosition);
        formSearch.add(position);

        String usersub = null;
        if (propertyModel1 == "usersub") {
            usersub = value1;
        } else if (propertyModel2 == "usersub") {
            usersub = value2;
        } else if (propertyModel3 == "usersub") {
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
                setResponsePage(new UsersMobileList(true, "secondname"));
            }
        });

        formSearch.add(new Button("search") {
            private static final long serialVersionUID = 2285212311331663536L;

            @Override
            public void onSubmit() {
                if (userSecondname.getObject() == null && userPosition.getObject() == null && userSub.getObject() == null)
                    setResponsePage(new UsersMobileList(true, "secondname"));
                else if (userPosition.getObject() == null && userSub.getObject() == null)
                    setResponsePage(new UsersMobileList("secondname", userSecondname.getObject()));
                else if (userSecondname.getObject() == null && userSub.getObject() == null)
                    setResponsePage(new UsersMobileList("userposition", userPosition.getObject()));
                else if (userSecondname.getObject() == null && userPosition.getObject() == null)
                    setResponsePage(new UsersMobileList("usersub", userSub.getObject()));
                else if (userSub.getObject() == null)
                    setResponsePage(new UsersMobileList("secondname", userSecondname.getObject(), "userposition", userPosition.getObject()));
                else if (userPosition.getObject() == null)
                    setResponsePage(new UsersMobileList("secondname", userSecondname.getObject(), "usersub", userSub.getObject()));
                else if (userSecondname.getObject() == null)
                    setResponsePage(new UsersMobileList("userposition", userPosition.getObject(), "usersub", userSub.getObject()));
                else
                    setResponsePage(new UsersMobileList("secondname", userSecondname.getObject(),
                            "usersub", userSub.getObject(), "userposition", userPosition.getObject()));

            }
        });

        form.add(new Link("sort") {
            private static final long serialVersionUID = -8247067492640051612L;

            @Override
            public void onClick() {
                if (sort)
                    setResponsePage(new UsersMobileList(false, "secondname"));
                else
                    setResponsePage(new UsersMobileList(true, "secondname"));
            }
        });

        form.add(new Link("sortSub") {
            private static final long serialVersionUID = -8247067492640051612L;

            @Override
            public void onClick() {
                if (!sort)
                    setResponsePage(new UsersMobileList(false, "usersub"));
                else
                    setResponsePage(new UsersMobileList(true, "usersub"));
            }
        });

        form.add(dataView);
        form.add(formSearch);

        add(form);

    }
}
