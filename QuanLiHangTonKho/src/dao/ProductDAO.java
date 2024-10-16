/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Product;

import java.util.List;

/**
 * @author ASUS
 */
public interface ProductDAO {

    List<Product> getAllProducts();

    Product getProductById(int id);

    boolean insertProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(int id);
}
