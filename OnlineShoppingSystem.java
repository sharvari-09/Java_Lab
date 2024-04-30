import java.util.ArrayList;
import java.util.List;

// Product class representing items in the online store
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

// Customer class representing a customer of the online store
class Customer {
    private String name;
    private String email;
    private ShoppingCart cart;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.cart = new ShoppingCart();
    }

    public void addToCart(Product product) {
        cart.addProduct(product);
    }

    public void removeFromCart(Product product) {
        cart.removeProduct(product);
    }

    public Order placeOrder() {
        Order order = new Order(cart.getProducts(), this);
        cart.clear(); // Empty the cart after placing the order
        return order;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

// ShoppingCart class representing the shopping cart of a customer
class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void clear() {
        products.clear();
    }
}

// Order class representing an order placed by a customer
class Order {
    private List<Product> products;
    private Customer customer;

    public Order(List<Product> products, Customer customer) {
        this.products = products;
        this.customer = customer;
    }

    public double getTotalBill() {
        double totalBill = 0;
        for (Product product : products) {
            totalBill += product.getPrice();
        }
        return totalBill;
    }

    public void displayOrderDetails() {
        System.out.println("Customer: " + customer.getName() + " (" + customer.getEmail() + ")");
        System.out.println("Order details:");
        for (Product product : products) {
            System.out.println("- " + product.getName() + ": $" + product.getPrice());
        }
        System.out.println("Total Bill: $" + getTotalBill());
    }
}

// Main class to test the online shopping system
public class OnlineShoppingSystem {
    public static void main(String[] args) {
        // Create some products
        Product laptop = new Product("Laptop", 800);
        Product phone = new Product("Phone", 500);
        Product headphones = new Product("Headphones", 100);

        // Create a customer
        Customer customer = new Customer("John Doe", "john@example.com");

        // Add products to customer's cart
        customer.addToCart(laptop);
        customer.addToCart(phone);
        customer.addToCart(headphones);

        // Remove a product from customer's cart
        customer.removeFromCart(headphones);

        // Place order
        Order order = customer.placeOrder();
        order.displayOrderDetails();
    }
}
