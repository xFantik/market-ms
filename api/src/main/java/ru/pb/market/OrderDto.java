package ru.pb.market;


import java.time.LocalDateTime;

public class OrderDto {

    public OrderDto() {
    }

    private Long id;

    private Integer totalCost;

    private String username;

    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public String  getUsername() {
        return username;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public OrderDto(Long id, Integer totalCost, String username, LocalDateTime createdAt) {
        this.id = id;
        this.totalCost = totalCost;
        this.username = username;
        this.createdAt = createdAt;
    }
}

