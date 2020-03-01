package ru.java_course.addressbook.model;

import java.util.Objects;

public class UserData {
    private int id;
    private final String name;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String title;
    private final String company;
    private final String address;
    private final String mobile;
    private final String email;
    private String group;


    public UserData(String name, String middlename, String lastname, String nickname, String title, String company,
                    String address, String mobile, String email, String group) {
        this.id = 0;
        this.name = name;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }

    public UserData(int id, String name, String middlename, String lastname, String nickname, String title, String company,
                    String address, String mobile, String email, String group) {
        this.id = id;
        this.name = name;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
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

    public void setId(int id) {
        this.id = id;
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
