package ru.pb.market;


import java.time.LocalDateTime;

public class OrderDto {

    public OrderDto() {
    }

    private Long id;

    private Integer totalCost;

    private Long ownerId;

    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public OrderDto(Long id, Integer totalCost, Long ownerId, LocalDateTime createdAt) {
        this.id = id;
        this.totalCost = totalCost;
        this.ownerId = ownerId;
        this.createdAt = createdAt;
    }
}

