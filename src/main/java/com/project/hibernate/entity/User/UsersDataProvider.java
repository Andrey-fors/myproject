package com.project.hibernate.entity.User;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SingleSortState;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class UsersDataProvider implements ISortableDataProvider<Users, Object>, IFilterStateLocator {
    private static final long serialVersionUID = 5613612109481404799L;
    ISortState sortState = new SingleSortState();
    UsersFilter filter = new UsersFilter();

    private List<Users> list;


    public Iterator<? extends Users> iterator(long first, long count) {
        initList();

        List<Users> ret = list;
        if (ret.size() > (first + count)) {
            ret = ret.subList((int)first, (int)(first + count));
        }

        return ret.iterator();
    }

    public IModel<Users> model(Users object) {
        return Model.of(object);
    }

    public long size() {
        initList();

        return list.size();
    }

    public void detach() {
        list = null;
    }

    public ISortState getSortState() {
        return sortState;
    }

    public void setSortState(ISortState state) {
        sortState = state;
    }

    public Object getFilterState() {
        return filter;
    }

    @Override
    public void setFilterState(Object state) {
        filter = (UsersFilter) state;
    }

    private void initList() {
        if (list == null) {
            final SortOrder secondNameSort;
            if (sortState != null) {
                secondNameSort = sortState.getPropertySortOrder("secondName");
            } else {
                secondNameSort = SortOrder.NONE;
            }

            list = getSortedList(secondNameSort, filter);
        }
    }

    private static SessionFactory factory;
    private List<Users> getUserDB(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        List<Users> list = new ArrayList<>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM Users").list();
            for (Iterator iterator =
                 users.iterator(); iterator.hasNext(); ) {
                Users usersEntity = (Users) iterator.next();
                list.add(usersEntity);

            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }

    private List<Users> listDB = getUserDB();

    private List<Users> getSortedList(final SortOrder nameSort, UsersFilter filter) {
        List<Users> result = listDB;

        Collections.sort(result, new Comparator<Users>() {
            public int compare(Users o1, Users o2) {
                int compSecondName = o1.getSecondname().compareTo(o2.getSecondname());
                switch (nameSort) {
                    case NONE:
                        compSecondName = 0;
                        break;
                    case DESCENDING:
                        compSecondName = -compSecondName;
                        break;
                }
                return compSecondName;
            }
        });

        return result;
    }
}

