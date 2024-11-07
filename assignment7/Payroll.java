package assignment7;

public class Payroll {
    
    public void printSalarySlips(Organization organization) {
        
        for (Employee employee : organization.getAllEmployees()) {
            
            double tax = calculateTax(employee.getCompensation());
            double netSalary = employee.getCompensation() - tax;
            
            System.out.println("Salary Slip for " + employee.getName() + ":");
            System.out.println("Basic Salary: " + employee.getBasicSalary());
            System.out.println("Bonus: " + employee.getBonus());
            System.out.println("Compensation: " + employee.getCompensation());
            System.out.println("Tax: " + tax);
            System.out.println("Net Salary: " + netSalary);
            System.out.println("---------------------------");
        }
        
    }

    private double calculateTax(double compensation) {
        return compensation * 0.2; // Example tax calculation
    }
}