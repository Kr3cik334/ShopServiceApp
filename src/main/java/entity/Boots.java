package entity;

import entity.enums.Color;
import entity.enums.Material;
import entity.enums.SkinType;
import entity.enums.ProductSeparators;

public class Boots extends Product{

    private Integer size;
    private SkinType skinType;

    public Boots(Long id, String productName, Float price, Float weight, Color color, Integer productCount, int size, SkinType skinType) {
        super(id, productName, price, weight, color, productCount);
        this.size = size;
        this.skinType = skinType;
    }

    public int getSize() {
        return size;
    }

    public SkinType getSkinType() {
        return skinType;
    }

    @Override
    public String toString() {
        return ProductSeparators.BOOTS_ID.toString() + ProductSeparators.PRODUCT_SEPARATOR.toString() + getBasicProductString() + ProductSeparators.PRODUCT_SEPARATOR.toString() + size + ProductSeparators.PRODUCT_SEPARATOR.toString() + skinType;
    }
}
