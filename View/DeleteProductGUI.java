package View;

import Controller.ProductController;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DeleteProductGUI extends JFrame {
    private ProductController controller;

    public DeleteProductGUI(ProductController controller) {
        this.controller = controller;

        setTitle("Delete Product");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblList = new JLabel("Available Products:");
        lblList.setBounds(20, 10, 200, 25);
        add(lblList);

        JTextArea txtProducts = new JTextArea();
        txtProducts.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtProducts);
        scroll.setBounds(20, 40, 340, 100);
        add(scroll);

        // Display product list
        List<Product> products = controller.getAllProducts();
        for (Product p : products) {
            txtProducts.append(p.getId() + " - " + p.getName() + "\n");
        }

        JLabel lblId = new JLabel("Product ID to delete:");
        lblId.setBounds(20, 150, 200, 25);
        add(lblId);

        JTextField txtId = new JTextField();
        txtId.setBounds(180, 150, 100, 25);
        add(txtId);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(130, 200, 120, 30);
        add(btnDelete);

        btnDelete.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());

                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete product ID: " + id + "?",
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean deleted = controller.deleteProduct(id);
                    if (deleted) {
                        JOptionPane.showMessageDialog(null, "Product deleted successfully.");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Product not found or could not be deleted.");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID.");
            }
        });

        setVisible(true);
    }
}
