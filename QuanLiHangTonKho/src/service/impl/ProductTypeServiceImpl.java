package service.impl;

import dao.ProductTypeDAO;
import dao.impl.ProductTypeDAOImpl;
import model.ProductType;
import service.ProductTypeService;

import java.util.List;

public class ProductTypeServiceImpl implements ProductTypeService {

    private ProductTypeDAO productTypeDAO;

    public ProductTypeServiceImpl() {
        this.productTypeDAO = new ProductTypeDAOImpl();
    }

    @Override
    public List<ProductType> getAllProductType() {
        return productTypeDAO.getAllProductTypes();
    }

    @Override
    public ProductType getProductTypeById(int id) {
        return productTypeDAO.getProductTypeById(id);
    }

    @Override
    public boolean insertProductType(ProductType productType) {
        return productTypeDAO.insertProductType(productType);
    }

    @Override
    public boolean updateProductType(ProductType productType) {
        return productTypeDAO.updateProductType(productType);
    }

    @Override
    public boolean deleteProductType(int id) {
        return productTypeDAO.deleteProductType(id);
    }
}
