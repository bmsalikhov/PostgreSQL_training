package models;

public class Salary {
    private final int id;
    private final int empId;
    private final int salary;

    public Salary(int id, int empId, int salary) {
        this.id = id;
        this.empId = empId;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public int getEmpId() {
        return empId;
    }

    public int getSalary() {
        return salary;
    }
}