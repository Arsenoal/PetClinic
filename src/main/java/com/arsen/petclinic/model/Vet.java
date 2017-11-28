package com.arsen.petclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "vets")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vet {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;

    @Column(name = "owner_id")
    @JsonProperty("owner_id")
    private int ownerId;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "age")
    @JsonProperty("age")
    private int age;

    @Column(name = "sex")
    @JsonProperty("sex")
    private String sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
