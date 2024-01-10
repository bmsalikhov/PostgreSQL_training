package data_source;

import models.Employee;
import models.Salary;

import java.sql.*;
import java.util.List;

public class DbManager {
    private final String dbName;
    private final String user;
    private final String password;
    private final int port;
    private final String dbUrl;

    public DbManager(String dbName, String user, String password, int port) {
        this.dbName = dbName;
        this.user = user;
        this.password = password;
        this.port = port;
        this.dbUrl = "jdbc:postgresql://localhost:" + this.port + "/" + this.dbName;
    }

    // create table of employees
    public void createEmpsTable(String tableName) {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.user, this.password)) {
            String query = "CREATE TABLE if not exists \"" + tableName + "\" (" +
                    "id int," +
                    " first_name varchar(20)," +
                    " last_name varchar(20)," +
                    " email varchar(20)," +
                    " phone_number varchar(20)," +
                    " hire_date date," +
                    " job_id varchar(20)," +
                    " primary key(id)" +
                    ");";
            System.out.println("QUERY: " + query + "\n");
            connection.createStatement().executeUpdate(query);
            System.out.println("Successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
    }

    // create table of salaries
    public void createSalariesTable(String tableName, String empsTableName) {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.user, this.password)) {
            String query = "CREATE TABLE if not exists \"" + tableName + "\" (" +
                    "id int," +
                    " emp_id int," +
                    " salary int," +
                    " primary key(id)," +
                    " constraint \"fk_" + empsTableName + "_" + tableName + "\"" +
                    " foreign key (\"" + "emp_id" + "\")" +
                    " references \"" + empsTableName + "\" (id)" +
                    ");";
            System.out.println("QUERY: " + query + "\n");
            connection.createStatement().executeUpdate(query);
            System.out.println("Successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
    }

    // insert list of employees
    public void insertEmployees(List<Employee> employeeList, String tableName) {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.user, this.password)) {
            for (Employee employee : employeeList) {
                String query = "INSERT INTO " + tableName +
                        " (id, first_name, last_name, email, phone_number, hire_date, job_id)" +
                        " VALUES (" +
                        employee.getEmployeeId() + ", " +
                        "'" + employee.getFirstName() + "', " +
                        "'" + employee.getLastName() + "', " +
                        "'" + employee.getEmail() + "', " +
                        "'" + employee.getPhoneNumber() + "', " +
                        "'" + employee.getHireDate() + "', " +
                        "'" + employee.getJobId() + "'" +
                        ");";
                System.out.println("QUERY: " + query + "\n");
                connection.createStatement().executeUpdate(query);
                System.out.println("Successfully!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
    }

    // insert list of salaries
    public void insertSalaries(List<Salary> salaries, String tableName) {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.user, this.password)) {
            for (Salary salary : salaries) {
                String query = "INSERT INTO " + tableName +
                        " (id, emp_id, salary)" +
                        " VALUES (" +
                        salary.getId() + ", " +
                        salary.getEmpId() + ", " +
                        salary.getSalary() +
                        ");";
                System.out.println("QUERY: " + query + "\n");
                connection.createStatement().executeUpdate(query);
                System.out.println("Successfully!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
    }

    // select first_name and id
    public void selectAll(String tableName) {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.user, this.password)) {
            String query = "SELECT id, first_name" +
                    " FROM " + tableName + ";";
            System.out.println("QUERY: " + query + "\n");
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            System.out.println(resultSetMetaData.getColumnLabel(1) + getSpaces(20, resultSetMetaData.getColumnLabel(1)) + resultSetMetaData.getColumnLabel(2) + "\n");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + getSpaces(20, String.valueOf(resultSet.getInt("id"))) + resultSet.getString("first_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // select last_name and hire_date
    public void selectAllAgain(String tableName) {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.user, this.password)) {
            String query = "SELECT last_name, hire_date" +
                    " FROM " + tableName + ";";
            System.out.println("QUERY: " + query + "\n");
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            System.out.println(resultSetMetaData.getColumnLabel(1) + getSpaces(20, resultSetMetaData.getColumnLabel(1)) + resultSetMetaData.getColumnLabel(2) + "\n");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("last_name") + getSpaces(20, resultSet.getString("last_name")) + resultSet.getDate("hire_date"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
    }

    // select first_name and last_name ordered by ID (DESC)
    public void selectAllOrder(String tableName) {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.user, this.password)) {
            String query = "SELECT first_name, last_name" +
                    " FROM " + tableName +
                    " ORDER BY id DESC;";
            System.out.println("QUERY: " + query + "\n");
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            System.out.println(resultSetMetaData.getColumnLabel(1) + getSpaces(20, resultSetMetaData.getColumnLabel(1)) + resultSetMetaData.getColumnLabel(2) + "\n");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("first_name") + getSpaces(20, resultSet.getString("first_name")) + resultSet.getString("last_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
    }

    // select all emps with their salary
    public void selectSals(String empTableName, String empSalTableName) {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.user, this.password)) {
            String query = "SELECT e.\"first_name\", e.\"last_name\", s.\"salary\"" +
                    " FROM \"" + empTableName + "\" e" +
                    " LEFT JOIN \"" + empSalTableName + "\" s" +
                    " ON e.id = s.emp_id;";
            System.out.println("QUERY: " + query + "\n");
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            StringBuilder builder = new StringBuilder();
            builder.append(resultSetMetaData.getColumnLabel(1));
            builder.append(getSpaces(20, resultSetMetaData.getColumnLabel(1)));
            builder.append(resultSetMetaData.getColumnLabel(2));
            builder.append(getSpaces(20, resultSetMetaData.getColumnLabel(2)));
            builder.append(resultSetMetaData.getColumnLabel(3));
            builder.append("\n");

            while (resultSet.next()) {
                 builder.append(resultSet.getString(1));
                 builder.append(getSpaces(20, resultSet.getString(1)));
                 builder.append(resultSet.getString(2));
                 builder.append(getSpaces(20, resultSet.getString(2)));
                 builder.append(resultSet.getInt(3));
                 builder.append("\n");
            }

            System.out.println(builder);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // служебный метод для красивого вывода данных
    private String getSpaces(int colSize, String s) {
        StringBuilder builder = new StringBuilder();
        int n = colSize - s.length();
        while (n > 0) {
            builder.append(" ");
            n--;
        }
        return builder.toString();
    }
}
