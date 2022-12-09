package market.controllers;

import lombok.RequiredArgsConstructor;
import market.services.OrderService;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import ru.pb.market.OrderDto;
import ru.pb.market.ProductInCartDto;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("")
    public List<OrderDto> getOrders(@RequestHeader Map<String, String> headers) {
        System.out.println(headers);
        return orderService.getOrders(getUserNameFromHeaders(headers));
//        List<OrderDto> t=new ArrayList<>();
//        t.add(new OrderDto(1l,200,"user", LocalDateTime.now()));
//        return t;
    }


    @GetMapping("/{orderId}")
    public List<ProductInCartDto> getProducts(@PathVariable Long orderId) {
        return orderService.get(orderId);
    }

    @PostMapping("/create")
    public Long createOrder(@RequestHeader Map<String, String> headers) {
        return orderService.createOrder(getUserNameFromHeaders(headers));
    }


    private String getUserNameFromHeaders(Map<String, String> headers){
        return  (headers.get("username"));
    }

}
