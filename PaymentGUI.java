package View;

import javax.swing.*;
import Controller.TransactionController;
public class PaymentGUI extends JFrame {
    public PaymentGUI(TransactionController tc) {
        setTitle("Payment Simulation");
        setSize(300, 200);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblAmount = new JLabel("Amount to Pay:");
        lblAmount.setBounds(20, 30, 120, 25);
        add(lblAmount);

        JTextField txtAmount = new JTextField();
        txtAmount.setBounds(140, 30, 120, 25);
        add(txtAmount);

        JButton btnCash = new JButton("Pay Cash");
        btnCash.setBounds(40, 80, 100, 30);
        add(btnCash);

        JButton btnCard = new JButton("Pay Card");
        btnCard.setBounds(160, 80, 100, 30);
        add(btnCard);

        btnCash.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Payment accepted (Cash).");
            boolean saved = tc.saveTransaction("Cash");
            if (saved) {
                JOptionPane.showMessageDialog(null, "Transaction saved.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save transaction.");
            }
        });

        btnCard.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Payment accepted (Card).");
            boolean saved = tc.saveTransaction("Card");
            if (saved) {
                JOptionPane.showMessageDialog(null, "Transaction saved.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save transaction.");
            }
        });

        setVisible(true);
    }
}
