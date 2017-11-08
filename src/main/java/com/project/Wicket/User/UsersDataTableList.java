package com.project.Wicket.User;

import com.project.hibernate.entity.User.Users;
import com.project.hibernate.entity.User.UsersDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.TextFilteredPropertyColumn;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.List;

public class UsersDataTableList extends WebPage {
    private static final long serialVersionUID = 4302064745856550903L;

    UsersDataTableList() {
        List<IColumn<Users, String>> columns=new ArrayList<IColumn<Users, String>>();
        UsersDataProvider dataProvider=new UsersDataProvider();
        columns.add(new TextFilteredPropertyColumn<Users,String, String >(Model.of("SecondName"),"secondname","secondname"));
        columns.add(new TextFilteredPropertyColumn<Users,String, String >(Model.of("Name"),"name","name"));
        columns.add(new PropertyColumn<Users, String>(Model.of("Alter"),"alter","alter"));


        FilterForm form=new FilterForm("form",dataProvider);

        DataTable<Users, String> dataTable = new DataTable<>("dataTable",columns,dataProvider,10);
        dataTable.addTopToolbar(new FilterToolbar(dataTable,form));
        form.add(dataTable);

        add(form);
    }
}
