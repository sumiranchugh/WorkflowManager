package com.atlas.processes.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Sumiran Chugh on 1/12/2016.
 */
@Entity
public class Applicant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long applicantid;

    private String name;

    private String email;

    private String phone;

    public Applicant(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
