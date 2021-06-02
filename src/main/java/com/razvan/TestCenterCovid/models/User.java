package com.razvan.TestCenterCovid.models;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @Column( nullable = false, length = 100)
    private String name;
    @Column( nullable = false, length = 10)
    private String phoneNumber;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(length = 100)
    private String roles = "ROLE_USER";

    @Column (nullable = false, unique = true, length = 10)
    private String CPR;
    @Column(nullable = false, length = 64)
    private String password;


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCPR() {
        return CPR;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
