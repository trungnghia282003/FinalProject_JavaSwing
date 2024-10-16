package service;

import model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product getProductById(int id);

    boolean insertProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(int id);
}
