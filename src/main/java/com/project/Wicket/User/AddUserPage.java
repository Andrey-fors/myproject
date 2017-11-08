package com.project.Wicket.User;

import com.project.Wicket.MainPage;
import com.project.Wicket.Product.ProductsList;
import com.project.hibernate.entity.User.Departmen;
import com.project.hibernate.entity.User.Users;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.converter.DateConverter;
import org.apache.wicket.util.lang.Bytes;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.apache.wicket.markup.html.form.upload.FileUploadField;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddUserPage extends WebPage {

    private static final long serialVersionUID = -5223126205489216801L;

    private static SessionFactory factory;
    private FileUploadField fileUpload;
        private String UPLOAD_FOLDER = "../../../home/user/FilesSpace/images/";
//    private String UPLOAD_FOLDER = "C:\\Users\\andrey.kirgizbaev\\IdeaProjects\\userCard\\src\\main\\java\\com\\project\\FilesSpace\\images\\";

    public AddUserPage() {

        addComponents();
    }

    private void addComponents() {
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

            private static final long serialVersionUID = -2466276015602992677L;

            @Override
            public void onClick() {
                setResponsePage(new UsersList(true));
            }
        });
        formTop.add(new Link("UsersMobileListLink") {

            private static final long serialVersionUID = -174032356046281915L;

            @Override
            public void onClick() {
                setResponsePage(new UsersMobileList(true, "secondname"));
            }
        });
        formTop.add(new Link("ProductsListLink") {

            private static final long serialVersionUID = -1533022685759715667L;

            @Override
            public void onClick() {
                setResponsePage(new ProductsList(true, "name"));
            }
        });

        add(formTop);

        Form<?> form = new Form<Object>("form");


        form.add(new Label("FIO", new Model<>("ФИО")));
        final Model<String> userName = new Model<>();
        final TextField textFieldName = new TextField<>("textfieldName", userName, String.class);
        form.add(textFieldName);
        final Model<String> userSecondName = new Model<>();
        final TextField textFieldSecondName = new TextField<>("textfieldSecondName", userSecondName, String.class);
        form.add(textFieldSecondName);
        final Model<String> userPatr = new Model<>();
        final TextField textFieldPatr = new TextField<>("textfieldPatr", userPatr, String.class);
        form.add(textFieldPatr);

        form.setMultiPart(true);
        form.setMaxSize(Bytes.megabytes(100));
        form.add(fileUpload = new FileUploadField("fileUpload"));

        form.add(new Label("user", new Model<>("Логин")));
        final Model<String> userConf = new Model<>();
        final EmailTextField textFieldUserConf = new EmailTextField("textfieldUserConf", userConf);
        form.add(textFieldUserConf);

        form.add(new Label("date", new Model<>("Дата приема на работу")));
        final Model<String> userDate = new Model<>();
        final TextField dateTextField = new TextField("dateTextField", userDate, String.class);
        form.add(dateTextField);

        form.add(new Label("position", new Model<>("Должность")));
        Departmen subdivision = new Departmen();
        final Model<String> userPosition = new Model<>();
        List<String> subChoicesPosition = subdivision.returnPosition();
        DropDownChoice<String> position = new DropDownChoice<>("choicePosition", userPosition, subChoicesPosition);
        form.add(position);

        form.add(new Label("department", new Model<>("Подразделение")));
        final Model<String> userSub = new Model<>();
        List<String> subChoicesSub = subdivision.returnSubdivision();
        DropDownChoice<String> sub = new DropDownChoice<>("choiceSub", userSub, subChoicesSub);
        form.add(sub);

        form.add(new Label("branch", new Model<>("Компания")));
        final Model<String> userBranch = new Model<>();
        List<String> subChoicesBranch = subdivision.returnBranch();
        DropDownChoice<String> branch = new DropDownChoice<>("choiceBranch", userBranch, subChoicesBranch);
        form.add(branch);
        form.add(new Label("adress", new Model<>("Территория")));
        final Model<String> userAdress = new Model<>();
        List<String> subChoicesAdress = subdivision.returnAdress();
        DropDownChoice<String> adress = new DropDownChoice<>("choiceAdress", userAdress, subChoicesAdress);
        form.add(adress);

        form.add(new Label("officialMobile", new Model<>("Телефон")));
        final Model<String> userOfficialMobile = new Model<>();
        final TextField textFieldUserOfficialMobile = new TextField<>("textfieldUserOfficialMobile", userOfficialMobile, String.class);
        form.add(textFieldUserOfficialMobile);

        form.add(new Label("mobile", new Model<>("Моб. телефон")));
        final Model<String> userMobile = new Model<>();
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

        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        form.add(feedbackPanel);

        form.add(new Button("submitSave") {
            private static final long serialVersionUID = -7424012154139108909L;

            public void onSubmit() {

                if (userName.getObject() == null || userSecondName.getObject() == null || userAdress.getObject() == null
                        || userConf.getObject() == null || userSub.getObject() == null
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
                    if (userSub.getObject() == null) {
                        info("Поле Подразделение не может быть пустым");
                    }
                    if (userAdress.getObject() == null) {
                        info("Поле Территория не может быть пустым");
                    }
                } else {
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

                        Users users = new Users();
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

                        session.save(users);
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

                    setResponsePage(new UsersList(true));
                }
            }
        });
        form.add(new Link("UsersListLinkBack") {

            private static final long serialVersionUID = -6107821220040654170L;

            @Override
            public void onClick() {
                setResponsePage(new UsersList(true));
            }
        });
        add(form);
    }
}


