package io.github.djunicode.canteenapp.RequestObjects;

public class SignUpRequest {

    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String division;
    private String department;
    private int admission_year;

    public SignUpRequest(String username, String password, String first_name, String last_name, String email, String phone_number, String division, String department, int admission_year) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.department = department;
        this.division = division;
        this.admission_year = admission_year;
    }


}
