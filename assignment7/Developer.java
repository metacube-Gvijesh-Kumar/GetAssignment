package assignment7;

public class Developer extends Employee {
    private double basicSalary;

    public Developer(String name, int id, double basicSalary) {
        super(name, id);
        this.basicSalary = basicSalary;
    }

    @Override
    public double getBasicSalary() {
        return basicSalary;
    }

    @Override
    public double getBonus() {
        return basicSalary * 0.1; // Example bonus calculation
    }

    @Override
    public double getCompensation() {
        return getBasicSalary() + getBonus();
    }
}
