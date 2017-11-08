package com.project.hibernate.entity.Project;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects", schema = "public", catalog = "fors")
public class ProjectsEntity implements Serializable {

    private static final long serialVersionUID = -3217813107043609126L;

    private int id;
    private String projectname;
    private String projectcipher;
    private String projectcustomer;
    private String projecttype;
    private String projectapplicationarea;
    private String projecttarget;
    private String projectdatastart;
    private String projectdataend;
    private String projecttechnologicaltype;
    private String projectstechnologicalresults;
    private String projectcentralization;
    private String projectarchitecture;
    private String projectlanguages;
    private String projectdatabase;
    private String projectlogic;
    private String projecttools;
    private String projectinformationinteraction;
    private String projectencryptiontools;
    private String projectpersonaldata;
    private String projecthardwareplatform;
    private String projectscalability;
    private String projectreplicate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="projectsentity_projectsfiles", schema = "public", catalog = "fors",
            joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "projectsfiles_id") })
    private Set<ProjectsFiles> projectsFiles = new HashSet<ProjectsFiles>();

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "projectname", nullable = false, length = -1)
    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    @Basic
    @Column(name = "projectcipher", nullable = false, length = -1)
    public String getProjectcipher() {
        return projectcipher;
    }

    public void setProjectcipher(String projectcipher) {
        this.projectcipher = projectcipher;
    }

    @Basic
    @Column(name = "projectcustomer", nullable = false, length = -1)
    public String getProjectcustomer() {
        return projectcustomer;
    }

    public void setProjectcustomer(String projectcustomer) {
        this.projectcustomer = projectcustomer;
    }

    @Basic
    @Column(name = "projecttype", nullable = false, length = -1)
    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    @Basic
    @Column(name = "projectapplicationarea", nullable = true, length = -1)
    public String getProjectapplicationarea() {
        return projectapplicationarea;
    }

    public void setProjectapplicationarea(String projectapplicationarea) {
        this.projectapplicationarea = projectapplicationarea;
    }

    @Basic
    @Column(name = "projecttarget", nullable = true, length = -1)
    public String getProjecttarget() {
        return projecttarget;
    }

    public void setProjecttarget(String projecttarget) {
        this.projecttarget = projecttarget;
    }

    @Basic
    @Column(name = "projectdatastart", nullable = true, length = -1)
    public String getProjectdatastart() {
        return projectdatastart;
    }

    public void setProjectdatastart(String projectdatastart) {
        this.projectdatastart = projectdatastart;
    }

    @Basic
    @Column(name = "projectdataend", nullable = true, length = -1)
    public String getProjectdataend() {
        return projectdataend;
    }

    public void setProjectdataend(String projectdataend) {
        this.projectdataend = projectdataend;
    }

    @Basic
    @Column(name = "projecttechnologicaltype", nullable = true, length = -1)
    public String getProjecttechnologicaltype() {
        return projecttechnologicaltype;
    }

    public void setProjecttechnologicaltype(String projecttechnologicaltype) {
        this.projecttechnologicaltype = projecttechnologicaltype;
    }

    @Basic
    @Column(name = "projectstechnologicalresults", nullable = true, length = -1)
    public String getProjectstechnologicalresults() {
        return projectstechnologicalresults;
    }

    public void setProjectstechnologicalresults(String projectstechnologicalresults) {
        this.projectstechnologicalresults = projectstechnologicalresults;
    }

    @Basic
    @Column(name = "projectcentralization", nullable = true, length = -1)
    public String getProjectcentralization() {
        return projectcentralization;
    }

    public void setProjectcentralization(String projectcentralization) {
        this.projectcentralization = projectcentralization;
    }

    @Basic
    @Column(name = "projectarchitecture", nullable = true, length = -1)
    public String getProjectarchitecture() {
        return projectarchitecture;
    }

    public void setProjectarchitecture(String projectarchitecture) {
        this.projectarchitecture = projectarchitecture;
    }

    @Basic
    @Column(name = "projectlanguages", nullable = true, length = -1)
    public String getProjectlanguages() {
        return projectlanguages;
    }

    public void setProjectlanguages(String projectlanguages) {
        this.projectlanguages = projectlanguages;
    }

    @Basic
    @Column(name = "projectdatabase", nullable = true, length = -1)
    public String getProjectdatabase() {
        return projectdatabase;
    }

    public void setProjectdatabase(String projectdatabase) {
        this.projectdatabase = projectdatabase;
    }

    @Basic
    @Column(name = "projectlogic", nullable = true, length = -1)
    public String getProjectlogic() {
        return projectlogic;
    }

    public void setProjectlogic(String projectlogic) {
        this.projectlogic = projectlogic;
    }

    @Basic
    @Column(name = "projecttools", nullable = true, length = -1)
    public String getProjecttools() {
        return projecttools;
    }

    public void setProjecttools(String projecttools) {
        this.projecttools = projecttools;
    }

    @Basic
    @Column(name = "projectinformationinteraction", nullable = true, length = -1)
    public String getProjectinformationinteraction() {
        return projectinformationinteraction;
    }

    public void setProjectinformationinteraction(String projectinformationinteraction) {
        this.projectinformationinteraction = projectinformationinteraction;
    }

    @Basic
    @Column(name = "projectencryptiontools", nullable = true, length = -1)
    public String getProjectencryptiontools() {
        return projectencryptiontools;
    }

    public void setProjectencryptiontools(String projectencryptiontools) {
        this.projectencryptiontools = projectencryptiontools;
    }

    @Basic
    @Column(name = "projectpersonaldata", nullable = true, length = -1)
    public String getProjectpersonaldata() {
        return projectpersonaldata;
    }

    public void setProjectpersonaldata(String projectpersonaldata) {
        this.projectpersonaldata = projectpersonaldata;
    }

    @Basic
    @Column(name = "projecthardwareplatform", nullable = true, length = -1)
    public String getProjecthardwareplatform() {
        return projecthardwareplatform;
    }

    public void setProjecthardwareplatform(String projecthardwareplatform) {
        this.projecthardwareplatform = projecthardwareplatform;
    }

    @Basic
    @Column(name = "projectscalability", nullable = true, length = -1)
    public String getProjectscalability() {
        return projectscalability;
    }

    public void setProjectscalability(String projectscalability) {
        this.projectscalability = projectscalability;
    }

    @Basic
    @Column(name = "projectreplicate", nullable = true, length = -1)
    public String getProjectreplicate() {
        return projectreplicate;
    }

    public void setProjectreplicate(String projectreplicate) {
        this.projectreplicate = projectreplicate;
    }

    public Set<ProjectsFiles> getProjectsFiles() {
        return projectsFiles;
    }

    public void setProjectsFiles(Set<ProjectsFiles> projectsFiles) {
        this.projectsFiles = projectsFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectsEntity that = (ProjectsEntity) o;

        if (id != that.id) return false;
        if (projectname != null ? !projectname.equals(that.projectname) : that.projectname != null) return false;
        if (projectcipher != null ? !projectcipher.equals(that.projectcipher) : that.projectcipher != null)
            return false;
        if (projectcustomer != null ? !projectcustomer.equals(that.projectcustomer) : that.projectcustomer != null)
            return false;
        if (projecttype != null ? !projecttype.equals(that.projecttype) : that.projecttype != null) return false;
        if (projectapplicationarea != null ? !projectapplicationarea.equals(that.projectapplicationarea) : that.projectapplicationarea != null)
            return false;
        if (projecttarget != null ? !projecttarget.equals(that.projecttarget) : that.projecttarget != null)
            return false;
        if (projectdatastart != null ? !projectdatastart.equals(that.projectdatastart) : that.projectdatastart != null)
            return false;
        if (projectdataend != null ? !projectdataend.equals(that.projectdataend) : that.projectdataend != null)
            return false;
        if (projecttechnologicaltype != null ? !projecttechnologicaltype.equals(that.projecttechnologicaltype) : that.projecttechnologicaltype != null)
            return false;
        if (projectstechnologicalresults != null ? !projectstechnologicalresults.equals(that.projectstechnologicalresults) : that.projectstechnologicalresults != null)
            return false;
        if (projectcentralization != null ? !projectcentralization.equals(that.projectcentralization) : that.projectcentralization != null)
            return false;
        if (projectarchitecture != null ? !projectarchitecture.equals(that.projectarchitecture) : that.projectarchitecture != null)
            return false;
        if (projectlanguages != null ? !projectlanguages.equals(that.projectlanguages) : that.projectlanguages != null)
            return false;
        if (projectdatabase != null ? !projectdatabase.equals(that.projectdatabase) : that.projectdatabase != null)
            return false;
        if (projectlogic != null ? !projectlogic.equals(that.projectlogic) : that.projectlogic != null) return false;
        if (projecttools != null ? !projecttools.equals(that.projecttools) : that.projecttools != null) return false;
        if (projectinformationinteraction != null ? !projectinformationinteraction.equals(that.projectinformationinteraction) : that.projectinformationinteraction != null)
            return false;
        if (projectencryptiontools != null ? !projectencryptiontools.equals(that.projectencryptiontools) : that.projectencryptiontools != null)
            return false;
        if (projectpersonaldata != null ? !projectpersonaldata.equals(that.projectpersonaldata) : that.projectpersonaldata != null)
            return false;
        if (projecthardwareplatform != null ? !projecthardwareplatform.equals(that.projecthardwareplatform) : that.projecthardwareplatform != null)
            return false;
        if (projectscalability != null ? !projectscalability.equals(that.projectscalability) : that.projectscalability != null)
            return false;
        if (projectreplicate != null ? !projectreplicate.equals(that.projectreplicate) : that.projectreplicate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (projectname != null ? projectname.hashCode() : 0);
        result = 31 * result + (projectcipher != null ? projectcipher.hashCode() : 0);
        result = 31 * result + (projectcustomer != null ? projectcustomer.hashCode() : 0);
        result = 31 * result + (projecttype != null ? projecttype.hashCode() : 0);
        result = 31 * result + (projectapplicationarea != null ? projectapplicationarea.hashCode() : 0);
        result = 31 * result + (projecttarget != null ? projecttarget.hashCode() : 0);
        result = 31 * result + (projectdatastart != null ? projectdatastart.hashCode() : 0);
        result = 31 * result + (projectdataend != null ? projectdataend.hashCode() : 0);
        result = 31 * result + (projecttechnologicaltype != null ? projecttechnologicaltype.hashCode() : 0);
        result = 31 * result + (projectstechnologicalresults != null ? projectstechnologicalresults.hashCode() : 0);
        result = 31 * result + (projectcentralization != null ? projectcentralization.hashCode() : 0);
        result = 31 * result + (projectarchitecture != null ? projectarchitecture.hashCode() : 0);
        result = 31 * result + (projectlanguages != null ? projectlanguages.hashCode() : 0);
        result = 31 * result + (projectdatabase != null ? projectdatabase.hashCode() : 0);
        result = 31 * result + (projectlogic != null ? projectlogic.hashCode() : 0);
        result = 31 * result + (projecttools != null ? projecttools.hashCode() : 0);
        result = 31 * result + (projectinformationinteraction != null ? projectinformationinteraction.hashCode() : 0);
        result = 31 * result + (projectencryptiontools != null ? projectencryptiontools.hashCode() : 0);
        result = 31 * result + (projectpersonaldata != null ? projectpersonaldata.hashCode() : 0);
        result = 31 * result + (projecthardwareplatform != null ? projecthardwareplatform.hashCode() : 0);
        result = 31 * result + (projectscalability != null ? projectscalability.hashCode() : 0);
        result = 31 * result + (projectreplicate != null ? projectreplicate.hashCode() : 0);
        return result;
    }
}
