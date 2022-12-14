package market.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import ru.pb.market.ProductInCartDto;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;
    private static final String URI = "http://localhost:8190/market-cart/api/v1/cart/";

    public List<ProductInCartDto> get(String userName) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("username", userName);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<ProductInCartDto[]> response=restTemplate.exchange(URI, HttpMethod.GET, entity, ProductInCartDto[].class);

        return Arrays.stream(response.getBody()).toList();


    }
    public void clearCart(String userName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("username", userName);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        restTemplate.exchange(URI+"clear", HttpMethod.DELETE, entity, ProductInCartDto[].class);

    }

//    public void clear(String username) {
//        cartServiceWebClient.get()
//                .uri("/api/v1/cart/0/clear")
//                .header("username", username)
//                .retrieve()
//                .toBodilessEntity()
//                .block();
//    }

}
