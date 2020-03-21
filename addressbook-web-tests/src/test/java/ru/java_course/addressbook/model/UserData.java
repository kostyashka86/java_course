package ru.java_course.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class UserData {

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String firstname;

    @Expose
    @Column(name = "lastname")
    private String lastname;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;

    @Transient
    private String allPhones;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String emailTwo;

    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String emailThree;

    @Transient
    private String allEmails;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public UserData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public UserData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public UserData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserData withEmailTwo(String emailTwo) {
        this.emailTwo = emailTwo;
        return this;
    }

    public UserData withEmailThree(String emailThree) {
        this.emailThree = emailThree;
        return this;
    }

    public UserData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public UserData withAddress(String address) {
        this.address = address;
        return this;
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailTwo() {
        return emailTwo;
    }

    public String getEmailThree() {
        return emailThree;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getAddress() {
        return address;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id &&
                Objects.equals(firstname, userData.firstname) &&
                Objects.equals(lastname, userData.lastname) &&
                Objects.equals(mobilePhone, userData.mobilePhone) &&
                Objects.equals(homePhone, userData.homePhone) &&
                Objects.equals(workPhone, userData.workPhone) &&
                Objects.equals(allPhones, userData.allPhones) &&
                Objects.equals(email, userData.email) &&
                Objects.equals(emailTwo, userData.emailTwo) &&
                Objects.equals(emailThree, userData.emailThree) &&
                Objects.equals(allEmails, userData.allEmails) &&
                Objects.equals(address, userData.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, mobilePhone, homePhone, workPhone, allPhones, email, emailTwo, emailThree, allEmails, address);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", allPhones='" + allPhones + '\'' +
                ", email='" + email + '\'' +
                ", emailTwo='" + emailTwo + '\'' +
                ", emailThree='" + emailThree + '\'' +
                ", allEmails='" + allEmails + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public UserData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
