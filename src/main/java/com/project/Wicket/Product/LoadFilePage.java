package com.project.Wicket.Product;

import com.project.Wicket.User.UserPage;
import com.project.hibernate.entity.Product.ProductsEntity;
import com.project.hibernate.entity.Product.ProductsFiles;
import com.project.hibernate.entity.User.Users;
import com.sun.istack.internal.NotNull;
import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Bytes;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoadFilePage extends WebPage {

    private static final long serialVersionUID = -6076935565689880522L;

    private FileUploadField fileUpload;
        private String UPLOAD_FOLDER = "../../../home/user/FilesSpace/productFiles/";
//    private String UPLOAD_FOLDER = "D:\\Imgs\\FilesSpace\\";
    private Integer productID;
    private static SessionFactory factory;
    private Model<String> fileType = new Model<>();

    public LoadFilePage(String productName, final PageReference page, final ModalWindow window) {

        UPLOAD_FOLDER = UPLOAD_FOLDER + productName + "/";
        addComponents(page, window);
    }

    public LoadFilePage(String productName, Integer productsID, final PageReference page, final ModalWindow window) {

        UPLOAD_FOLDER = UPLOAD_FOLDER + productName + "/";
        productID = productsID;

        addComponents(page, window);

    }

    private void addComponents(final PageReference page, final ModalWindow window) {

        Form<?> form = new Form<Object>("form");

        final Model<String> fileType = new Model<String>();
        List<String> subChoicesType = returnFileType();
        DropDownChoice<String> type = new DropDownChoice<String>("choiceType", fileType, subChoicesType);
        form.add(type);

        form.setMultiPart(true);
        form.setMaxSize(Bytes.megabytes(100));
        form.add(fileUpload = new FileUploadField("fileUpload"));


        form.add(new AjaxButton("submitSave") {

            private static final long serialVersionUID = 7162031563760330908L;

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
                    final FileUpload uploadedFile = fileUpload.getFileUpload();
                    Set<ProductsFiles> productsFiles = new HashSet<ProductsFiles>();
                    if (uploadedFile != null) {
                        // write to a new file
                        File newFile = new File(UPLOAD_FOLDER
                                + uploadedFile.getClientFileName());
                        Path path = Paths.get(UPLOAD_FOLDER);
                        if (!Files.exists(path)) {
                            new File(UPLOAD_FOLDER).mkdirs();
                        }

                        boolean created;
                        try {
                            created = newFile.createNewFile();
                            uploadedFile.writeTo(newFile);
                            productsFiles.add(new ProductsFiles(uploadedFile.getClientFileName(), fileType.getObject()));
                        } catch (Exception error) {
                            throw new IllegalStateException("Error");
                        }
                    }
                    ProductsEntity product =
                            (ProductsEntity) session.get(ProductsEntity.class, productID);
                    product.setProductsFiles(productsFiles);
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
                if (page != null)
                    window.close(target);
            }
        });

        form.add(new AjaxLink("submitCancel") {

            private static final long serialVersionUID = 9129387194406215595L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                window.close(target);
            }
        });

        add(form);
    }

    public List<String> returnFileType() {
        List<String> fileTypeChoices = new ArrayList<>();
        fileTypeChoices.add("Презентация");
        fileTypeChoices.add("Листовка");
        fileTypeChoices.add("Длинная листовка");
        fileTypeChoices.add("Брошюра");
        fileTypeChoices.add("Пресс-релиз");
        fileTypeChoices.add("Интервью");

        return fileTypeChoices;
    }

}
