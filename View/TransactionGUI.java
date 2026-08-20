package View;

import Controller.ProductController;
import Controller.TransactionController;
import Model.Product;
import Model.SaleTransaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class TransactionGUI extends JFrame {
    private JTable table;
    private JTextField txtProductId, txtQuantity, txtDiscount;
    private JTextArea txtReceipt;
    private JButton btnAdd, btnDiscount, btnPrint;
    private ProductController productController;
    private TransactionController transactionController;

    public TransactionGUI(ProductController pc, TransactionController tc) {
        this.productController = pc;
        this.transactionController = tc;

        setTitle("Sales Transaction");
        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblTable = new JLabel("Available Products:");
        lblTable.setBounds(20, 10, 200, 20);
        add(lblTable);

        table = new JTable();
        JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.setBounds(20, 30, 740, 150);
        add(scrollTable);
        loadProductTable();

        JLabel lblId = new JLabel("Product ID:");
        lblId.setBounds(20, 200, 80, 25);
        add(lblId);

        txtProductId = new JTextField();
        txtProductId.setBounds(100, 200, 80, 25);
        add(txtProductId);

        JLabel lblQty = new JLabel("Quantity:");
        lblQty.setBounds(200, 200, 80, 25);
        add(lblQty);

        txtQuantity = new JTextField();
        txtQuantity.setBounds(280, 200, 80, 25);
        add(txtQuantity);

        btnAdd = new JButton("Add to Cart");
        btnAdd.setBounds(380, 200, 120, 25);
        add(btnAdd);

        JLabel lblDiscount = new JLabel("Discount (%):");
        lblDiscount.setBounds(20, 240, 100, 25);
        add(lblDiscount);

        txtDiscount = new JTextField("0");
        txtDiscount.setBounds(120, 240, 80, 25);
        add(txtDiscount);

        btnDiscount = new JButton("Apply Discount");
        btnDiscount.setBounds(220, 240, 150, 25);
        add(btnDiscount);

        txtReceipt = new JTextArea();
        JScrollPane scrollReceipt = new JScrollPane(txtReceipt);
        scrollReceipt.setBounds(20, 280, 740, 200);
        add(scrollReceipt);

        btnPrint = new JButton("Print Receipt");
        btnPrint.setBounds(320, 500, 150, 30);
        add(btnPrint);

        JButton btnCalculate = new JButton("Calculate Total");
        btnCalculate.setBounds(150, 500, 150, 30);
        add(btnCalculate);

        // ========== ACTIONS ==========

        btnAdd.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtProductId.getText().trim());
                int qty = Integer.parseInt(txtQuantity.getText().trim());
                Product dbProduct = productController.getProductById(id);

                if (dbProduct != null && qty > 0 && qty <= dbProduct.getQuantity()) {
                    Product cartItem = new Product(
                        dbProduct.getId(),
                        dbProduct.getName(),
                        dbProduct.getPrice(),
                        qty,  // Set the user-specified quantity
                        dbProduct.getCategory(),
                        dbProduct.getSupplier()
                    );
                    transactionController.addToCart(cartItem, qty);
                    txtReceipt.append("Added to Cart: " + dbProduct.getName() + " x " + qty + "\n");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid product or quantity");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values");
            }
        });

        btnDiscount.addActionListener(e -> {
            try {
                double discount = Double.parseDouble(txtDiscount.getText().trim());
                transactionController.applyDiscount(discount);
                txtReceipt.append("Discount Applied: " + discount + "%\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid discount");
            }
        });

        btnPrint.addActionListener(e -> {
            String[] options = {"Cash", "Card"};
            int choice = JOptionPane.showOptionDialog(this, "Select Payment Method", "Payment",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == -1) return;

            String method = options[choice];
            if (method.equals("Card")) {
                String cardNumber = JOptionPane.showInputDialog(this, "Enter Card Number:");
                if (cardNumber == null || cardNumber.trim().length() < 4) {
                    JOptionPane.showMessageDialog(this, "Invalid card");
                    return;
                }
                method = "Card (****" + cardNumber.substring(cardNumber.length() - 4) + ")";
            }

            SaleTransaction tx = transactionController.getTransaction();

            StringBuilder receipt = new StringBuilder();
            receipt.append("=========== RECEIPT ===========\n");
            receipt.append(String.format("%-10s %-5s %-10s %-10s\n", "Product", "Qty", "Price", "Total"));
            receipt.append("----------------------------------------\n");

            double grandTotal = 0;
            for (Product p : tx.getProducts()) {
                double sub = p.getPrice() * p.getQuantity();
                receipt.append(String.format("%-10s %-5d RM%-9.2f RM%-10.2f\n",
                        p.getName(), p.getQuantity(), p.getPrice(), sub));
                grandTotal += sub;
            }

            receipt.append("----------------------------------------\n");
            receipt.append(String.format("Discount:  %.2f\n", tx.getDiscount()));
            receipt.append(String.format("Total:    RM%.2f\n", tx.getTotalAmount()));
            receipt.append("Payment Method: " + method + "\n");
            receipt.append("========================================\n");

            txtReceipt.setText(receipt.toString());

            boolean saved = transactionController.saveTransaction(method);
            if (saved) {
                for (Product p : tx.getProducts()) {
                    Product dbProduct = productController.getProductById(p.getId());
                    if (dbProduct != null) {
                        // Correct calculation: subtract sold quantity from current database quantity
                        int updatedQty = dbProduct.getQuantity() - p.getQuantity();
                        if (updatedQty < 0) updatedQty = 0;
                        productController.updateProductQuantity(p.getId(), updatedQty);
                    }
                }
                loadProductTable();
                transactionController.clearCart();
            
            }
            JOptionPane.showMessageDialog(this, saved ? "Transaction saved" : "Transaction failed");
        });

        btnCalculate.addActionListener(e -> {
            SaleTransaction tx = transactionController.getTransaction();
            StringBuilder calc = new StringBuilder();
            calc.append("\n=========== CALCULATION ===========\n");
            calc.append(String.format("%-20s %-8s %-10s %-10s\n", "Product", "Qty", "Price", "Total"));

            double grandTotal = 0.0;
            for (Product p : tx.getProducts()) {
                double subtotal = p.getPrice() * p.getQuantity();
                grandTotal += subtotal;
                calc.append(String.format("%-20s %-8d RM%-10.2f RM%-10.2f\n",
                        p.getName(), p.getQuantity(), p.getPrice(), subtotal));
            }

            calc.append("-----------------------------------\n");
            calc.append(String.format("Discount:  %.2f\n", tx.getDiscount()));
            calc.append(String.format("Total:    RM%.2f\n", tx.getTotalAmount()));
            calc.append("===================================\n");

            txtReceipt.setText(calc.toString());
        });

        setVisible(true);
    }

    private void loadProductTable() {
        List<Product> products = productController.getAllProducts();
        String[] columns = {"ID", "Name", "Price", "Quantity", "Category", "Supplier"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Product p : products) {
            model.addRow(new Object[]{
                    p.getId(), p.getName(), p.getPrice(), p.getQuantity(), p.getCategory(), p.getSupplier()
            });
        }
        table.setModel(model);
    }
}
