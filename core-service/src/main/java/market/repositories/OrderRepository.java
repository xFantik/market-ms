package market.repositories;



import market.data.Order;
import market.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

  List<Order> getOrderByOwnerIs(User u);

}
