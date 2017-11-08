package com.project.hibernate.entity.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "products", schema = "public", catalog = "fors")
public class ProductsEntity implements Serializable {

    private static final long serialVersionUID = 4697142860663594391L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productsID;
    @Column(name = "name", nullable = false, length = 60)
    private String name;
    @Column(name = "type", nullable = false, length = 60)
    private String type;
    @Column(name = "code", nullable = false, length = 60)
    private String code;
    @Column(name = "direction", nullable = false, length = 60)
    private String direction;
    @Column(name = "features", nullable = true, length = 60)
    private String features;
    @Column(name = "customers", nullable = false, length = 60)
    private String customers;
    @Column(name = "automationarea", nullable =false, length = 60)
    private String automationarea;
    @Column(name = "targets", nullable = true, length = 60)
    private String targets;
    @Column(name = "tasks", nullable = true, length = 60)
    private String tasks;
    @Column(name = "centralization", nullable = true, length = 60)
    private String centralization;
    @Column(name = "architecture", nullable = true, length = 60)
    private String architecture;
    @Column(name = "languages", nullable = true, length = 60)
    private String languages;
    @Column(name = "database", nullable = true, length = -1)
    private String database;
    @Column(name = "tools", nullable = true, length = -1)
    private String tools;
    @Column(name = "encryptions", nullable = true, length = -1)
    private String encryptions;
    @Column(name = "requirements", nullable = true, length = -1)
    private String requirements;
    @Column(name = "integration", nullable = true, length = -1)
    private String integration;
    @Column(name = "work", nullable = true, length = -1)
    private String work;
    @Column(name = "number", nullable = true, length = -1)
    private String number;
    @Column(name = "inclusion", nullable = true, length = -1)
    private String inclusion;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="productsentity_productsfiles", schema = "public", catalog = "fors",
            joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "productfiles_id") })
    private Set<ProductsFiles> productsFiles = new HashSet<ProductsFiles>();

    public int getId() {
        return productsID;
    }

    public void setId(int id) {
        this.productsID = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getCustomers() {
        return customers;
    }

    public void setCustomers(String customers) {
        this.customers = customers;
    }

    public String getAutomationArea() {
        return automationarea;
    }

    public void setAutomationArea(String automationArea) {
        this.automationarea = automationArea;
    }

    public String getTargets() {
        return targets;
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public String getCentralization() {
        return centralization;
    }

    public void setCentralization(String centralization) {
        this.centralization = centralization;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    public String getEncryptions() {
        return encryptions;
    }

    public void setEncryptions(String encryptions) {
        this.encryptions = encryptions;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getIntegration() {
        return integration;
    }

    public void setIntegration(String integration) {
        this.integration = integration;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getInclusion() {
        return inclusion;
    }

    public void setInclusion(String inclusion) {
        this.inclusion = inclusion;
    }

    public Set<ProductsFiles> getProductsFiles() {
        return productsFiles;
    }

    public void setProductsFiles(Set<ProductsFiles> productsFiles) {
        this.productsFiles = productsFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsEntity that = (ProductsEntity) o;

        if (productsID != that.productsID) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (direction != null ? !direction.equals(that.direction) : that.direction != null) return false;
        if (features != null ? !features.equals(that.features) : that.features != null) return false;
        if (customers != null ? !customers.equals(that.customers) : that.customers != null) return false;
        if (automationarea != null ? !automationarea.equals(that.automationarea) : that.automationarea != null)
            return false;
        if (targets != null ? !targets.equals(that.targets) : that.targets != null) return false;
        if (tasks != null ? !tasks.equals(that.tasks) : that.tasks != null) return false;
        if (centralization != null ? !centralization.equals(that.centralization) : that.centralization != null)
            return false;
        if (architecture != null ? !architecture.equals(that.architecture) : that.architecture != null) return false;
        if (languages != null ? !languages.equals(that.languages) : that.languages != null) return false;
        if (database != null ? !database.equals(that.database) : that.database != null) return false;
        if (tools != null ? !tools.equals(that.tools) : that.tools != null) return false;
        if (encryptions != null ? !encryptions.equals(that.encryptions) : that.encryptions != null) return false;
        if (requirements != null ? !requirements.equals(that.requirements) : that.requirements != null) return false;
        if (integration != null ? !integration.equals(that.integration) : that.integration != null) return false;
        if (work != null ? !work.equals(that.work) : that.work != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (inclusion != null ? !inclusion.equals(that.inclusion) : that.inclusion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productsID;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + (features != null ? features.hashCode() : 0);
        result = 31 * result + (customers != null ? customers.hashCode() : 0);
        result = 31 * result + (automationarea != null ? automationarea.hashCode() : 0);
        result = 31 * result + (targets != null ? targets.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        result = 31 * result + (centralization != null ? centralization.hashCode() : 0);
        result = 31 * result + (architecture != null ? architecture.hashCode() : 0);
        result = 31 * result + (languages != null ? languages.hashCode() : 0);
        result = 31 * result + (database != null ? database.hashCode() : 0);
        result = 31 * result + (tools != null ? tools.hashCode() : 0);
        result = 31 * result + (encryptions != null ? encryptions.hashCode() : 0);
        result = 31 * result + (requirements != null ? requirements.hashCode() : 0);
        result = 31 * result + (integration != null ? integration.hashCode() : 0);
        result = 31 * result + (work != null ? work.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (inclusion != null ? inclusion.hashCode() : 0);
        return result;
    }
}
