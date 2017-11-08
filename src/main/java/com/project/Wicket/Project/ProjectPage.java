package com.project.Wicket.Project;

import com.project.Wicket.MainPage;
import com.project.Wicket.Product.ProductsList;
import com.project.Wicket.User.UsersList;
import com.project.Wicket.User.UsersMobileList;
import com.project.hibernate.entity.Product.ProductsEntity;
import com.project.hibernate.entity.Product.ProductsFiles;
import com.project.hibernate.entity.Project.ProjectsEntity;
import com.project.hibernate.entity.Project.ProjectsFiles;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjectPage extends WebPage {
    private static final long serialVersionUID = -4494603815145626246L;

    private String UPLOAD_FOLDER = "../../../home/user/FilesSpace/projectFiles/";
    //    private String UPLOAD_FOLDER = "..\\..\\..\\..\\..\\..\\home\\user\\FilesSpace\\projectFiles\\";

    public ProjectPage() {
        readProject(1);
    }
    public ProjectPage(Integer projectID) {
        readProject(projectID);
    }

    private void readProject(Integer projectID) {
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        List<ProjectsFiles> list = new ArrayList<>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ProjectsEntity project =
                    (ProjectsEntity) session.get(ProjectsEntity.class, projectID);

            List files = session.createQuery("FROM ProjectsFiles").list();
            for (Iterator iterator =
                 files.iterator(); iterator.hasNext(); ) {
                ProjectsFiles projectFiles = (ProjectsFiles) iterator.next();
                list.add(projectFiles);
            }
            addComponents(project, list);
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

    private void addComponents(final ProjectsEntity project, List<ProjectsFiles> files) {
        Link link = new Link("mainPage") {

            private static final long serialVersionUID = -2466276015602992677L;

            @Override
            public void onClick() {
                setResponsePage(new MainPage());
            }
        };

        link.add(new Image("logo", "../../CSS/logo.png"));

        add(link);
        Form formTop = new Form("formTop");

        formTop.add(new Link("UsersListLink") {

            private static final long serialVersionUID = -3185682846233690144L;

            @Override
            public void onClick() {
                setResponsePage(new UsersList(true));
            }
        });
        formTop.add(new Link("UsersMobileListLink") {

            private static final long serialVersionUID = 2526202799124681772L;

            @Override
            public void onClick() {
                setResponsePage(new UsersMobileList(true, "secondname"));
            }
        });
        formTop.add(new Link("ProductsListLink") {

            private static final long serialVersionUID = -5706398235525958099L;

            @Override
            public void onClick() {
                setResponsePage(new ProductsList(true, "name"));
            }
        });

        add(formTop);
        Form<?> form = new Form<Object>("form");
        List tabs=new ArrayList();
        tabs.add(new AbstractTab(new Model<String>("Основное")) {
            private static final long serialVersionUID = -6913962363073141143L;

            public Panel getPanel(String panelId) { return new TabPanel1(panelId, project); }
        });
        tabs.add(new AbstractTab(new Model<String>("Маркетинг")) {
            private static final long serialVersionUID = -621135287129847306L;

            public Panel getPanel(String panelId) { return new TabPanel2(panelId); }
        });
        tabs.add(new AbstractTab(new Model<String>("Раб. материалы")) {
            private static final long serialVersionUID = -621135287129847306L;

            public Panel getPanel(String panelId) { return new TabPanel3(panelId); }
        });
        tabs.add(new AbstractTab(new Model<String>("НСИ")) {
            private static final long serialVersionUID = -621135287129847306L;

            public Panel getPanel(String panelId) { return new TabPanel4(panelId); }
        });
        tabs.add(new AbstractTab(new Model<String>("Контракт")) {
            private static final long serialVersionUID = -621135287129847306L;

            public Panel getPanel(String panelId) { return new TabPanel5(panelId); }
        });
        tabs.add(new AbstractTab(new Model<String>("Материалы от заказчика")) {
            private static final long serialVersionUID = -621135287129847306L;

            public Panel getPanel(String panelId) { return new TabPanel6(panelId); }
        });

        form.add(new Link("closeButton") {

            private static final long serialVersionUID = -5939848943299426485L;

            @Override
            public void onClick() {
                setResponsePage(new MainPage());
            }
        });

        form.add(new TabbedPanel("tabs", tabs));
        add(form);
    }

    private static class TabPanel1 extends Panel
    {
        private static final long serialVersionUID = -5329491619084096666L;

        public TabPanel1(String id, ProjectsEntity project)
        {
            super(id);
            Form<?> mainForm = new Form<Object>("mainForm");
            Form<?> formGeneralInformation = new Form<Object>("formGeneralInformation");
            formGeneralInformation.add(new Label("name", new Model<>("Наименование проекта")));
            formGeneralInformation.add(new Label("projectName", new PropertyModel<String>(project, "projectname")));

            formGeneralInformation.add(new Label("cipher", new Model<>("Шифр проекта")));
            formGeneralInformation.add(new Label("projectCipher", new PropertyModel<String>(project, "projectcipher")));

            formGeneralInformation.add(new Label("customer", new Model<>("Заказчик")));
            formGeneralInformation.add(new Label("projectCustomer", new PropertyModel<String>(project, "projectcustomer")));

            formGeneralInformation.add(new Label("type", new Model<>("Тип проекта")));
            formGeneralInformation.add(new Label("projectType", new PropertyModel<String>(project, "projecttype")));

            formGeneralInformation.add(new Label("applicationArea", new Model<>("Область приминения")));
            formGeneralInformation.add(new Label("projectApplicationArea", new PropertyModel<String>(project, "projectapplicationarea")));

            formGeneralInformation.add(new Label("target", new Model<>("Цели")));
            formGeneralInformation.add(new Label("projectTarget", new PropertyModel<String>(project, "projecttarget")));

            formGeneralInformation.add(new Label("dataStart", new Model<>("Дата начала и завершения")));
            formGeneralInformation.add(new Label("projectDataStart", new PropertyModel<String>(project, "projectdatastart")));
            formGeneralInformation.add(new Label("projectDataEnd", new PropertyModel<String>(project, "projectdataend")));
            formGeneralInformation.add(new AjaxLink("changeGeneralInformation") {
                private static final long serialVersionUID = 5672977798816574517L;

                @Override
                public void onClick(AjaxRequestTarget ajaxRequestTarget) {

                }
            });
            mainForm.add(formGeneralInformation);

            Form<?> formTechnologies = new Form<Object>("formTechnologies");

            formTechnologies.add(new Label("technologicalType", new Model<>("Технологический тип проекта")));
            formTechnologies.add(new Label("projectTechnologicalType", new PropertyModel<String>(project, "projecttechnologicaltype")));

            formTechnologies.add(new Label("technologicalResults", new Model<>("Основные технологические результаты работ")));
            formTechnologies.add(new Label("projectTechnologicalResults", new PropertyModel<String>(project, "projecttechnologicalresults")));

            formTechnologies.add(new Label("centralization", new Model<>("Централизация")));
            formTechnologies.add(new Label("projectCentralization", new PropertyModel<String>(project, "projectcentralization")));

            formTechnologies.add(new Label("architecture", new Model<>("Архитектура")));
            formTechnologies.add(new Label("projectArchitecture", new PropertyModel<String>(project, "projectarchitecture")));

            formTechnologies.add(new Label("languages", new Model<>("Язык программирования, моделирования")));
            formTechnologies.add(new Label("projectLanguages", new PropertyModel<String>(project, "projectlanguages")));

            formTechnologies.add(new Label("database", new Model<>("СУБД")));
            formTechnologies.add(new Label("projectDatabase", new PropertyModel<String>(project, "projectdatabase")));

            formTechnologies.add(new Label("logic", new Model<>("Средства реализации логики приложений")));
            formTechnologies.add(new Label("projectLogic", new PropertyModel<String>(project, "projectlogic")));

            formTechnologies.add(new Label("tools", new Model<>("Прочие инструментальные средства")));
            formTechnologies.add(new Label("projectTools", new PropertyModel<String>(project, "projecttools")));

            formTechnologies.add(new Label("informationInteraction", new Model<>("Информационное взаимодействие с другими ИС")));
            formTechnologies.add(new Label("projectInformationInteraction", new PropertyModel<String>(project, "projectinformationinteraction")));

            formTechnologies.add(new Label("encryptionTools", new Model<>("Использование средств криптозащиты")));
            formTechnologies.add(new Label("projectEncryptionTools", new PropertyModel<String>(project, "projectencryptiontools")));

            formTechnologies.add(new Label("hardwarePlatform", new Model<>("Работа с персональными данными")));
            formTechnologies.add(new Label("projectHardwarePlatform", new PropertyModel<String>(project, "projecthardwareplatform")));

            formTechnologies.add(new Label("scalability", new Model<>("Возможность масштабирования")));
            formTechnologies.add(new Label("projectScalability", new PropertyModel<String>(project, "projectscalability")));

            formTechnologies.add(new Label("replicate", new Model<>("Возможность тиражирования")));
            formTechnologies.add(new Label("projectReplicate", new PropertyModel<String>(project, "projectreplicate")));

            formTechnologies.add(new AjaxLink("changeTechnologies") {

                private static final long serialVersionUID = 8047140194246984862L;

                @Override
                public void onClick(AjaxRequestTarget ajaxRequestTarget) {

                }
            });

            mainForm.add(formTechnologies);

            add(mainForm);
        }

    }
    private static class TabPanel2 extends Panel
    {
        private static final long serialVersionUID = -7095139052892621825L;

        public TabPanel2(String id)
        {
            super(id);
            Form<?> form = new Form<Object>("form");
            form.add(new AjaxLink("download"){

                private static final long serialVersionUID = 6739376242135521358L;

                @Override
                public void onClick(AjaxRequestTarget ajaxRequestTarget) {

                }
            });

            add(form);

        }

    }
    private static class TabPanel3 extends Panel
    {
        private static final long serialVersionUID = -7115376680951550515L;

        public TabPanel3(String id)
        {
            super(id);

        }

    }
    private static class TabPanel4 extends Panel
    {
        private static final long serialVersionUID = 6361361961965456894L;

        public TabPanel4(String id)
        {
            super(id);

        }

    }
    private static class TabPanel5 extends Panel
    {
        private static final long serialVersionUID = 8029599985330644260L;

        public TabPanel5(String id)
        {
            super(id);

        }

    }
    private static class TabPanel6 extends Panel
    {
        private static final long serialVersionUID = 3033133747364712827L;

        public TabPanel6(String id)
        {
            super(id);

        }

    }
}
