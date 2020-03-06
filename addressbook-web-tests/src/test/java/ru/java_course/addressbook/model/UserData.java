package ru.java_course.addressbook.model;

import java.util.Objects;

public class UserData {
    private int id = Integer.MAX_VALUE;
    private String name;
    private String middlename;
    private String lastname;
    private String nickname;
    private String title;
    private String company;
    private String address;
    private String mobile;
    private String email;
    private String group;

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withName(String name) {
        this.name = name;
        return this;
    }

    public UserData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public UserData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public UserData withTitle(String title) {
        this.title = title;
        return this;
    }

    public UserData withCompany(String company) {
        this.company = company;
        return this;
    }

    public UserData withAddress(String address) {
        this.address = address;
        return this;
    }

    public UserData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserData withGroup(String group) {
        this.group = group;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id &&
                Objects.equals(name, userData.name) &&
                Objects.equals(middlename, userData.middlename) &&
                Objects.equals(lastname, userData.lastname) &&
                Objects.equals(nickname, userData.nickname) &&
                Objects.equals(title, userData.title) &&
                Objects.equals(company, userData.company) &&
                Objects.equals(address, userData.address) &&
                Objects.equals(mobile, userData.mobile) &&
                Objects.equals(email, userData.email) &&
                Objects.equals(group, userData.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, middlename, lastname, nickname, title, company, address, mobile, email, group);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", middlename='" + middlename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
        }
}
