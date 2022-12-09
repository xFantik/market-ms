package market.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import market.converters.ProductConverter;
import market.entities.Order;
import market.entities.OrderProduct;
import market.exceptions.EmptyCartException;
import market.integrations.CartServiceIntegration;
import market.repositories.OrderProductRepository;
import market.repositories.OrderRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.pb.market.OrderDto;
import ru.pb.market.ProductInCartDto;


import java.util.List;


@Component
@Scope("singleton") // по-умолчанию
//@Scope("session") // ?
//@Scope("globalsession") // ?
//@Scope("prototype")
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private final ProductConverter productConverter;
    private final ProductService productService;




    @Transactional
    public List<OrderDto> getOrders(String username) {
        return orderRepository.getOrderByUsernameIs(username).stream().map(order -> new OrderDto(order.getId(), order.getTotalCost(), username, order.getCreatedAt())).toList();
    }


    @Transactional
    public List<ProductInCartDto> get(Long orderId) {
        Order o = orderRepository.getById(orderId);
        List<OrderProduct> orderProducts = o.getOrderProducts();

        List<ProductInCartDto> productsDto = orderProducts.stream()
                .map(p -> productConverter.orderProductToDtoInCart(p, productService.getTitleById(p.getId()))).toList();

        return productsDto;

    }

    @Transactional
    public Long createOrder(String userName) {
        List<ProductInCartDto> productsInCartDtoList = cartServiceIntegration.get(userName);

        System.out.println(productsInCartDtoList);


        if (productsInCartDtoList.size() == 0) {
            throw new EmptyCartException("Корзина пуста");
        }

        Order order = new Order();
        order.setUsername(userName);
        order.setTotalCost(calculateTotalCost(productsInCartDtoList));

        List<OrderProduct> orderProductsList = productsInCartDtoList.stream().map(pro ->
                productConverter.productInCartDtoToOrderProduct(pro, productService.getProduct(pro.getId()),order)).toList();


        orderProductRepository.saveAll(orderProductsList);

        order.setOrderProducts(orderProductsList);
        orderRepository.save(order);
        cartServiceIntegration.clearCart(userName);

        return order.getId();

    }

    private Integer calculateTotalCost(List<ProductInCartDto> products) {
        int total = 0;
        for (ProductInCartDto product : products) {
            total += product.getPrice() * product.getCount();
        }
        return total;
    }
}
