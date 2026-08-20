package View;

import Controller.ProductController;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EditProductGUI extends JFrame {
    private JTextField txtId, txtName, txtPrice, txtQty;
    private JTextArea txtProductList;

    public EditProductGUI(ProductController controller) {
        setTitle("Edit Product");
        setSize(300, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        // Product List Area
        txtProductList = new JTextArea();
        txtProductList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtProductList);
        scrollPane.setBounds(20, 10, 240, 100);
        add(scrollPane);

        // Load Products
        List<Product> products = controller.getAllProducts();
        for (Product p : products) {
            txtProductList.append(p.getId() + " - " + p.getName() + "\n");
        }

        // Labels and TextFields
        JLabel lblId = new JLabel("Product ID:");
        lblId.setBounds(20, 120, 100, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(130, 120, 120, 25);
        add(txtId);

        JLabel lblName = new JLabel("New Name:");
        lblName.setBounds(20, 150, 100, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(130, 150, 120, 25);
        add(txtName);

        JLabel lblPrice = new JLabel("New Price:");
        lblPrice.setBounds(20, 180, 100, 25);
        add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(130, 180, 120, 25);
        add(txtPrice);

        JLabel lblQty = new JLabel("New Quantity:");
        lblQty.setBounds(20, 210, 100, 25);
        add(lblQty);

        txtQty = new JTextField();
        txtQty.setBounds(130, 210, 120, 25);
        add(txtQty);

        // Buttons
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(40, 250, 90, 30);
        add(btnUpdate);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(150, 250, 90, 30);
        add(btnClear);

        // Update Action
        btnUpdate.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String name = txtName.getText().trim();
                String priceText = txtPrice.getText().trim();
                String qtyText = txtQty.getText().trim();

                // Validate name is not empty and does not contain numbers (optional)
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Name cannot be empty.");
                    return;
                }

                // Validate price
                if (!priceText.matches("\\d+(\\.\\d+)?")) {
                    JOptionPane.showMessageDialog(null, "Invalid price. Use numbers only.");
                    return;
                }

                // Validate quantity
                if (!qtyText.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "Invalid quantity. Use whole numbers only.");
                    return;
                }

                double price = Double.parseDouble(priceText);
                int quantity = Integer.parseInt(qtyText);

                boolean updated = controller.updateProduct(id, name, price, quantity);
                if (updated) {
                    JOptionPane.showMessageDialog(null, "Product updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update product.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numeric input for ID.");
            }
        });


        setVisible(true);
    }
}
