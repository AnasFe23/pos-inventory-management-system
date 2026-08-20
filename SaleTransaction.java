package Model;

import java.util.ArrayList;
import java.util.List;
public class SaleTransaction {
    private List<Product> products = new ArrayList<>();
    private double discount = 0.0;
    private double totalAmount = 0.0;

    public void addProduct(Product p, int quantity) {
        p.setQuantity(quantity);
        products.add(p);
        calculateTotal(); // important!
    }

    public void applyDiscount(double discount) {
        this.discount = discount;
        calculateTotal(); // important!
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    

    private void calculateTotal() {
        double total = 0.0;
        for (Product p : products) {
            total += p.getPrice() * p.getQuantity();
        }
        // Apply discount
        totalAmount = total - (total * discount / 100.0);
    }
}
