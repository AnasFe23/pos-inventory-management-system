package View;

import Controller.ProductController;
import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ProductInventoryGUI extends JFrame {
    private JTable table;
    private JLabel lblOverview;

    public ProductInventoryGUI(ProductController controller) {
        setTitle("Product Inventory Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        // Top menu
        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(20, 20, 100, 30);
        add(btnSearch);

        JButton btnAdd = new JButton("Add Product");
        btnAdd.setBounds(130, 20, 130, 30);
        add(btnAdd);

        JButton btnCategory = new JButton("Category");
        btnCategory.setBounds(270, 20, 120, 30);
        add(btnCategory);

        JButton btnSupplier = new JButton("Supplier");
        btnSupplier.setBounds(400, 20, 120, 30);
        add(btnSupplier);

        // Table with scroll
        String[] columns = {"ID", "Name", "Price", "Quantity", "Category"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 70, 740, 200);
        add(scrollPane);

        // Load data
        ArrayList<Product> products = (ArrayList<Product>) controller.getAllProducts();
        for (Product p : products) {
            model.addRow(new Object[]{
                p.getId(),
                p.getName(),
                String.format("RM %.2f", p.getPrice()),
                p.getQuantity(),
                "Category"  // Placeholder until category added
            });
        }

        JLabel lblShowing = new JLabel("Showing 1–10 of " + products.size() + " products");
        lblShowing.setBounds(20, 280, 300, 25);
        add(lblShowing);

        // Overview section
        JLabel lblInventory = new JLabel("INVENTORY OVERVIEW");
        lblInventory.setFont(new Font("Arial", Font.BOLD, 14));
        lblInventory.setBounds(20, 320, 300, 25);
        add(lblInventory);

        lblOverview = new JLabel("Total Product: " + products.size() + "     Low Stock Items: " + countLowStock(products));
        lblOverview.setBounds(20, 350, 400, 25);
        add(lblOverview);

        JTextArea txtStock = new JTextArea();
        txtStock.setEditable(false);
        txtStock.setBounds(20, 380, 350, 120);
        txtStock.setText("[Stock Levels]\nSoap: 129\nSkin Care: 90\nBody Care: 67\nHair Care: 55");
        add(txtStock);

        JTextArea txtActivity = new JTextArea();
        txtActivity.setEditable(false);
        txtActivity.setBounds(400, 380, 350, 120);
        txtActivity.setText("[Recent Activity]\n25/05 - Added 13 Soap\n25/05 - Sold 15 Face Mask\n25/05 - Added 34 Body Lotion\n25/05 - Price change Hair Oil");
        add(txtActivity);

        JButton btnGenerate = new JButton("Generate Report");
        btnGenerate.setBounds(20, 520, 150, 30);
        add(btnGenerate);

        JButton btnExport = new JButton("Export Data");
        btnExport.setBounds(180, 520, 150, 30);
        add(btnExport);

        setVisible(true);
    }

    private int countLowStock(ArrayList<Product> products) {
        int count = 0;
        for (Product p : products) {
            if (p.getQuantity() < 10) count++;
        }
        return count;
    }
}
