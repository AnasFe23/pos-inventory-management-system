package Controller;

import Model.Product;
import Main.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    private Connection conn;

    public ProductController() {
        conn = DbConnection.getConnection();
    }

    public boolean addProduct(String name, String description, String barcode, String category, String supplier,
            double price, int quantity, int reorderLevel) {
String sql = "INSERT INTO product (name, description, barcode, category, supplier, price, quantity, reorder_level) " +
   "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, name);
			stmt.setString(2, description);
			stmt.setString(3, barcode);
			stmt.setString(4, category);
			stmt.setString(5, supplier);
			stmt.setDouble(6, price);
			stmt.setInt(7, quantity);
			stmt.setInt(8, reorderLevel);
			int rowsInserted = stmt.executeUpdate();
		return rowsInserted > 0;
			} catch (SQLException e) {
			e.printStackTrace();
					return false;
		}
}

    public boolean updateProduct(int id, String name, double price, int quantity) {
        String sql = "UPDATE product SET name = ?, price = ?, quantity = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, quantity);
            stmt.setInt(4, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM product WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void updateProductQuantity(int productId, int newQuantity) {
        String sql = "UPDATE product SET quantity = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newQuantity);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
  
    public Product getProductById(int id) {
        String sql = "SELECT * FROM product WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("barcode"),
                    rs.getString("category"),
                    rs.getString("supplier"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getInt("reorder_level")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                productList.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("barcode"),
                    rs.getString("category"),
                    rs.getString("supplier"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getInt("reorder_level")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}


    
