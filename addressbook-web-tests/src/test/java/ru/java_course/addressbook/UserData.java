package ru.java_course.addressbook;

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
    private final String bday;
    private final String bmonth;
    private final String byear;

    public UserData(String name, String middlename, String lastname, String nickname, String title, String company, String address, String mobile, String email, String bday, String bmonth, String byear) {
        this.name = name;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.bday = bday;
        this.bmonth = bmonth;
        this.byear = byear;
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

    public String getBday() {
        return bday;
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getByear() {
        return byear;
    }
}
