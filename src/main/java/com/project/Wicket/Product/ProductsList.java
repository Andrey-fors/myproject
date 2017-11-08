package com.project.Wicket.Product;

import com.project.Wicket.MainPage;
import com.project.Wicket.User.UserPage;
import com.project.Wicket.User.UsersList;
import com.project.Wicket.User.UsersMobileList;
import com.project.hibernate.entity.Product.ProductChoices;
import com.project.hibernate.entity.Product.ProductsEntity;
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
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductsList extends WebPage {

    private static final long serialVersionUID = -5664695236765028264L;

    private static SessionFactory factory;

    public ProductsList(boolean sort, String parameter) {

        String propertyModel1 = null;
        String value1 = null;
        String propertyModel2 = null;
        String value2 = null;
        String propertyModel3 = null;
        String value3 = null;
        String propertyModel4 = null;
        String value4 = null;
        String propertyModel5 = null;
        String value5 = null;

        ArrayList list = getList(sort, parameter);

        Page(list, sort, parameter, propertyModel1, value1, propertyModel2, value2, propertyModel3, value3, propertyModel4, value4, propertyModel5, value5);
    }

    public ProductsList(String propertyModel1, String value1) {

        String parameter = null;
        String propertyModel2 = null;
        String value2 = null;
        String propertyModel3 = null;
        String value3 = null;
        String propertyModel4 = null;
        String value4 = null;
        String propertyModel5 = null;
        String value5 = null;

        ArrayList list = getList(propertyModel1, value1);

        Page(list, false, parameter, propertyModel1, value1, propertyModel2, value2, propertyModel3, value3, propertyModel4, value4, propertyModel5, value5);
    }

    public ProductsList(String propertyModel1, String value1, String propertyModel2, String value2) {

        String parameter = null;
        String propertyModel3 = null;
        String value3 = null;
        String propertyModel4 = null;
        String value4 = null;
        String propertyModel5 = null;
        String value5 = null;

        ArrayList list = getList(propertyModel1, value1, propertyModel2, value2);

        Page(list, false, parameter, propertyModel1, value1, propertyModel2, value2, propertyModel3, value3, propertyModel4, value4, propertyModel5, value5);
    }

    public ProductsList(String propertyModel1, String value1, String propertyModel2, String value2, String propertyModel3, String value3) {

        String parameter = null;
        String propertyModel4 = null;
        String value4 = null;
        String propertyModel5 = null;
        String value5 = null;

        ArrayList list = getList(propertyModel1, value1, propertyModel2, value2, propertyModel3, value3);

        Page(list, false, parameter, propertyModel1, value1, propertyModel2, value2, propertyModel3, value3, propertyModel4, value4, propertyModel5, value5);
    }

    public ProductsList(String propertyModel1, String value1, String propertyModel2, String value2,
                        String propertyModel3, String value3, String propertyModel4, String value4) {

        String parameter = null;
        String propertyModel5 = null;
        String value5 = null;

        ArrayList list = getList(propertyModel1, value1, propertyModel2, value2, propertyModel3, value3);

        Page(list, false, parameter, propertyModel1, value1, propertyModel2, value2, propertyModel3, value3, propertyModel4, value4, propertyModel5, value5);
    }

    public ProductsList(String propertyModel1, String value1, String propertyModel2, String value2, String propertyModel3, String value3,
                        String propertyModel4, String value4, String propertyModel5, String value5) {

        String parameter = null;

        ArrayList list = getList(propertyModel1, value1, propertyModel2, value2, propertyModel3, value3);

        Page(list, false, parameter, propertyModel1, value1, propertyModel2, value2, propertyModel3, value3, propertyModel4, value4, propertyModel5, value5);
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
            Criteria cr = session.createCriteria(ProductsEntity.class);
            if (sort)
                cr.addOrder(Order.asc(parameter));
            else
                cr.addOrder(Order.desc(parameter));
            List users = cr.list();
            for (Iterator iterator =
                 users.iterator(); iterator.hasNext(); ) {
                ProductsEntity productsEntity = (ProductsEntity) iterator.next();
                list.add(productsEntity);

            }
            session.flush();
            session.clear();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
            factory.close();
        }

        return list;
    }

    public ArrayList getList(String propertyModel1, String value1) {
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
            Criteria cr = session.createCriteria(ProductsEntity.class);
            cr.add(Restrictions.ilike(propertyModel1, value1 + "%"));
            List products = cr.list();
            for (Iterator iterator =
                 products.iterator(); iterator.hasNext(); ) {
                ProductsEntity productsEntity = (ProductsEntity) iterator.next();
                list.add(productsEntity);

            }
            session.flush();
            session.clear();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally {
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
            Criteria cr = session.createCriteria(ProductsEntity.class);
            Criterion first = Restrictions.ilike(propertyModel1, value1 + "%");
            Criterion second = Restrictions.ilike(propertyModel2, value2 + "%");
            LogicalExpression andExp = Restrictions.and(first, second);
            cr.add(andExp);
            List products = cr.list();
            for (Iterator iterator =
                 products.iterator(); iterator.hasNext(); ) {
                ProductsEntity productsEntity = (ProductsEntity) iterator.next();
                list.add(productsEntity);

            }
            session.flush();
            session.clear();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
            factory.close();
        }

        return list;
    }

    public ArrayList getList(String propertyModel1, String value1, String propertyModel2, String value2, String propertyModel3, String value3) {
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
            Criteria cr = session.createCriteria(ProductsEntity.class);
            Criterion first = Restrictions.like(propertyModel1, value1 + "%");
            Criterion second = Restrictions.like(propertyModel2, value2 + "%");
            Criterion third = Restrictions.like(propertyModel3, value3 + "%");
            LogicalExpression and = Restrictions.and(first, second);
            LogicalExpression andExp = Restrictions.and(and, third);
            cr.add(andExp);
            List products = cr.list();
            for (Iterator iterator =
                 products.iterator(); iterator.hasNext(); ) {
                ProductsEntity productsEntity = (ProductsEntity) iterator.next();
                list.add(productsEntity);

            }
            session.flush();
            session.clear();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
            factory.close();
        }

        return list;
    }

    public ArrayList getList(String propertyModel1, String value1, String propertyModel2, String value2, String propertyModel3, String value3,
                             String propertyModel4, String value4) {
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
            Criteria cr = session.createCriteria(ProductsEntity.class);
            Criterion first = Restrictions.like(propertyModel1, value1 + "%");
            Criterion second = Restrictions.like(propertyModel2, value2 + "%");
            Criterion third = Restrictions.like(propertyModel3, value3 + "%");
            Criterion fourth = Restrictions.like(propertyModel4, value4 + "%");
            LogicalExpression and1 = Restrictions.and(first, second);
            LogicalExpression and2 = Restrictions.and(third, fourth);
            LogicalExpression andExp = Restrictions.and(and1, and2);
            cr.add(andExp);
            List users = cr.list();
            for (Iterator iterator =
                 users.iterator(); iterator.hasNext(); ) {
                ProductsEntity productsEntity = (ProductsEntity) iterator.next();
                list.add(productsEntity);

            }
            session.flush();
            session.clear();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
            factory.close();
        }

        return list;
    }

    public ArrayList getList(String propertyModel1, String value1, String propertyModel2, String value2, String propertyModel3, String value3,
                             String propertyModel4, String value4, String propertyModel5, String value5) {
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
            Criteria cr = session.createCriteria(ProductsEntity.class);
            Criterion first = Restrictions.like(propertyModel1, value1 + "%");
            Criterion second = Restrictions.like(propertyModel2, value2 + "%");
            Criterion third = Restrictions.like(propertyModel3, value3 + "%");
            Criterion fourth = Restrictions.like(propertyModel4, value4 + "%");
            Criterion fifth = Restrictions.like(propertyModel5, value5 + "%");
            LogicalExpression and1 = Restrictions.and(first, second);
            LogicalExpression and2 = Restrictions.and(third, fourth);
            LogicalExpression and3 = Restrictions.and(and1, and2);
            LogicalExpression andExp = Restrictions.and(and3, fifth);
            cr.add(andExp);
            List users = cr.list();
            for (Iterator iterator =
                 users.iterator(); iterator.hasNext(); ) {
                ProductsEntity productsEntity = (ProductsEntity) iterator.next();
                list.add(productsEntity);

            }
            session.flush();
            session.clear();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
            factory.close();
        }

        return list;
    }

    public void Page(ArrayList list, final boolean sort, final String parameter, String propertyModel1, String value1, String propertyModel2, String value2, String propertyModel3, String value3,
                     String propertyModel4, String value4, String propertyModel5, String value5) {
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

            private static final long serialVersionUID = -2569943428727488858L;

            @Override
            public void onClick() {
                setResponsePage(new UsersList(true));
            }
        });
        formTop.add(new Link("UsersMobileListLink") {

            private static final long serialVersionUID = -4214927561124334248L;

            @Override
            public void onClick() {
                setResponsePage(new UsersMobileList(true, "secondname"));
            }
        });
        formTop.add(new Link("ProductsListLink") {

            private static final long serialVersionUID = -7309763496862906559L;

            @Override
            public void onClick() {
                setResponsePage(new ProductsList(true, "name"));
            }
        });

        add(formTop);

        Form form = new Form("form");
        form.add(new Link("addProductLink") {

            private static final long serialVersionUID = -8848198752850666870L;

            @Override
            public void onClick() {
                setResponsePage(new AddProductPage());
            }
        });

        final DataView<ProductsEntity> dataView = new DataView<ProductsEntity>("simple", new ListDataProvider(list)) {

            public void populateItem(final Item<ProductsEntity> item) {
                final ProductsEntity productsEntity = (ProductsEntity) item.getModelObject();

                Link productPageLink = new Link("productPageLink") {

                    private static final long serialVersionUID = 2325808654982897895L;

                    @Override
                    public void onClick() {
                        setResponsePage(new ProductPage(productsEntity.getId()));
                    }
                };
                productPageLink.add(new Label("name", productsEntity.getName()));

                item.add(productPageLink);
                item.add(new Label("code", productsEntity.getCode()));
                item.add(new Label("type", productsEntity.getType()));
                item.add(new Label("direction", productsEntity.getDirection()));
                item.add(new Label("automationArea", productsEntity.getAutomationArea()));
            }
        };

        dataView.setItemsPerPage(50);

        form.add(new PagingNavigator("navigatorTop", dataView));
        form.add(new NavigatorLabel("lblTop", dataView));
        form.add(new PagingNavigator("navigatorBot", dataView));
        form.add(new NavigatorLabel("lblBot", dataView));
        form.add(dataView);

        Form formSearch = new Form<>("search");

        String productName = null;
        if (propertyModel1 == "name") {
            productName = value1;
        }else if (propertyModel2 == "name"){
            productName = value2;
        }else if (propertyModel3 == "name"){
            productName = value3;
        }else if (propertyModel4 == "name"){
            productName = value4;
        }else if (propertyModel5 == "name"){
            productName = value5;
        }

        final Model<String> name = new Model<String>(productName);
        final TextField textFieldName = new TextField<>("textfieldName", name, String.class);
        formSearch.add(textFieldName);

        String productCode = null;
        if (propertyModel1 == "code"){
            productCode = value1;
        }else if (propertyModel2 == "code"){
            productCode = value2;
        }else if (propertyModel3 == "code"){
            productCode = value3;
        }else if (propertyModel4 == "code"){
            productCode = value4;
        }else if (propertyModel5 == "code"){
            productCode = value5;
        }
        final Model<String> code = new Model<String>(productCode);
        final TextField textFieldCode = new TextField<>("textfieldCode", code, String.class);
        formSearch.add(textFieldCode);

        ProductChoices productChoices = new ProductChoices();

        String producttype = null;
        if (propertyModel1 == "type"){
            producttype = value1;
        }else if (propertyModel2 == "type"){
            producttype = value2;
        }else if (propertyModel3 == "type"){
            producttype = value3;
        }else if (propertyModel4 == "type"){
            producttype = value4;
        }else if (propertyModel5 == "type"){
            producttype = value5;
        }
        final Model<String> productType = new Model<>(producttype);
        List<String> productTypeChoices = new ArrayList<>();
        productTypeChoices.add("Продукт");
        productTypeChoices.add("Решение");
        DropDownChoice<String> productTypeChoice = new DropDownChoice<>("productTypeChoice", productType, productTypeChoices);
        formSearch.add(productTypeChoice);

        String productdirection = null;
        if (propertyModel1 == "direction"){
            productdirection = value1;
        }else if (propertyModel2 == "direction"){
            productdirection = value2;
        }else if (propertyModel3 == "direction"){
            productdirection = value3;
        }else if (propertyModel4 == "direction"){
            productdirection = value4;
        }else if (propertyModel5 == "direction"){
            productdirection = value5;
        }
        final Model<String> productDirection = new Model<>(productdirection);
        List<String> productDirectionChoices = productChoices.getProductDirection();
        DropDownChoice<String> productDirectionChoice = new DropDownChoice<>("productDirectionChoice", productDirection, productDirectionChoices);
        formSearch.add(productDirectionChoice);

        String productautomationArea = null;
        if (propertyModel1 == "automationarea"){
            productautomationArea = value1;
        }else if (propertyModel2 == "automationarea"){
            productautomationArea = value2;
        }else if (propertyModel3 == "automationarea"){
            productautomationArea = value3;
        }else if (propertyModel4 == "automationarea"){
            productautomationArea = value4;
        }else if (propertyModel5 == "automationarea"){
            productautomationArea = value5;
        }
        final Model<String> productAutomationArea = new Model<>(productautomationArea);
        List<String> productAutomationAreaChoices = productChoices.getProductAutomationArea();
        DropDownChoice<String> productAutomationAreaChoice = new DropDownChoice<>("productAutomationAreaChoice", productAutomationArea, productAutomationAreaChoices);
        formSearch.add(productAutomationAreaChoice);

        formSearch.add(new Button("resetSearch") {

            private static final long serialVersionUID = -7600092263263967603L;

            @Override
            public void onSubmit() {
                setResponsePage(new ProductsList(true, "name"));
            }
        });

        formSearch.add(new Button("search") {
            private static final long serialVersionUID = 2285212311331663536L;

            @Override
            public void onSubmit() {


                if (name.getObject() == null && code.getObject() == null && productType.getObject() == null &&
                        productDirection.getObject() == null && productAutomationArea.getObject() == null)
                    setResponsePage(new ProductsList(true, "name"));
                //N
                else if (code.getObject() == null && productType.getObject() == null &&
                        productDirection.getObject() == null && productAutomationArea.getObject() == null)
                    setResponsePage(new ProductsList("name", name.getObject()));
                //C
                else if (name.getObject() == null && productType.getObject() == null &&
                        productDirection.getObject() == null && productAutomationArea.getObject() == null)
                    setResponsePage(new ProductsList("code", code.getObject()));
                //T
                else if (name.getObject() == null && code.getObject() == null &&
                        productDirection.getObject() == null && productAutomationArea.getObject() == null)
                    setResponsePage(new ProductsList("type", productType.getObject()));
                //D
                else if (name.getObject() == null && code.getObject() == null && productType.getObject() == null &&
                        productAutomationArea.getObject() == null)
                    setResponsePage(new ProductsList("direction", productDirection.getObject()));
                //A
                else if (name.getObject() == null && code.getObject() == null && productType.getObject() == null &&
                        productDirection.getObject() == null)
                    setResponsePage(new ProductsList("automationarea", productAutomationArea.getObject()));
                //N C
                else if (productType.getObject() == null && productDirection.getObject() == null &&
                        productAutomationArea.getObject() == null)
                    setResponsePage(new ProductsList("name", name.getObject(), "code", code.getObject()));
                //N T
                else if (code.getObject() == null && productDirection.getObject() == null &&
                        productAutomationArea.getObject() == null)
                    setResponsePage(new ProductsList("name", name.getObject(), "type", productType.getObject()));
                //N D
                else if (productType.getObject() == null && code.getObject() == null &&
                        productAutomationArea.getObject() == null)
                    setResponsePage(new ProductsList("name", name.getObject(), "direction", productDirection.getObject()));
                //N A
                else if (productType.getObject() == null && productDirection.getObject() == null &&
                        code.getObject() == null)
                    setResponsePage(new ProductsList("name", name.getObject(), "automationarea", productAutomationArea.getObject()));
                //C D
                else if (productType.getObject() == null && name.getObject() == null &&
                        productAutomationArea.getObject() == null)
                    setResponsePage(new ProductsList("code", code.getObject(), "direction", productDirection.getObject()));
                //C T
                else if (name.getObject() == null && productDirection.getObject() == null &&
                        productAutomationArea.getObject() == null)
                    setResponsePage(new ProductsList("code", code.getObject(), "type", productType.getObject()));
                //C A
                else if (productType.getObject() == null && productDirection.getObject() == null &&
                        name.getObject() == null)
                    setResponsePage(new ProductsList("code", code.getObject(), "automationarea", productAutomationArea.getObject()));
                //T D
                else if (productAutomationArea.getObject() == null && code.getObject() == null &&
                        name.getObject() == null)
                    setResponsePage(new ProductsList("type", productType.getObject(), "direction", productDirection.getObject()));
                //T A
                else if (code.getObject() == null && productDirection.getObject() == null &&
                        name.getObject() == null)
                    setResponsePage(new ProductsList("type", productType.getObject(), "automationarea", productAutomationArea.getObject()));
                //D A
                else if (productType.getObject() == null && code.getObject() == null &&
                        name.getObject() == null)
                    setResponsePage(new ProductsList("direction", productDirection.getObject(), "automationarea", productAutomationArea.getObject()));
                //N D A
                else if (productType.getObject() == null && code.getObject() == null)
                    setResponsePage(new ProductsList("name", name.getObject(),
                            "direction", productDirection.getObject(), "automationarea", productAutomationArea.getObject()));
                //N C A
                else if (productType.getObject() == null && productDirection.getObject() == null)
                    setResponsePage(new ProductsList("name", name.getObject(),
                            "code", code.getObject(), "automationarea", productAutomationArea.getObject()));
                //N D T
                else if (productAutomationArea.getObject() == null && code.getObject() == null)
                    setResponsePage(new ProductsList("name", name.getObject(),
                            "direction", productDirection.getObject(), "type", productType.getObject()));
                //N C T
                else if (productAutomationArea.getObject() == null && productDirection.getObject() == null)
                    setResponsePage(new ProductsList("name", name.getObject(),
                            "code", code.getObject(), "type", productType.getObject()));
                //N D C
                else if (productType.getObject() == null && productAutomationArea.getObject() == null)
                    setResponsePage(new ProductsList("name", name.getObject(),
                            "direction", productDirection.getObject(), "code", code.getObject()));
                //C D A
                else if (productType.getObject() == null && name.getObject() == null)
                    setResponsePage(new ProductsList("code", code.getObject(),
                            "direction", productDirection.getObject(), "automationarea", productAutomationArea.getObject()));
                //C D T
                else if (productAutomationArea.getObject() == null && name.getObject() == null)
                    setResponsePage(new ProductsList("code", code.getObject(),
                            "direction", productDirection.getObject(), "type", productType.getObject()));
                //T D A
                else if (code.getObject() == null && name.getObject() == null)
                    setResponsePage(new ProductsList("automationarea", productAutomationArea.getObject(),
                            "direction", productDirection.getObject(), "type", productType.getObject()));
                //N T C D
                else if (productAutomationArea.getObject() == null)
                    setResponsePage(new ProductsList("name", name.getObject(),"code", code.getObject(),
                            "direction", productDirection.getObject(), "type", productType.getObject()));
                //N T C A
                else if (productDirection.getObject() == null)
                    setResponsePage(new ProductsList("name", name.getObject(),"code", code.getObject(),
                            "automationarea", productAutomationArea.getObject(), "type", productType.getObject()));
                //N T D A
                else if (code.getObject() == null)
                    setResponsePage(new ProductsList("name", name.getObject(),"direction", productDirection.getObject(),
                            "automationarea", productAutomationArea.getObject(), "type", productType.getObject()));
                //N C D A
                else if (productType.getObject() == null)
                    setResponsePage(new ProductsList("name", name.getObject(),"direction", productDirection.getObject(),
                            "automationarea", productAutomationArea.getObject(), "code", code.getObject()));
                //T C D A
                else if (name.getObject() == null)
                    setResponsePage(new ProductsList("type", productType.getObject(),"direction", productDirection.getObject(),
                            "automationarea", productAutomationArea.getObject(), "code", code.getObject()));
                //N T C D A
                else
                    setResponsePage(new ProductsList("name", name.getObject(),
                            "type", productType.getObject(), "code", code.getObject(),"direction", productDirection.getObject(),
                            "automationarea", productAutomationArea.getObject()));

            }
        });

        form.add(formSearch);

        form.add(new Link("sortName") {

            private static final long serialVersionUID = -7246337072391770706L;

            @Override
            public void onClick() {
                if (sort)
                    setResponsePage(new ProductsList(false, "name"));
                else
                    setResponsePage(new ProductsList(true, "name"));
            }
        });


        form.add(new Link("sortCode") {

            private static final long serialVersionUID = 7830898995270875155L;

            @Override
            public void onClick() {
                if (sort)
                    setResponsePage(new ProductsList(false, "code"));
                else
                    setResponsePage(new ProductsList(true, "code"));
            }
        });

        add(form);
    }
}
