// Taxable interface
interface Taxable {
    double incomeTax = 0.0;
    double salesTax = 0.0;

    double calcTax();
}

// Employee class implementing Taxable interface to calculate income tax
class Employee implements Taxable {
    private double salary;

    public Employee(double salary) {
        this.salary = salary;
    }

    @Override
    public double calcTax() {
        // Assuming a flat 20% income tax rate
        return salary * 0.2;
    }
}

// Product class implementing Taxable interface to calculate sales tax
class Product implements Taxable {
    private double price;

    public Product(double price) {
        this.price = price;
    }

    @Override
    public double calcTax() {
        // Assuming a flat 10% sales tax rate
        return price * 0.1;
    }
}

public class TaxableDemo {
    public static void main(String[] args) {
        // Example usage of Employee class
        Employee employee = new Employee(50000);
        System.out.println("Income Tax for Employee: $" + employee.calcTax());

        // Example usage of Product class
        Product product = new Product(100);
        System.out.println("Sales Tax for Product: $" + product.calcTax());
    }
}
