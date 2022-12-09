package market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "username")
    private String username;

    @Column(name = "total_cost")
    private Integer totalCost;


    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts;



    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}

