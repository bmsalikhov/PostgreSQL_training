package main;

import data_source.DbManager;
import models.Employee;
import models.Salary;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // инициализируем данные для дальнейшей работы
        List<Employee> employees = getEmps();
        List<Salary> salaries = getSalaries();

        // создаем названия таблиц для работы
        Scanner scanner = new Scanner(System.in);
        System.out.print("введите название таблицы с работниками: ");
        String empTableName = scanner.nextLine();
        System.out.print("введите название таблицы с зарплатами сотрудников: ");
        String empSalaryTableName = scanner.nextLine();

        // создаем DBmanager для взаимодействия с базой
        DbManager dbManager = new DbManager("somedb", "someuser", "somepass", 5432);

        // создаем таблицы и заносим туда данные
        dbManager.createEmpsTable(empTableName);
        dbManager.createSalariesTable(empSalaryTableName, empTableName);

        dbManager.insertEmployees(employees, empTableName);
        dbManager.insertSalaries(salaries, empSalaryTableName);

        // задание 3 - вывод всех имен и ID
        dbManager.selectAll(empTableName);

        // задание 4 - вывод фамилии и даты трудоустройства
        dbManager.selectAllAgain(empTableName);

        // задание 5 - вывод имени и фамилии в порядке убывания по ID
        dbManager.selectAllOrder(empTableName);

        // доп. задание 6 - тренируем left join - выводим фамилию+имя и зарплату
        dbManager.selectSals(empTableName, empSalaryTableName);

    }

    public static List<Employee> getEmps() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(100, "Steven", "King", "SKING", "515.123.4567", Date.valueOf("1987-06-17"), "AD_PRES"));
        employees.add(new Employee(101, "Neena", "Konchar", "NIKONCHAR", "515.123.4568", Date.valueOf("1987-06-18"), "AD_VP"));
        employees.add(new Employee(102, "Lex", "De Haan", "LDEHAAN", "515.123.4569", Date.valueOf("1987-06-19"), "AD_VP"));
        employees.add(new Employee(103, "Alexander", "Hunold", "AHUNOLD", "515.123.4570", Date.valueOf("1987-06-20"), "IT_PROG"));
        employees.add(new Employee(104, "Bruce", "Ernst", "BERNST", "515.123.4571", Date.valueOf("1997-06-21"), "IT_PROG"));
        employees.add(new Employee(105, "David", "Austin", "DAUSTIN", "515.123.4572", Date.valueOf("1987-06-22"), "IT_PROG"));
        employees.add(new Employee(106, "Valli", "Pataballa", "VPATABAL", "515.123.4573", Date.valueOf("1988-06-17"), "IT_PROG"));
        return employees;
    }

    public static List<Salary> getSalaries() {
        ArrayList<Salary> salaries = new ArrayList<>();
        salaries.add(new Salary(1, 100, 34000));
        salaries.add(new Salary(2, 101, 24000));
        salaries.add(new Salary(3, 102, 24000));
        salaries.add(new Salary(4, 103, 9000));
        salaries.add(new Salary(5, 104, 9000));
        salaries.add(new Salary(6, 105, 9000));
        salaries.add(new Salary(7, 106, 9000));
        return salaries;
    }
}
