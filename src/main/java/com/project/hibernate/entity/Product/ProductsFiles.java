package com.project.hibernate.entity.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productsfiles", schema = "public", catalog = "fors")
public class ProductsFiles implements Serializable {

    private static final long serialVersionUID = -7540986463713395945L;

    @Id
    @Column(name = "productfiles_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productfilesID;
    @Column(name = "name", nullable = false, length = 60)
    private String name;
    @Column(name = "type", nullable = false, length = 60)
    private String type;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="productsentity_productsfiles", schema = "public", catalog = "fors",
            joinColumns = { @JoinColumn(name = "productfiles_id") }, inverseJoinColumns = { @JoinColumn(name = "id") })
    private Set<ProductsEntity> productsEntity = new HashSet<ProductsEntity>();

    public ProductsFiles(){

    }

    public ProductsFiles (String name, String type){
        this.name = name;
        this.type = type;
    }

    public int getProductfilesID() {
        return productfilesID;
    }

    public void setProductfilesID(int productfilesID) {
        this.productfilesID = productfilesID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<ProductsEntity> getProductsEntity() {
        return productsEntity;
    }

    public void setProductsEntity(Set<ProductsEntity> productsEntity) {
        this.productsEntity = productsEntity;
    }
}
