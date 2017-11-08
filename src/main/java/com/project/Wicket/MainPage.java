package com.project.Wicket;

import com.project.Wicket.Product.ProductPage;
import com.project.Wicket.Product.ProductsList;
import com.project.Wicket.Project.ProjectPage;
import com.project.Wicket.User.UsersList;
import com.project.Wicket.User.UsersMobileList;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

public class MainPage extends WebPage {

    private static final long serialVersionUID = 3807164972237129068L;

    public MainPage() {

        Link link = new Link("mainPage") {
            private static final long serialVersionUID = -3046076558422615941L;

            @Override
            public void onClick() {
                setResponsePage(new MainPage());
            }
        };

        link.add(new Image("logo", "../CSS/logo.png"));

        add(link);

        Form<?> formTop = new Form<Object>("formTop");

        formTop.add(new Link("UsersListLink") {

            private static final long serialVersionUID = 7823367549609334463L;

            @Override
            public void onClick() {
                setResponsePage(new UsersList(true));
            }
        });
        formTop.add(new Link("UsersMobileListLink") {

            private static final long serialVersionUID = 7823367549609334463L;

            @Override
            public void onClick() {
                setResponsePage(new UsersMobileList(true, "secondname"));
            }
        });
        formTop.add(new Link("ProductsListLink") {

            private static final long serialVersionUID = 7823367549609334463L;

            @Override
            public void onClick() {
                setResponsePage(new ProductsList(true, "name"));
            }
        });
        formTop.add(new Link("ProjectsListLink") {

            private static final long serialVersionUID = 7823367549609334463L;

            @Override
            public void onClick() {
                setResponsePage(new ProjectPage(1));
            }
        });


        add(formTop);
    }
}
