package management.auto.db;

import management.auto.employee.Employee;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Database dbInstance;
    private final List<Employee> employees = new ArrayList<>();

    // Private constructor to prevent instantiation from outside
    private Database() {
    }

    public static synchronized Database getInstance() {
        if (dbInstance == null) {
            dbInstance = new Database();
        }
        return dbInstance;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
