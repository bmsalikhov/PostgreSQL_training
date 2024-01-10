package models;

import java.sql.Date;

public class Employee {
    private final int employeeId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final Date hireDate;
    private final String jobId;

    public Employee(int employeeId, String firstName, String lastName, String email, String phoneNumber, Date hireDate, String jobId) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.jobId = jobId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public String getJobId() {
        return jobId;
    }
}