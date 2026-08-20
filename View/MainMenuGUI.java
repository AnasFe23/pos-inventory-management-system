package View;

import Controller.ProductController;
import Controller.TransactionController;

import javax.swing.*;

public class MainMenuGUI extends JFrame {
    public MainMenuGUI(ProductController pc, TransactionController tc) {
        setTitle("POS Main Menu");
        setSize(400, 360);
        setLayout(null);
        setLocationRelativeTo(null);  // Center the window
        
        JButton btnAdd = new JButton("Add Product");
        btnAdd.setBounds(100, 30, 200, 30);
        add(btnAdd);

        JButton btnEdit = new JButton("Edit Product");
        btnEdit.setBounds(100, 70, 200, 30);
        add(btnEdit);

        JButton btnDelete = new JButton("Delete Product");
        btnDelete.setBounds(100, 110, 200, 30);
        add(btnDelete);

        JButton btnTransaction = new JButton("New Transaction");
        btnTransaction.setBounds(100, 150, 200, 30);
        add(btnTransaction);

        JButton btnReport = new JButton("View Report");
        btnReport.setBounds(100, 190, 200, 30);
        add(btnReport);

        JButton btnPayment = new JButton();
        add(btnPayment);

        btnAdd.addActionListener(e -> new AddProductGUI(pc));
        btnEdit.addActionListener(e -> new EditProductGUI(pc));
        btnDelete.addActionListener(e -> new DeleteProductGUI(pc));
        btnTransaction.addActionListener(e -> new TransactionGUI(pc, tc));
        btnReport.addActionListener(e -> new ReportGUI(pc));
        btnPayment.addActionListener(e -> new PaymentGUI(tc));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
