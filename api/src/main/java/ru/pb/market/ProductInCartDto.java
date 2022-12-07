package ru.pb.market;


public class ProductInCartDto {
    private Long id;
    private String title;
    private int price;

    private int count;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public ProductInCartDto(Long id, String title, int price, int count) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.count = count;
    }

    public ProductInCartDto(Long id, String title, Integer price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public ProductInCartDto() {
    }
}

