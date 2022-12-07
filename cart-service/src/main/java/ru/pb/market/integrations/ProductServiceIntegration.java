package ru.pb.market.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.pb.market.ProductDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    private static final String URI = "http://localhost:8189/market-core/api/v1/products/";

    public ProductDto findById(Long id) {
        return restTemplate.getForObject(URI + id, ProductDto.class);
    }

    public List<ProductDto> getProductsByIdIn(Long[] ids) {

//        ProductDto[] pr = restTemplate.getForObject(URI +"list",  ProductDto[].class, ids);
//        System.out.println(pr);
//        return Arrays.stream(pr).toList();

        List<ProductDto> result = new ArrayList<>();
        for (Long id : ids) {
            result.add(findById(id));
        }
        return result;


    }
}
