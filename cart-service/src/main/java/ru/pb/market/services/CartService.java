package ru.pb.market.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.pb.market.ProductInCartDto;
import ru.pb.market.integrations.ProductServiceIntegration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;


@Component
@Scope("singleton") // по-умолчанию
//@Scope("session") // ?
//@Scope("globalsession") // ?
//@Scope("prototype")
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private HashMap<String, HashMap<Long, Integer>> carts;

    private final ProductServiceIntegration productServiceIntegration;


    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    public void addProduct(String username, long productId, int count) {
        HashMap<Long, Integer> products = getCart(username);
     //   log.info("Добавили в корзину {} {}", count, productServiceIntegration.findById(productId).getTitle());
        products.put(productId, products.getOrDefault(productId, 0) + count);
        if (products.get(productId) <= 0) {
            products.remove(productId);
       //     log.info("В корзине не осталось {}, убрали совсем", productServiceIntegration.findById(productId).getTitle());
        }
    }


    public void clearCart(String userName){
        HashMap<Long, Integer> products = getCart(userName);
        products.clear();

    }
    public void removeProduct(String username, long productId, int count) {
        HashMap<Long, Integer> products = getCart(username);
        products.put(productId, products.getOrDefault(productId, 0) - count);
     //   log.info("Убрали из корзины {} {}", count, productServiceIntegration.findById(productId).getTitle());
        if (products.get(productId) <= 0) {
            products.remove(productId);
       //     log.info("В корзине не осталось {}, убрали совсем", productServiceIntegration.findById(productId).getTitle());
        }
    }

    public List<ProductInCartDto> get(String username) {
        HashMap<Long, Integer> products = getCart(username);

        Long[] ids = new Long[products.size()];
        products.keySet().toArray(ids);


        List<ProductInCartDto> productsByIdIn = productServiceIntegration.getProductsByIdIn(ids).stream().
                map(productDto -> new ProductInCartDto(productDto.getId(), productDto.getTitle(), productDto.getPrice(), products.get(productDto.getId())))
                .toList();


        if (products.size() > productsByIdIn.size()) {
            products.entrySet().removeIf(e -> !isPresentInList(productsByIdIn, e.getKey()));
        }

        return productsByIdIn;
    }

    private boolean isPresentInList(List<ProductInCartDto> list, Long id) {
        for (ProductInCartDto product : list) {
            if (product.getId().equals(id))
                return true;
        }
        log.warn("Продукт с id {}, не найден в базе, удаляем из корзины", id);
        return false;
    }

    private HashMap<Long, Integer> getCart(String name) {
        HashMap<Long, Integer> cart = carts.get(name);
        if (cart == null) {
            log.info("Создали корзину пользователя: " + name);
            cart = new HashMap<>();
            carts.put(name, cart);
        }
        log.info("Получили корзину пользователя: " + name);
        return cart;
    }


}
