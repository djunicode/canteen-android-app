package io.github.djunicode.canteenapp.ResponseObjects;

import java.math.BigInteger;

public class SignUpResponse {
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String division;
    private String department;
    private int admission_year;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAdmission_year(int admission_year) {
        this.admission_year = admission_year;
    }

    public String getUsername() {
        return username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getDivision() {
        return division;
    }

    public String getDepartment() {
        return department;
    }

    public int getAdmission_year() {
        return admission_year;
    }


}
