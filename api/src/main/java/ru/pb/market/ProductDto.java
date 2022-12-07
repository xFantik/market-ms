package ru.pb.market;


public class ProductDto {
    private Long id;
    private String title;
    private int price;
    private boolean active;

    public ProductDto() {
    }

    public ProductDto(Long id, String title, int price, boolean active) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }
}
