package assignment7;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private List<Department> departments;

    public Organization() {
        this.departments = new ArrayList<>();
    }

    public boolean addDepartment(Department department) {
        return departments.add(department);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> allEmployees = new ArrayList<>();
        for (Department department : departments) {
            allEmployees.addAll(department.getEmployees());
        }
        return allEmployees;
    }
}