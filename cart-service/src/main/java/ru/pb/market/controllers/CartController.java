package ru.pb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.pb.market.ProductInCartDto;
import ru.pb.market.services.CartService;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;


    @PostMapping("/checkout")
    public Long checkoutOrder() {
//        return cartService.checkout();
        return null;
    }

    @GetMapping("")
    public List<ProductInCartDto> getCart(@RequestHeader Map<String, String> headers) {
        System.out.println(headers);
      return cartService.get(getUserNameFromHeaders(headers));
    }

    @DeleteMapping("/clear")
    public void clearCart(@RequestHeader Map<String, String> headers)
    {
        cartService.clearCart(getUserNameFromHeaders(headers));
    }


    @DeleteMapping("")
    public void deleteProduct(@RequestHeader Map<String, String> headers, @RequestParam Long productId, @RequestParam(defaultValue = "1") int count) {
        cartService.removeProduct(getUserNameFromHeaders(headers), productId, count);
    }


    @PostMapping("")
    public void addProduct(@RequestHeader Map<String, String> headers, @RequestParam Long productId, @RequestParam(defaultValue = "1") int count) {
        cartService.addProduct(getUserNameFromHeaders(headers), productId, count);
    }

    private String getUserNameFromHeaders(Map<String, String> headers) {
        return (headers.get("username"));
    }

}
