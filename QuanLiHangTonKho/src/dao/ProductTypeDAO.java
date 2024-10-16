/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.ProductType;

import java.util.List;

/**
 * @author ASUS
 */
public interface ProductTypeDAO {

    List<ProductType> getAllProductTypes();

    ProductType getProductTypeById(int id);

    boolean insertProductType(ProductType productType);

    boolean updateProductType(ProductType productType);

    boolean deleteProductType(int id);
}
