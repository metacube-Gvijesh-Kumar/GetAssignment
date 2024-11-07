package test.java;

import org.junit.jupiter.api.Test;

import assignment7.Department;
import assignment7.Organization;
import assignment7.Payroll;
import assignment7.Employee;
import assignment7.Developer;


import static org.junit.jupiter.api.Assertions.*;

class Test7 {
    @Test
    void testPayroll() {
        Organization org = new Organization();
        Department devDept = new Department("Development");
        Department hrDept = new Department("HR");

        Employee dev1 = new Developer("Alice", 1, 50000);
        Employee dev2 = new Developer("Bob", 2, 60000);

        devDept.join(dev1);
        devDept.join(dev2);

        org.addDepartment(devDept);
        org.addDepartment(hrDept);

        Payroll payroll = new Payroll();
        payroll.printSalarySlips(org);

        assertEquals(2, org.getAllEmployees().size());
    }
}