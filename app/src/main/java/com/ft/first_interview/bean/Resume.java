package com.ft.first_interview.bean;

import java.util.ArrayList;
import java.util.Objects;

public class Resume {
    private Boolean show_or_hide;
    private String name;
    private String sex;
    private String age;
    private P_C_D nativeadd;
    private P_C_D address;
    private String phone;
    private String school;
    private String major;
    private String record;
    private String graduation;
    private P_C_D place;
    private String position;
    private String salary;
    private String certification;
    private String self;
    private ArrayList<String> imgUrlList;
    private String videlUrl;
    private String posttime;

    private String workplace;

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public Boolean getShow_or_hide() {
        return show_or_hide;
    }

    public void setShow_or_hide(Boolean show_or_hide) {
        this.show_or_hide = show_or_hide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public P_C_D getNativeadd() {
        return nativeadd;
    }

    public void setNativeadd(P_C_D nativeadd) {
        this.nativeadd = nativeadd;
    }

    public P_C_D getAddress() {
        return address;
    }

    public void setAddress(P_C_D address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public P_C_D getPlace() {
        return place;
    }

    public void setPlace(P_C_D place) {
        this.place = place;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public ArrayList<String> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(ArrayList<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }

    public String getVidelUrl() {
        return videlUrl;
    }

    public void setVidelUrl(String videlUrl) {
        this.videlUrl = videlUrl;
    }

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }
}
