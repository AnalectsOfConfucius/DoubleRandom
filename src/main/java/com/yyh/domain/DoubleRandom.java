package com.yyh.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DoubleRandom.
 */
@Entity
@Table(name = "double_random")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DoubleRandom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 32)
    @Column(name = "double_random_name", length = 32, nullable = false)
    private String doubleRandomName;

    @NotNull
    @Size(max = 32)
    @Column(name = "double_random_date", length = 32, nullable = false)
    private String doubleRandomDate;

    @NotNull
    @Size(max = 32)
    @Column(name = "double_random_notary", length = 32, nullable = false)
    private String doubleRandomNotary;

    @NotNull
    @Size(max = 64)
    @Column(name = "double_random_company_condition_1", length = 64, nullable = false)
    private String doubleRandomCompanyCondition1;

    @NotNull
    @Size(max = 64)
    @Column(name = "double_random_company_condition_2", length = 64, nullable = false)
    private String doubleRandomCompanyCondition2;

    @NotNull
    @Size(max = 64)
    @Column(name = "double_random_company_condition_3", length = 64, nullable = false)
    private String doubleRandomCompanyCondition3;

    @NotNull
    @Size(max = 64)
    @Column(name = "double_random_company_condition_4", length = 64, nullable = false)
    private String doubleRandomCompanyCondition4;

    @NotNull
    @Size(max = 64)
    @Column(name = "double_random_manager_condition_1", length = 64, nullable = false)
    private String doubleRandomManagerCondition1;

    @NotNull
    @Size(max = 64)
    @Column(name = "double_random_manager_condition_2", length = 64, nullable = false)
    private String doubleRandomManagerCondition2;

    @NotNull
    @Size(max = 64)
    @Column(name = "double_random_manager_condition_3", length = 64, nullable = false)
    private String doubleRandomManagerCondition3;

    @NotNull
    @Size(max = 64)
    @Column(name = "double_random_manager_condition_4", length = 64, nullable = false)
    private String doubleRandomManagerCondition4;

    @OneToMany(mappedBy = "doubleRandom")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(mappedBy = "doubleRandom")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DoubleRandomResult> doubleRandomResults = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoubleRandomName() {
        return doubleRandomName;
    }

    public DoubleRandom doubleRandomName(String doubleRandomName) {
        this.doubleRandomName = doubleRandomName;
        return this;
    }

    public void setDoubleRandomName(String doubleRandomName) {
        this.doubleRandomName = doubleRandomName;
    }

    public String getDoubleRandomDate() {
        return doubleRandomDate;
    }

    public DoubleRandom doubleRandomDate(String doubleRandomDate) {
        this.doubleRandomDate = doubleRandomDate;
        return this;
    }

    public void setDoubleRandomDate(String doubleRandomDate) {
        this.doubleRandomDate = doubleRandomDate;
    }

    public String getDoubleRandomNotary() {
        return doubleRandomNotary;
    }

    public DoubleRandom doubleRandomNotary(String doubleRandomNotary) {
        this.doubleRandomNotary = doubleRandomNotary;
        return this;
    }

    public void setDoubleRandomNotary(String doubleRandomNotary) {
        this.doubleRandomNotary = doubleRandomNotary;
    }

    public String getDoubleRandomCompanyCondition1() {
        return doubleRandomCompanyCondition1;
    }

    public DoubleRandom doubleRandomCompanyCondition1(String doubleRandomCompanyCondition1) {
        this.doubleRandomCompanyCondition1 = doubleRandomCompanyCondition1;
        return this;
    }

    public void setDoubleRandomCompanyCondition1(String doubleRandomCompanyCondition1) {
        this.doubleRandomCompanyCondition1 = doubleRandomCompanyCondition1;
    }

    public String getDoubleRandomCompanyCondition2() {
        return doubleRandomCompanyCondition2;
    }

    public DoubleRandom doubleRandomCompanyCondition2(String doubleRandomCompanyCondition2) {
        this.doubleRandomCompanyCondition2 = doubleRandomCompanyCondition2;
        return this;
    }

    public void setDoubleRandomCompanyCondition2(String doubleRandomCompanyCondition2) {
        this.doubleRandomCompanyCondition2 = doubleRandomCompanyCondition2;
    }

    public String getDoubleRandomCompanyCondition3() {
        return doubleRandomCompanyCondition3;
    }

    public DoubleRandom doubleRandomCompanyCondition3(String doubleRandomCompanyCondition3) {
        this.doubleRandomCompanyCondition3 = doubleRandomCompanyCondition3;
        return this;
    }

    public void setDoubleRandomCompanyCondition3(String doubleRandomCompanyCondition3) {
        this.doubleRandomCompanyCondition3 = doubleRandomCompanyCondition3;
    }

    public String getDoubleRandomCompanyCondition4() {
        return doubleRandomCompanyCondition4;
    }

    public DoubleRandom doubleRandomCompanyCondition4(String doubleRandomCompanyCondition4) {
        this.doubleRandomCompanyCondition4 = doubleRandomCompanyCondition4;
        return this;
    }

    public void setDoubleRandomCompanyCondition4(String doubleRandomCompanyCondition4) {
        this.doubleRandomCompanyCondition4 = doubleRandomCompanyCondition4;
    }

    public String getDoubleRandomManagerCondition1() {
        return doubleRandomManagerCondition1;
    }

    public DoubleRandom doubleRandomManagerCondition1(String doubleRandomManagerCondition1) {
        this.doubleRandomManagerCondition1 = doubleRandomManagerCondition1;
        return this;
    }

    public void setDoubleRandomManagerCondition1(String doubleRandomManagerCondition1) {
        this.doubleRandomManagerCondition1 = doubleRandomManagerCondition1;
    }

    public String getDoubleRandomManagerCondition2() {
        return doubleRandomManagerCondition2;
    }

    public DoubleRandom doubleRandomManagerCondition2(String doubleRandomManagerCondition2) {
        this.doubleRandomManagerCondition2 = doubleRandomManagerCondition2;
        return this;
    }

    public void setDoubleRandomManagerCondition2(String doubleRandomManagerCondition2) {
        this.doubleRandomManagerCondition2 = doubleRandomManagerCondition2;
    }

    public String getDoubleRandomManagerCondition3() {
        return doubleRandomManagerCondition3;
    }

    public DoubleRandom doubleRandomManagerCondition3(String doubleRandomManagerCondition3) {
        this.doubleRandomManagerCondition3 = doubleRandomManagerCondition3;
        return this;
    }

    public void setDoubleRandomManagerCondition3(String doubleRandomManagerCondition3) {
        this.doubleRandomManagerCondition3 = doubleRandomManagerCondition3;
    }

    public String getDoubleRandomManagerCondition4() {
        return doubleRandomManagerCondition4;
    }

    public DoubleRandom doubleRandomManagerCondition4(String doubleRandomManagerCondition4) {
        this.doubleRandomManagerCondition4 = doubleRandomManagerCondition4;
        return this;
    }

    public void setDoubleRandomManagerCondition4(String doubleRandomManagerCondition4) {
        this.doubleRandomManagerCondition4 = doubleRandomManagerCondition4;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public DoubleRandom tasks(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public DoubleRandom addTask(Task task) {
        tasks.add(task);
        task.setDoubleRandom(this);
        return this;
    }

    public DoubleRandom removeTask(Task task) {
        tasks.remove(task);
        task.setDoubleRandom(null);
        return this;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<DoubleRandomResult> getDoubleRandomResults() {
        return doubleRandomResults;
    }

    public DoubleRandom doubleRandomResults(Set<DoubleRandomResult> doubleRandomResults) {
        this.doubleRandomResults = doubleRandomResults;
        return this;
    }

    public DoubleRandom addDoubleRandomResult(DoubleRandomResult doubleRandomResult) {
        doubleRandomResults.add(doubleRandomResult);
        doubleRandomResult.setDoubleRandom(this);
        return this;
    }

    public DoubleRandom removeDoubleRandomResult(DoubleRandomResult doubleRandomResult) {
        doubleRandomResults.remove(doubleRandomResult);
        doubleRandomResult.setDoubleRandom(null);
        return this;
    }

    public void setDoubleRandomResults(Set<DoubleRandomResult> doubleRandomResults) {
        this.doubleRandomResults = doubleRandomResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DoubleRandom doubleRandom = (DoubleRandom) o;
        if (doubleRandom.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, doubleRandom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DoubleRandom{" +
            "id=" + id +
            ", doubleRandomName='" + doubleRandomName + "'" +
            ", doubleRandomDate='" + doubleRandomDate + "'" +
            ", doubleRandomNotary='" + doubleRandomNotary + "'" +
            ", doubleRandomCompanyCondition1='" + doubleRandomCompanyCondition1 + "'" +
            ", doubleRandomCompanyCondition2='" + doubleRandomCompanyCondition2 + "'" +
            ", doubleRandomCompanyCondition3='" + doubleRandomCompanyCondition3 + "'" +
            ", doubleRandomCompanyCondition4='" + doubleRandomCompanyCondition4 + "'" +
            ", doubleRandomManagerCondition1='" + doubleRandomManagerCondition1 + "'" +
            ", doubleRandomManagerCondition2='" + doubleRandomManagerCondition2 + "'" +
            ", doubleRandomManagerCondition3='" + doubleRandomManagerCondition3 + "'" +
            ", doubleRandomManagerCondition4='" + doubleRandomManagerCondition4 + "'" +
            '}';
    }
}
