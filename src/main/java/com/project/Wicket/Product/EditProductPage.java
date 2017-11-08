package com.project.Wicket.Product;

import com.project.Wicket.MainPage;
import com.project.Wicket.User.UserPage;
import com.project.Wicket.User.UsersList;
import com.project.Wicket.User.UsersMobileList;
import com.project.hibernate.entity.Product.ProductChoices;
import com.project.hibernate.entity.Product.ProductsEntity;
import com.project.hibernate.entity.Product.ProductsFiles;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EditProductPage extends WebPage{

    private static SessionFactory factory;

    public EditProductPage(Integer productID) {
        readProduct(productID);
    }

    private void readProduct(Integer productID) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        List<ProductsFiles> list = new ArrayList<>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ProductsEntity product =
                    (ProductsEntity) session.get(ProductsEntity.class, productID);
            List files = session.createQuery("FROM ProductsFiles").list();
            for (Iterator iterator =
                 files.iterator(); iterator.hasNext(); ) {
                ProductsFiles productsFiles = (ProductsFiles) iterator.next();
                list.add(productsFiles);
            }
            addComponents(product, productID, list);
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

    private void addComponents(final ProductsEntity product, final Integer productID, List<ProductsFiles> files){
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

            private static final long serialVersionUID = -8328059625453453597L;

            @Override
            public void onClick() {
                setResponsePage(new UsersList(true));
            }
        });
        formTop.add(new Link("UsersMobileListLink") {

            private static final long serialVersionUID = -4208815660299130327L;

            @Override
            public void onClick() {
                setResponsePage(new UsersMobileList(true, "secondname"));
            }
        });
        formTop.add(new Link("ProductsListLink") {

            private static final long serialVersionUID = -2033095835403248255L;

            @Override
            public void onClick() {
                setResponsePage(new ProductsList(true, "name"));
            }
        });

        add(formTop);

        Form<?> form = new Form<Object>("form");

        form.add(new Label("name", new Model<>("Название")));

        final Model<String> productName = new Model<>(product.getName());
        final TextField textFieldProductName = new TextField<>("textfieldProductName", productName, String.class);
        form.add(textFieldProductName);

        ProductChoices productChoices = new ProductChoices();

        form.add(new Label("type", new Model<>("Тип")));

        final Model<String> productType = new Model<>(product.getType());
        List<String> productTypeChoices = productChoices.getType();
        DropDownChoice<String> productTypeChoice = new DropDownChoice<>("productTypeChoice", productType, productTypeChoices);
        form.add(productTypeChoice);

        form.add(new Label("code", new Model<>("Код")));

        final Model<String> productCode = new Model<>(product.getCode());
        final TextField textFieldProductCode = new TextField<>("textfieldProductCode", productCode, String.class);
        form.add(textFieldProductCode);

        form.add(new Label("direction", new Model<>("Область решения, направление деятельности")));

        final Model<String> productDirection = new Model<>(product.getDirection());
        List<String> productDirectionChoices = productChoices.getProductDirection();
        DropDownChoice<String> productDirectionChoice = new DropDownChoice<>("productDirectionChoice", productDirection, productDirectionChoices);
        form.add(productDirectionChoice);

        form.add(new Label("features", new Model<>("Особенности направления деятельности решения")));

        final Model<String> productFeatures = new Model<>(product.getFeatures());
        final TextArea textAreaProductFeatures = new TextArea<>("textareaProductFeatures", productFeatures);
        form.add(textAreaProductFeatures);

        form.add(new Label("customers", new Model<>("Клиенты, где используется")));

        final Model<String> productCustomers = new Model<>(product.getCustomers());
        List<String> productCustomersChoices = productChoices.getProductCustomers();
        DropDownChoice<String> productCustomersChoice = new DropDownChoice<>("productCustomersChoice", productCustomers, productCustomersChoices);
        form.add(productCustomersChoice);

        form.add(new Label("automationArea", new Model<>("Область автоматизации")));

        final Model<String> productAutomationArea = new Model<>(product.getAutomationArea());
        List<String> productAutomationAreaChoices = productChoices.getProductAutomationArea();
        DropDownChoice<String> productAutomationAreaChoice = new DropDownChoice<>("productAutomationAreaChoice", productAutomationArea, productAutomationAreaChoices);
        form.add(productAutomationAreaChoice);

        form.add(new Label("targets", new Model<>("Цель решения")));

        final Model<String> productTargets = new Model<>(product.getTargets());
        final TextArea textAreaProductTargets = new TextArea<>("textareaProductTargets", productTargets);
        form.add(textAreaProductTargets);

        form.add(new Label("tasks", new Model<>("Задачи решения, автоматизируемые процессы")));

        final Model<String> productTasks = new Model<>(product.getTasks());
        final TextArea textAreaProductTasks = new TextArea<>("textareaProductTasks", productTasks);
        form.add(textAreaProductTasks);


        form.add(new Label("technologies", new Model<>("Используемые технологии")));

        form.add(new Label("centralization", new Model<>("Централизация")));

        final Model<String> centralization = new Model<>(product.getCentralization());
        List<String> centralizationChoices = productChoices.getCentralization();
        DropDownChoice<String> centralizationChoice = new DropDownChoice<>("centralizationChoice", centralization, centralizationChoices);
        form.add(centralizationChoice);

        form.add(new Label("architecture", new Model<>("Архитектура")));

        final Model<String> architecture = new Model<>(product.getArchitecture());
        List<String> architectureChoices = productChoices.getArchitecture();
        DropDownChoice<String> architectureChoice = new DropDownChoice<>("architectureChoice", architecture, architectureChoices);
        form.add(architectureChoice);

        form.add(new Label("languages", new Model<>("Языки программирования, моделирования")));

        final Model<String> languages = new Model<>(product.getLanguages());
        List<String> languagesChoices = productChoices.getLanguages();
        DropDownChoice<String> languagesChoice = new DropDownChoice<>("languagesChoice", languages, languagesChoices);
        form.add(languagesChoice);

        form.add(new Label("database", new Model<>("СУБД")));

        final Model<String> database = new Model<>(product.getDatabase());
        List<String> databaseChoices = productChoices.getDatabase();
        DropDownChoice<String> databaseChoice = new DropDownChoice<>("databaseChoice", database, databaseChoices);
        form.add(databaseChoice);

        form.add(new Label("tools", new Model<>("Средства реализации логики приложений")));

        final Model<String> tools = new Model<>(product.getTools());
        List<String> toolsChoices = productChoices.getTools();
        DropDownChoice<String> toolsChoice = new DropDownChoice<>("toolsChoice", tools, toolsChoices);
        form.add(toolsChoice);

        form.add(new Label("encryptions", new Model<>("Использования средств криптозащиты")));

        final Model<String> encryptions = new Model<>(product.getEncryptions());
        List<String> encryptionsChoices = productChoices.getEncryptions();
        DropDownChoice<String> encryptionsChoice = new DropDownChoice<>("encryptionsChoice", encryptions, encryptionsChoices);
        form.add(encryptionsChoice);

        form.add(new Label("requirements", new Model<>("Требования к используемому оборудованию")));

        final Model<String> productRequirements = new Model<>(product.getRequirements());
        final TextArea textAreaProductRequirements = new TextArea<>("textareaProductRequirements", productRequirements);
        form.add(textAreaProductRequirements);

        form.add(new Label("integration", new Model<>("Интеграция с другими ИС")));

        final Model<String> productIntegration = new Model<>(product.getIntegration());
        final TextArea textAreaProductIntegration = new TextArea<>("textareaProductIntegration", productIntegration);
        form.add(textAreaProductIntegration);

        form.add(new Label("work", new Model<>("Работа с персональными данными")));

        final Model<String> productWork = new Model<>(product.getWork());
        List<String> productWorkChoices = productChoices.getWork();
        DropDownChoice<String> productWorkChoice = new DropDownChoice<>("productWorkChoice", productWork, productWorkChoices);
        form.add(productWorkChoice);

        form.add(new Label("number", new Model<>("Количество пользователей")));

        final Model<String> productNumber = new Model<>(product.getNumber());
        final TextField textFieldProductNumber = new TextField<>("textfieldProductNumber", productNumber, String.class);
        form.add(textFieldProductNumber);

        form.add(new Label("inclusion", new Model<>("Включения в проекты")));

        final Model<String> productInclusion = new Model<>(product.getInclusion());
        final TextArea textAreaProductInclusion = new TextArea<>("textareaProductInclusion", productInclusion);
        form.add(textAreaProductInclusion);

        form.add(new Label("documents", new Model<>("Маркетинговые документы")));

        final ModalWindow modalWindowLoadFile;
        modalWindowLoadFile = new ModalWindow("modalWindowLoadFile");
        modalWindowLoadFile.setResizable(false);
        modalWindowLoadFile.setInitialWidth(25);
        modalWindowLoadFile.setInitialHeight(10);
        modalWindowLoadFile.setWidthUnit("em");
        modalWindowLoadFile.setHeightUnit("em");
        modalWindowLoadFile.setPageCreator(new ModalWindow.PageCreator() {

            private static final long serialVersionUID = 5384234716633515439L;

            @Override
            public Page createPage() {
                return new LoadFilePage(productName.getObject(), productID, EditProductPage.this.getPageReference(), modalWindowLoadFile);
            }
        });
        modalWindowLoadFile.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {

            private static final long serialVersionUID = 3524657546550115927L;

            @Override
            public void onClose(AjaxRequestTarget target) {
                modalWindowLoadFile.setResponsePage(new EditProductPage(productID));
            }
        });

        modalWindowLoadFile.setTitle("Загрузка документа");


        form.add(new AjaxLink<String>("loadFileLink") {
            private static final long serialVersionUID = 759806455941671669L;

            @Override
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                modalWindowLoadFile.show(ajaxRequestTarget);
            }
        });
        form.add(modalWindowLoadFile);

        final DataView<ProductsFiles> dataView = new DataView<ProductsFiles>("simple", new ListDataProvider(files)) {

            public void populateItem(final Item<ProductsFiles> item) {
                final ProductsFiles files = (ProductsFiles) item.getModelObject();
                item.add(new Label("filesName", files.getName()));
            }
        };
        form.add(dataView);

        Button buttonCancel = new Button("submitCancel") {
            private static final long serialVersionUID = 4517528821719246588L;

            @Override
            public void onSubmit() {
                setResponsePage(new ProductPage(productID));
            }
        };

        form.add(buttonCancel);

        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        form.add(feedbackPanel);

        form.add(new Button("submitSave") {

            private static final long serialVersionUID = -2403228949878624947L;

            public void onSubmit() {

                if (productName.getObject() == null || productType.getObject() == null || productCode.getObject() == null
                        || productDirection.getObject() == null || productCustomers.getObject() == null
                        || productAutomationArea.getObject() == null) {
                    if (productName.getObject() == null) {
                        info("Поле Название не может быть пустым");
                    }
                    if (productType.getObject() == null) {
                        info("Поле Тип не может быть пустым");
                    }
                    if (productCode.getObject() == null) {
                        info("Поле Код не может быть пустым");
                    }
                    if (productDirection.getObject() == null) {
                        info("Поле Область решения, направление деятельности не может быть пустым");
                    }
                    if (productCustomers.getObject() == null) {
                        info("Поле Клиенты, где используется не может быть пустым");
                    }
                    if (productAutomationArea.getObject() == null) {
                        info("Поле Область автоматизации не может быть пустым");
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
                        ProductsEntity product =
                                (ProductsEntity) session.get(ProductsEntity.class, productID);
                        product.setName(productName.getObject());
                        product.setType(productType.getObject());
                        product.setCode(productCode.getObject());
                        product.setDirection(productDirection.getObject());
                        product.setFeatures(productFeatures.getObject());
                        product.setCustomers(productCustomers.getObject());
                        product.setAutomationArea(productAutomationArea.getObject());
                        product.setTargets(productTargets.getObject());
                        product.setTasks(productTasks.getObject());
                        product.setCentralization(centralization.getObject());
                        product.setArchitecture(architecture.getObject());
                        product.setLanguages(languages.getObject());
                        product.setDatabase(database.getObject());
                        product.setTools(tools.getObject());
                        product.setEncryptions(encryptions.getObject());
                        product.setRequirements(productRequirements.getObject());
                        product.setIntegration(productIntegration.getObject());
                        product.setWork(productWork.getObject());
                        product.setNumber(productNumber.getObject());
                        product.setInclusion(productInclusion.getObject());
                        session.update(product);
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
                    setResponsePage(new ProductPage(productID));
                }
            }
        });

        add(form);
    }

}
