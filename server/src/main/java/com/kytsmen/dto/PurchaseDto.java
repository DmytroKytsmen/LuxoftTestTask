package com.kytsmen.dto;

import java.util.List;
import java.util.Objects;

public class PurchaseDto {
    private List<ProductPurchaseDto> data;

    public PurchaseDto() {
    }

    public PurchaseDto(List<ProductPurchaseDto> data) {
        this.data = data;
    }

    public List<ProductPurchaseDto> getData() {
        return data;
    }

    public void setData(List<ProductPurchaseDto> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PurchaseDto{" +
                "data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseDto that = (PurchaseDto) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
