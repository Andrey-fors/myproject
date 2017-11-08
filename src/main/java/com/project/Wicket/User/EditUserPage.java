package com.project.Wicket.User;

import com.project.Wicket.MainPage;
import com.project.Wicket.Product.ProductsList;
import com.project.hibernate.entity.User.Departmen;
import com.project.hibernate.entity.User.Users;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Bytes;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditUserPage extends WebPage {

    private static final long serialVersionUID = -5223126205489216801L;
    public static void  load(IHeaderResponse response){

    };
    private static SessionFactory factory;

    private FileUploadField fileUpload;
    private String UPLOAD_FOLDER = "../../../home/user/FilesSpace/images/";
//    private String UPLOAD_FOLDER = "C:\\Users\\andrey.kirgizbaev\\IdeaProjects\\userCard\\src\\main\\java\\com\\project\\FilesSpace\\images\\";

    public EditUserPage(final Integer usersID) {

        readUser(usersID);
    }

    private void readUser(Integer usersID) {
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
            addComponents(user, usersID);
            session.flush();
            session.clear();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void addComponents(final Users user, final Integer usersID) {
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

            private static final long serialVersionUID = 5607007552053347878L;

            @Override
            public void onClick() {
                setResponsePage(new UsersList(true));
            }
        });
        formTop.add(new Link("UsersMobileListLink") {

            private static final long serialVersionUID = -8961193664029537309L;

            @Override
            public void onClick() {
                setResponsePage(new UsersMobileList(true, "secondname"));
            }
        });
        formTop.add(new Link("ProductsListLink") {

            private static final long serialVersionUID = 2259349014615494859L;

            @Override
            public void onClick() {
                setResponsePage(new ProductsList(true, "name"));
            }
        });

        add(formTop);


        Form<?> form = new Form<Object>("form");

        form.add(new Label("FIO", new Model<>("ФИО")));
        final Model<String> userName = new Model<String>(user.getName());
        final TextField textFieldName = new TextField<>("textfieldName", userName, String.class);
        form.add(textFieldName);
        final Model<String> userSecondName = new Model<String>(user.getSecondname());
        final TextField textFieldSecondName = new TextField<>("textfieldSecondName", userSecondName, String.class);
        form.add(textFieldSecondName);
        final Model<String> userPatr = new Model<String>(user.getPatr());
        final TextField textFieldPatr = new TextField<>("textfieldPatr", userPatr, String.class);
        form.add(textFieldPatr);

        form.setMultiPart(true);
        form.setMaxSize(Bytes.megabytes(100));
        form.add(fileUpload = new FileUploadField("fileUpload"));

        form.add(new Label("user", new Model<>("Логин")));
        final Model<String> userConf = new Model<String>(user.getUserconf());
        final EmailTextField textFieldUserConf = new EmailTextField("textfieldUserConf", userConf);
        form.add(textFieldUserConf);

        form.add(new Label("date", new Model<>("Дата приема на работу")));
        final Model<String> userDate = new Model<String>(user.getUserdate());
        final TextField dateTextField = new TextField("dateTextField", userDate, String.class);
        form.add(dateTextField);

        form.add(new Label("position", new Model<>("Должность")));
        Departmen subdivision = new Departmen();
        final Model<String> userPosition = new Model<String>(user.getUserposition());
        List<String> subChoicesPosition = subdivision.returnPosition();
        DropDownChoice<String> position = new DropDownChoice<String>("choicePosition", userPosition, subChoicesPosition);
        form.add(position);

        form.add(new Label("department", new Model<>("Подразделение")));
        final Model<String> userSub = new Model<String>(user.getUsersub());
        List<String> subChoicesSub = subdivision.returnSubdivision();
        DropDownChoice<String> sub = new DropDownChoice<String>("choiceSub", userSub, subChoicesSub);
        form.add(sub);

        form.add(new Label("branch", new Model<>("Компания")));
        final Model<String> userBranch = new Model<String>(user.getUserbranch());
        List<String> subChoicesBranch = subdivision.returnBranch();
        DropDownChoice<String> branch = new DropDownChoice<String>("choiceBranch", userBranch, subChoicesBranch);
        form.add(branch);

        form.add(new Label("adress", new Model<>("Территория")));
        final Model<String> userAdress = new Model<String>(user.getUseradress());
        List<String> subChoicesAdress = subdivision.returnAdress();
        DropDownChoice<String> adress = new DropDownChoice<String>("choiceAdress", userAdress, subChoicesAdress);
        form.add(adress);

        form.add(new Label("officialMobile", new Model<>("Телефон")));
        final Model<String> userOfficialMobile = new Model<String>(user.getUserofficialmobile());
        final TextField textFieldUserOfficialMobile = new TextField<>("textfieldUserOfficialMobile", userOfficialMobile, String.class);
        form.add(textFieldUserOfficialMobile);

        form.add(new Label("mobile", new Model<>("Моб. телефон")));
        final Model<String> userMobile = new Model<String>(user.getUsermobile());
        final TextField textFieldUserMobile = new TextField<>("textfieldUserMobile", userMobile, String.class);
        form.add(textFieldUserMobile);

        form.add(new Label("role", new Model<>("Роль(права доступа)")));
        final Model<String> userRole = new Model<String>("Администратор");
        List<String> roleChoices = new ArrayList<>();
        roleChoices.add("Администратор");
//        roleChoices.add("Секретарь");
//        roleChoices.add("Пользователь");
        DropDownChoice<String> role = new DropDownChoice<String>("dropDownRole", userRole, roleChoices);
        form.add(role);


        form.add(new Link("UserPageCancel") {

            private static final long serialVersionUID = 1160520500998900196L;

            @Override
            public void onClick() {
                setResponsePage(new UserPage(usersID));
            }
        });
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        form.add(feedbackPanel);
        form.add(new Button("submitSave") {
            public void onSubmit() {

                if (userName.getObject() == null || userSecondName.getObject() == null || userAdress.getObject() == null
                        || userConf.getObject() == null || user.getUsersub() == null
                        || userPosition.getObject() == null) {
                    if (userName.getObject() == null) {
                        info("Поле Имя не может быть пустым");
                    }
                    if (userSecondName.getObject() == null) {
                        info("Поле Фамилия не может быть пустым");
                    }
                    if (userConf.getObject() == null) {
                        info("Поле Учетная запись не может быть пустым");
                    }
                    if (userPosition.getObject() == null) {
                        info("Поле Должность не может быть пустым");
                    }
                    if (userAdress.getObject() == null) {
                        info("Поле Адресс не может быть пустым");
                    }
                } else {
                    Session session = factory.openSession();
                    Transaction tx = null;
                    try {
                        tx = session.beginTransaction();
                        Users users =
                                (Users) session.get(Users.class, usersID);
                        users.setName(userName.getObject());
                        users.setPatr(userPatr.getObject());
                        users.setSecondname(userSecondName.getObject());
                        users.setUseradress(userAdress.getObject());
                        users.setUserconf(userConf.getObject());
                        users.setUserdate(userDate.getObject());
                        users.setUsermobile(userMobile.getObject());
                        users.setUserofficialmobile(userOfficialMobile.getObject());
                        users.setUserposition(userPosition.getObject());
                        users.setUsersub(userSub.getObject());
                        users.setUserbranch(userBranch.getObject());

                        final FileUpload uploadedFile = fileUpload.getFileUpload();
                        if (uploadedFile != null) {

                            // write to a new file
                            File newFile = new File(UPLOAD_FOLDER
                                    + uploadedFile.getClientFileName());

                            boolean deleted, created;
                            if (newFile.exists()) {
                                deleted = newFile.delete();
                            }

                            try {
                                created = newFile.createNewFile();
                                uploadedFile.writeTo(newFile);
                                users.setUserimg(uploadedFile.getClientFileName());
                            } catch (Exception error) {
                                throw new IllegalStateException("Error");
                            }
                        }
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
                    }
                    setResponsePage(new UserPage(usersID));
                }
            }
        });

        add(form);
    }
}
