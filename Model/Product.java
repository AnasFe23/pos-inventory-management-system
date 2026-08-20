package Model;

public class Product {
    private int id;
    private String name;
    private String description;
    private String barcode;
    private String category;
    private String supplier;
    private double price;
    private int quantity;
    private int reorderLevel;

    // Constructor with all fields
    public Product(int id, String name, String description, String barcode,
                   String category, String supplier, double price, int quantity, int reorderLevel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.category = category;
        this.supplier = supplier;
        this.price = price;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
    }
    public Product(int id, String name, double price, int quantity, String category, String supplier) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.supplier = supplier;
    }




    // Constructor without ID (for insert)
    public Product(String name, String description, String barcode,
                   String category, String supplier, double price, int quantity, int reorderLevel) {
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.category = category;
        this.supplier = supplier;
        this.price = price;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
    }

   
    

	

	
	// Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
}
