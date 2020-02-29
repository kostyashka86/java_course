package ru.java_course.addressbook.model;

import java.util.Objects;

public class UserData {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return Objects.equals(name, userData.name) &&
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
        return Objects.hash(name, middlename, lastname, nickname, title, company, address, mobile, email, group);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", middlename='" + middlename + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public UserData(String name, String middlename, String lastname, String nickname, String title, String company,
                    String address, String mobile, String email, String group) {
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
