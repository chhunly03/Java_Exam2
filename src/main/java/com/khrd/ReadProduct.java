package com.khrd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Script database table product
//CREATE TABLE product(
//        id Int,
//        name VARCHAR(255),
//price_per_unit DECIMAL(10, 2),
//active_for_sell BOOLEAN
//);


public class ReadProduct {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/product_db";
        String userName = "postgres";
        String password = "1111@2222@";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Connect to PostgreSQL
            con = DriverManager.getConnection(url, userName, password);
            if (con != null) {
                System.out.println("Connected to PostgreSQL database!");
            }

            // Prepare and execute query
            String query = "SELECT id, name, price_per_unit, active_for_sell FROM product";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            // Process the ResultSet
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double pricePerUnit = rs.getDouble("price_per_unit");
                boolean activeForSell = rs.getBoolean("active_for_sell");

                System.out.printf("ID: %d, Name: %s, Price per Unit: %.2f, Active for Sell: %b\n",
                        id, name, pricePerUnit, activeForSell);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close ResultSet, PreparedStatement, and Connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
