package com.ft.first_interview.bean;

import java.util.ArrayList;

public class Job {
    private Company company;
    private String companyname;
    private String job_title;
    private String job_position;
    private String job_place;
    private String job_salary;
    private String job_describe;
    private String posttime;
    private ArrayList<String> job_tag;

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getJob_title() {return job_title;}
    public void setJob_title(String job_title) {this.job_title = job_title;}

    public void setJob_position(String job_position) {
        this.job_position = job_position;
    }

    public String getJob_position() {
        return job_position;
    }

    public String getJob_place() {return job_place;}
    public void setJob_place(String job_place) {this.job_place = job_place;}
    public String getJob_salary() {return job_salary;}
    public void setJob_salary(String job_salary) {this.job_salary = job_salary;}
    public String getJob_describe() {return job_describe;}
    public void setJob_describe(String job_describe) {this.job_describe = job_describe;}
    public String getPosttime() {return posttime;}
    public void setPosttime(String posttime) {this.posttime = posttime;}
    public ArrayList<String> getJob_tag() {return job_tag;}
    public void setJob_tag(ArrayList<String> job_tag) {this.job_tag = job_tag;}
}
