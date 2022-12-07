package market.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.pb.market.ProductInCartDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    private static final String URI = "http://localhost:8190/market-cart/api/v1/cart/";



    public List<ProductInCartDto> get(String userName) {
        return Arrays.stream(restTemplate.getForObject(URI + userName, ProductInCartDto[].class)).toList();

    }

    public void clearCart(String userName) {
        restTemplate.delete(URI + "clear/"+userName);
    }
}
