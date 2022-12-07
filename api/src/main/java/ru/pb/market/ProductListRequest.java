package ru.pb.market;

public class ProductListRequest {
    Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public ProductListRequest(Long value) {
        this.value = value;
    }

    public ProductListRequest() {
    }


}
