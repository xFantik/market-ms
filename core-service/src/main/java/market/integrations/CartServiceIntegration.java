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
//        try {
//
//            RequestEntity request = new RequestEntity(HttpMethod.GET, new URI(URI));
////            request.getHeaders() =new HttpHeaders();
//            request.getHeaders().add("username", userName);
//            ResponseEntity <ProductInCartDto[]> response = restTemplate.exchange(request,ProductInCartDto[].class);
//            System.out.println( response.getBody());
//
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("username", userName);
//        return Arrays.stream(restTemplate.getForObject(URI, ProductInCartDto[].class, headers)).toList();
        HttpHeaders headers = new HttpHeaders();
        headers.add("username", userName);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        System.out.println("1 :"+entity);
        ResponseEntity<ProductInCartDto[]> response=restTemplate.exchange(URI, HttpMethod.GET, entity, ProductInCartDto[].class);
        System.out.println("2 :"+response);
        System.out.println(response.getBody());




        return Arrays.stream(response.getBody()).toList();


    }
    public void clearCart(String userName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("username", userName);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        restTemplate.exchange(URI+"clear", HttpMethod.DELETE, entity, ProductInCartDto[].class);

    }
}
