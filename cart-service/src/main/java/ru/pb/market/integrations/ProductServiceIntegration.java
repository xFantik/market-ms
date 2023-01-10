package ru.pb.market.integrations;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import ru.pb.market.ProductDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

    private final WebClient productServiceWebClient;

//    public ProductDto findById(Long id) {
//        return productServiceWebClient.get()
//                .uri("/api/v1/products/" + id)
//                .retrieve()
//                .bodyToMono(ProductDto.class)
//                .block();
//    }

//    private final RestTemplate restTemplate;
//
//    private static final String URI = "http://localhost:8189/market-core/api/v1/products/";

//    public ProductDto findById(Long id) {
//        return restTemplate.getForObject(URI + id, ProductDto.class);
//    }


    public List<ProductDto> getProductsByIdIn(Long[] ids) {
        List<ProductDto> productDtos = new ArrayList<>();
        if (ids.length == 0) {
            return productDtos;
        }

        WebClient modifiedWebClient =
                productServiceWebClient.mutate().filter((request, next) -> next
                                .exchange(ClientRequest.from(request)
                                        .body(BodyInserters.fromValue(ids))
                                        .build())).
                        build();

        List response = modifiedWebClient.get()
                .uri("/api/v1/products/list")
                .retrieve()
                .bodyToMono(List.class)
                .block();


        for (Object object : response) {
            LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap) object;
            productDtos.add(
                    new ProductDto(Long.parseLong(linkedHashMap.get("id").toString()),
                            linkedHashMap.get("title").toString(),
                            Integer.parseInt(linkedHashMap.get("price").toString()),
                            linkedHashMap.get("active").toString().equals("true")));
        }
        return productDtos;
    }

}
