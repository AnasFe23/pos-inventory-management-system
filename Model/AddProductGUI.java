package View;

import Controller.ProductController;
import javax.swing.*;
import java.awt.*;

public class AddProductGUI extends JFrame {
    private JTextField txtName, txtDescription, txtBarcode, txtPrice, txtQuantity, txtReorderLevel;
    private JComboBox<String> cmbCategory, cmbSupplier;
    private ProductController productController;

    public AddProductGUI(ProductController productController) {
        this.productController = productController;

        setTitle("Add New Product");
        setSize(420, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add more vertical padding
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);

        JLabel[] labels = {
            new JLabel("Product Name:"),
            new JLabel("Description:"),
            new JLabel("Barcode:"),
            new JLabel("Category:"),
            new JLabel("Supplier:"),
            new JLabel("Price (RM):"),
            new JLabel("Quantity:"),
            new JLabel("Reorder Level:")
        };

        for (JLabel lbl : labels) lbl.setFont(labelFont);

        txtName = new JTextField(20);
        txtDescription = new JTextField(20);
        txtBarcode = new JTextField(20);
        cmbCategory = new JComboBox<>(new String[]{"Soap", "Body Care", "Skin Care", "Hair Care"});
        cmbSupplier = new JComboBox<>(new String[]{"Dove", "Lux", "Nivea"});
        txtPrice = new JTextField(20);
        txtQuantity = new JTextField(20);
        txtReorderLevel = new JTextField(20);

        Component[] fields = {
            txtName, txtDescription, txtBarcode, cmbCategory,
            cmbSupplier, txtPrice, txtQuantity, txtReorderLevel
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            panel.add(labels[i], gbc);

            gbc.gridx = 1;
            panel.add((Component) fields[i], gbc);
        }

        JButton btnSave = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");

        btnSave.setBackground(new Color(0, 123, 255));
        btnSave.setForeground(Color.WHITE);
        btnCancel.setBackground(Color.GRAY);
        btnCancel.setForeground(Color.WHITE);

        btnSave.setFocusPainted(false);
        btnCancel.setFocusPainted(false);

        btnSave.addActionListener(e -> saveProduct());
        btnCancel.addActionListener(e -> dispose());

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(btnSave);
        btnPanel.add(btnCancel);

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnPanel, gbc);

        add(panel);
        setVisible(true);
    }

    private void saveProduct() {
        try {
            String name = txtName.getText().trim();
            String description = txtDescription.getText().trim();
            String barcode = txtBarcode.getText().trim();
            String category = (String) cmbCategory.getSelectedItem();
            String supplier = (String) cmbSupplier.getSelectedItem();

            if (name.isEmpty() || barcode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in Product Name and Barcode.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double price;
            int quantity, reorderLevel;

            try {
                price = Double.parseDouble(txtPrice.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid price. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                txtPrice.requestFocus();
                return;
            }

            try {
                quantity = Integer.parseInt(txtQuantity.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid quantity. Please enter an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
                txtQuantity.requestFocus();
                return;
            }

            try {
                reorderLevel = Integer.parseInt(txtReorderLevel.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid reorder level. Please enter an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
                txtReorderLevel.requestFocus();
                return;
            }

            boolean success = productController.addProduct(
                name, description, barcode, category, supplier, price, quantity, reorderLevel
            );

            if (success) {
                JOptionPane.showMessageDialog(this, " Product added successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add product.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unexpected error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
