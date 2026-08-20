package Main;

import Controller.ProductController;
import Controller.TransactionController;
import View.MainMenuGUI;

public class MainApp {
    public static void main(String[] args) {
        ProductController productController = new ProductController();
        TransactionController transactionController = new TransactionController();

        new MainMenuGUI(productController, transactionController);
    }
}
