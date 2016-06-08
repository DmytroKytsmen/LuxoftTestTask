package com.kytsmen.dto;

import java.util.Objects;

public class ProductPurchaseDto {
    private String product;
    private int count;
    private double sum;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "ProductPurchaseDto{" +
                "product='" + product + '\'' +
                ", count=" + count +
                ", sum=" + sum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPurchaseDto that = (ProductPurchaseDto) o;
        return count == that.count &&
                Double.compare(that.sum, sum) == 0 &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, count, sum);
    }
}
