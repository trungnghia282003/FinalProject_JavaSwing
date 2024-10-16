package service.impl;

import dao.ProductDAO;
import dao.impl.ProductDAOImpl;
import model.Product;
import service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    public ProductServiceImpl() {
        productDAO = new ProductDAOImpl();
    }

    @Override
    public List<Product> getAllProduct() {
        return productDAO.getAllProducts();
    }

    @Override
    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    @Override
    public boolean insertProduct(Product product) {
        return productDAO.insertProduct(product);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDAO.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        return productDAO.deleteProduct(id);
    }
}
