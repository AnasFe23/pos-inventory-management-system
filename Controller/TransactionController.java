package Controller;

import Model.Product;
import Model.SaleTransaction;
import Main.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TransactionController {
    private SaleTransaction transaction;
    private Connection conn;

    public TransactionController() {
        transaction = new SaleTransaction();
        conn = DbConnection.getConnection(); // use centralized connection
    }

    public void addToCart(Product product, int quantity) {
        transaction.addProduct(product, quantity);
    }

    public void applyDiscount(double percent) {
        transaction.applyDiscount(percent);
    }

    public double getTotalAmount() {
        return transaction.getTotalAmount();
    }

    public double getDiscount() {
        return transaction.getDiscount();
    }

    public ArrayList<Product> getCartItems() {
        return (ArrayList<Product>) transaction.getProducts();
    }

    public SaleTransaction getTransaction() {
        return transaction;
    }
    public void clearCart() {
        transaction.getProducts().clear();
        transaction.applyDiscount(0);
    }

    public boolean saveTransaction(String paymentMethod) {
        try {
            Connection conn = Main.DbConnection.getConnection();
            String sql = "INSERT INTO transactions (total, discount, payment_method) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, transaction.getTotalAmount());
            ps.setDouble(2, transaction.getDiscount());
            ps.setString(3, paymentMethod);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int transactionId = 0;
            if (rs.next()) {
                transactionId = rs.getInt(1);
            }

            for (Product p : transaction.getProducts()) {
                String itemSql = "INSERT INTO transaction_items (transaction_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
                PreparedStatement itemPs = conn.prepareStatement(itemSql);
                itemPs.setInt(1, transactionId);
                itemPs.setInt(2, (p.getId()));
                itemPs.setInt(3, p.getQuantity());
                itemPs.setDouble(4, p.getPrice());
                itemPs.executeUpdate();
            }

            conn.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
