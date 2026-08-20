# 🛒 Java POS & Inventory Management System

[![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Architecture](https://img.shields.io/badge/Architecture-MVC-blue.svg)]()
[![GUI](https://img.shields.io/badge/GUI-Java%20Swing%20%2F%20AWT-green.svg)]()

A modular desktop Point of Sale (POS) and inventory tracking application developed in Java using the **Model-View-Controller (MVC)** architectural pattern.

---

## 📌 Features
- **Product Management:** Add, update, view, and delete items from the central catalog.
- **Sales Transactions:** Real-time billing, itemized receipt generation, and transaction logging.
- **Payment Processing:** Integrated payment module supporting multiple checkout workflows.
- **Reporting & Auditing:** Automated sales transaction history and inventory summary reports.
- **Persistent Storage:** Centralized database connection management (`DbConnection.java`) for transactional integrity.

---

## 🏗️ System Architecture (MVC Pattern)

```text
pos-inventory-management-system/
└── src/
    ├── Main/
    │   ├── MainApp.java            # Application launch and entry point
    │   └── DbConnection.java       # Database connectivity handler
    ├── Model/
    │   ├── Product.java            # Product entity and data schema
    │   └── SaleTransaction.java    # Transaction and billing data model
    ├── View/
    │   ├── MainMenuGUI.java        # Central administrative dashboard
    │   ├── AddProductGUI.java      # Product creation interface
    │   ├── EditProductGUI.java     # Product update interface
    │   ├── DeleteProductGUI.java   # Product removal interface
    │   ├── ProductInventoryGUI.java# Stock overview and monitoring
    │   ├── TransactionGUI.java     # POS sales register interface
    │   ├── PaymentGUI.java         # Payment checkout interface
    │   └── ReportGUI.java          # Analytics and transaction logs
    └── Controller/
        ├── ProductController.java  # Business logic for inventory operations
        └── TransactionController.java # Business logic for checkout and sales
```
##🛠️ Tech Stack & Prerequisites

Language: Java (JDK 8 or higher)

GUI Framework: Java Swing / AWT

Database Connectivity: JDBC / SQL

Architecture: Model-View-Controller (MVC)

## 🚀 Setup & Execution
1. Clone the repository: 
   git clone [https://github.com/AnasFe23/pos-inventory-management-system.git](https://github.com/AnasFe23/pos-inventory-management-system.git)
cd pos-inventory-management-system

2. Compile the project:
   javac -d bin src/Model/*.java src/View/*.java src/Controller/*.java src/Main/*.java

3. Run the application:
   java -cp bin Main.MainApp

## 👤 Author

Anas Faozi Abdullah Al-Abi

Universiti Teknikal Malaysia Melaka (UTeM)

