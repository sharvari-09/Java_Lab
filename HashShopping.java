import java.util.*;

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

class ShoppingCart {
    private Map<Product, Integer> items;

    public ShoppingCart() {
        items = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public void removeProduct(Product product, int quantity) {
        if (items.containsKey(product)) {
            int currentQuantity = items.get(product);
            if (quantity >= currentQuantity) {
                items.remove(product);
            } else {
                items.put(product, currentQuantity - quantity);
            }
        }
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }
}

class Customer {
    private String name;
    private ShoppingCart cart;

    public Customer(String name) {
        this.name = name;
        this.cart = new ShoppingCart();
    }

    public void addToCart(Product product, int quantity) {
        cart.addProduct(product, quantity);
    }

    public void removeFromCart(Product product, int quantity) {
        cart.removeProduct(product, quantity);
    }

    public Order placeOrder() {
        return new Order(this, cart.getItems());
    }

    public String getName() {
        return name;
    }
}

class Order {
    private Customer customer;
    private Map<Product, Integer> items;

    public Order(Customer customer, Map<Product, Integer> items) {
        this.customer = customer;
        this.items = items;
    }

    public double getTotalBill() {
        double totalBill = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            totalBill += entry.getKey().getPrice() * entry.getValue();
        }
        return totalBill;
    }

    public void displayOrderDetails() {
        System.out.println("Customer: " + customer.getName());
        System.out.println("Order details:");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("- " + product.getName() + ": $" + product.getPrice() + " x " + quantity);
        }
        System.out.println("Total Bill: $" + getTotalBill());
    }
}

public class HashShopping {
    public static void main(String[] args) {
        Product laptop = new Product("Laptop", 800);
        Product phone = new Product("Phone", 500);
        Product headphones = new Product("Headphones", 100);

        Customer customer = new Customer("John Doe");

        customer.addToCart(laptop, 1);
        customer.addToCart(phone, 2);
        customer.addToCart(headphones, 1);

        Order order = customer.placeOrder();
        order.displayOrderDetails();
    }
}
