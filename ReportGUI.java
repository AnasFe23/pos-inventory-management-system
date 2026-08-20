package View;

import Controller.ProductController;
import Model.Product;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class ReportGUI extends JFrame {
    private JTextArea txtReport;
    private JComboBox<String> cmbFilter;

    public ReportGUI(ProductController pc) {
        setTitle("Daily/Weekly Report");
        setSize(400, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblFilter = new JLabel("Filter by:");
        lblFilter.setBounds(20, 10, 100, 25);
        add(lblFilter);

        cmbFilter = new JComboBox<>(new String[]{"All", "Today", "Last 7 Days"});
        cmbFilter.setBounds(100, 10, 150, 25);
        add(cmbFilter);

        txtReport = new JTextArea();
        JScrollPane scroll = new JScrollPane(txtReport);
        scroll.setBounds(20, 50, 340, 220);
        add(scroll);

        JButton btnLoad = new JButton("Load Report");
        btnLoad.setBounds(20, 280, 140, 30);
        add(btnLoad);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(200, 280, 140, 30);
        add(btnClear);

        btnLoad.addActionListener(e -> {
            txtReport.setText("PRODUCT REPORT:\n\n");
            List<Product> products = pc.getAllProducts(); // Assuming same method
            double totalRevenue = 0;
            int totalQty = 0;

            for (Product p : products) {
                txtReport.append(p.getId() + " - " + p.getName() +
                        " - $" + p.getPrice() +
                        " - Qty: " + p.getQuantity() + "\n");

                totalRevenue += p.getPrice() * p.getQuantity();
                totalQty += p.getQuantity();
            }

            txtReport.append("\n-------------------------------------\n");
            txtReport.append("Total Quantity Sold: " + totalQty + "\n");
            txtReport.append("Total Revenue: $" + totalRevenue + "\n");
        });

        btnClear.addActionListener(e -> txtReport.setText(""));

        setVisible(true);
    }
}
