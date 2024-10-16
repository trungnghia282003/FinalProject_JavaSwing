package service;

import model.ProductType;

import java.util.List;

public interface ProductTypeService {

    List<ProductType> getAllProductType();

    ProductType getProductTypeById(int id);

    boolean insertProductType(ProductType productType);

    boolean updateProductType(ProductType productType);

    boolean deleteProductType(int id);
}
