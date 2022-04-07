package api;

import entity.Product;
import exception.ProductCountNegativeException;
import exception.ProductNameEmptyException;
import exception.ProductPriceNoPositiveException;
import exception.ProductWeightNoPositiveException;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts() throws IOException;
    Integer getCountProducts() throws IOException;
    Product getProductByProductName(String productName) throws IOException;

    boolean isProductOnWarehouse(String productName);
    boolean isProductExist(String productName) throws IOException;
    boolean isProductExist(Long productId) throws IOException;

    boolean saveProduct(Product product) throws IOException, ProductWeightNoPositiveException, ProductNameEmptyException, ProductCountNegativeException, ProductPriceNoPositiveException;
    void removeProduct(String productName) throws Exception;

}
