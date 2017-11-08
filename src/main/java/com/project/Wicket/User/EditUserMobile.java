package com.project.Wicket.User;

import com.project.hibernate.entity.User.Users;
import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.Model;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EditUserMobile extends WebPage {
    private static final long serialVersionUID = 6513312942427429045L;
    private static SessionFactory factory;

    public EditUserMobile(final Integer usersID, final PageReference usersMobileList, final ModalWindow window) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Users user =
                    (Users) session.get(Users.class, usersID);
            addComponents(user, usersID, usersMobileList, window);
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

    private void addComponents(final Users user, final Integer usersID, final PageReference usersMobileList, final ModalWindow window) {

        Form<?> form = new Form<Object>("form");
        Fragment fragment;


        fragment = new Fragment("container", "editFragment", this);
        fragment.add(new Label("mobile", new Model<>("Моб. телефон")));
        final Model<String> userMobile = new Model<String>(user.getUsermobile());
        final TextField textFieldUserMobile = new TextField<>("textfieldUserMobile", userMobile, String.class);
        fragment.add(textFieldUserMobile);


        fragment.add(new AjaxButton("submitSave") {
            private static final long serialVersionUID = 517154879102108312L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form1) {
                try {
                    factory = new Configuration().configure().buildSessionFactory();
                } catch (Throwable ex) {
                    System.err.println("Failed to create sessionFactory object." + ex);
                    throw new ExceptionInInitializerError(ex);
                }
                Session session = factory.openSession();
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    Users users =
                            (Users) session.get(Users.class, usersID);
                    users.setUsermobile(userMobile.getObject());
                    session.update(users);
                    session.flush();
                    session.clear();
                    tx.commit();
                } catch (HibernateException e) {
                    if (tx != null) tx.rollback();
                    e.printStackTrace();
                } finally {
                    session.close();
                    factory.close();
                    if (usersMobileList != null)
                        window.close(target);
                }
            }
        });

        fragment.add(new AjaxLink("submitCancel") {
            private static final long serialVersionUID = -3071857519704782258L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                window.close(target);
            }
        });
        form.add(fragment);
        add(form);
    }
}