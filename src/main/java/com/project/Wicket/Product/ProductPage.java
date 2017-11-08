package com.project.Wicket.Product;

import com.project.Wicket.MainPage;
import com.project.Wicket.User.UsersList;
import com.project.Wicket.User.UsersMobileList;
import com.project.hibernate.entity.Product.ProductsEntity;
import com.project.hibernate.entity.Product.ProductsFiles;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
//import org.apache.wicket.util.file.File;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.time.Duration;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductPage extends WebPage {

    private static final long serialVersionUID = 7969892664952974201L;

    private static SessionFactory factory;
    private String UPLOAD_FOLDER = "../../../home/user/FilesSpace/productFiles/";
//    private String UPLOAD_FOLDER = "..\\..\\..\\..\\..\\..\\home\\user\\FilesSpace\\productFiles\\";

    public ProductPage() {

        readProduct(1);

    }

    public ProductPage(Integer productID) {

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
            addComponents(product, list);
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

    private void addComponents(final ProductsEntity product, List<ProductsFiles> files) {
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

        Fragment fragment;

        fragment = new Fragment("container", "viewFragment", this);

        fragment.add(new Label("name", new Model<>("Название")));

        fragment.add(new Label("productName", new PropertyModel<String>(product, "name")));

        fragment.add(new Label("type", new Model<>("Тип")));

        fragment.add(new Label("productType", new PropertyModel<String>(product, "type")));

        fragment.add(new Label("code", new Model<>("Код")));

        fragment.add(new Label("productCode", new PropertyModel<String>(product, "code")));

        fragment.add(new Label("direction", new Model<>("Область решения, направление деятельности")));

        fragment.add(new Label("productDirection", new PropertyModel<String>(product, "direction")));

        fragment.add(new Label("features", new Model<>("Особенности направления деятельности решения")));

        fragment.add(new Label("productFeatures", new PropertyModel<String>(product, "features")));

        fragment.add(new Label("customers", new Model<>("Клиенты, где используется")));

        fragment.add(new Label("productCustomers", new PropertyModel<String>(product, "customers")));

        fragment.add(new Label("automationArea", new Model<>("Область автоматизации")));

        fragment.add(new Label("productAutomationArea", new PropertyModel<String>(product, "automationarea")));

        fragment.add(new Label("targets", new Model<>("Цель решения")));

        fragment.add(new Label("productTargets", new PropertyModel<String>(product, "targets")));

        fragment.add(new Label("tasks", new Model<>("Задачи решения, автоматизируемые процессы")));

        fragment.add(new Label("productTasks", new PropertyModel<String>(product, "tasks")));


        fragment.add(new Label("technologies", new Model<>("Используемые технологии")));

        fragment.add(new Label("centralization", new Model<>("Централизация")));

        fragment.add(new Label("productCentralization", new PropertyModel<String>(product, "centralization")));

        fragment.add(new Label("architecture", new Model<>("Архитектура")));

        fragment.add(new Label("productArchitecture", new PropertyModel<String>(product, "architecture")));

        fragment.add(new Label("languages", new Model<>("Языки программирования, моделирования")));

        fragment.add(new Label("productLanguages", new PropertyModel<String>(product, "languages")));

        fragment.add(new Label("database", new Model<>("СУБД")));

        fragment.add(new Label("productDatabase", new PropertyModel<String>(product, "database")));

        fragment.add(new Label("tools", new Model<>("Средства реализации логики приложений")));

        fragment.add(new Label("productTools", new PropertyModel<String>(product, "tools")));

        fragment.add(new Label("encryptions", new Model<>("Использования средств криптозащиты")));

        fragment.add(new Label("productEncryptions", new PropertyModel<String>(product, "encryptions")));

        fragment.add(new Label("requirements", new Model<>("Требования к используемому оборудованию")));

        fragment.add(new Label("productRequirements", new PropertyModel<String>(product, "requirements")));

        fragment.add(new Label("integration", new Model<>("Интеграция с другими ИС")));

        fragment.add(new Label("productIntegration", new PropertyModel<String>(product, "integration")));

        fragment.add(new Label("work", new Model<>("Работа с персональными данными")));

        fragment.add(new Label("productWork", new PropertyModel<String>(product, "work")));

        fragment.add(new Label("number", new Model<>("Количество пользователей")));

        fragment.add(new Label("productNumber", new PropertyModel<String>(product, "number")));

        fragment.add(new Label("inclusion", new Model<>("Включения в проекты")));

        fragment.add(new Label("productInclusion", new PropertyModel<String>(product, "inclusion")));

        fragment.add(new Label("documents", new Model<>("Маркетинговые документы")));


        final DataView<ProductsFiles> dataView = new DataView<ProductsFiles>("simple", new ListDataProvider(files)) {

            public void populateItem(final Item<ProductsFiles> item) {
                final ProductsFiles files = (ProductsFiles) item.getModelObject();


                DownloadLink downloadLink = new DownloadLink("downloadLink", new AbstractReadOnlyModel<File>() {

                    private static final long serialVersionUID = 4779583807948848366L;

                    @Override
                    public File getObject() {
                        File tempFile = new File(UPLOAD_FOLDER + product.getName(), files.getName());
                        return tempFile;
                    }
                }, files.getName());

                downloadLink.add(new Label("filesName", files.getName()));

                item.add(downloadLink);
            }
        };
        fragment.add(dataView);

        fragment.add(new Link("UserChangeLink") {


            private static final long serialVersionUID = -6333813484693440606L;

            @Override
            public void onClick() {
                setResponsePage(new EditProductPage(product.getId()));
            }
        });

        fragment.add(new Link("UsersListLinkBack") {

            private static final long serialVersionUID = -6107821220040654170L;

            @Override
            public void onClick() {
                setResponsePage(new ProductsList(true, "name"));
            }
        });

        form.add(fragment);

        add(form);
    }

}
