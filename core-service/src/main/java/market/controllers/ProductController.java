package market.controllers;

import lombok.RequiredArgsConstructor;

import market.entities.Product;
import market.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.ArrayUtils;
import ru.pb.market.ProductDto;
import ru.pb.market.ProductListRequest;


import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("")
    public Page<ProductDto> find(@RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(required = false) Integer minPrice,
                                 @RequestParam(required = false) Integer maxPrice,
                                 @RequestParam(required = false) String partName,
                                 @RequestParam(defaultValue = "true") boolean active) {
        return productService.find(page, minPrice, maxPrice, partName, active);
    }



    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
    }

    @PutMapping("")
    public void updateProduct(@RequestBody ProductDto productDto) {
        productService.update(productDto);
    }


    @GetMapping("/{id}")
    public Product infoRest(@PathVariable int id) {
        return productService.getProduct(id);
    }


    @GetMapping("/all")
    public List<Product> get() {
        return productService.getAllProducts();
    }

    @GetMapping("/list")
    public List<ProductDto> getListByIds(@RequestBody Long[] list){
        List<ProductDto> t = productService.getProductsByIdIn(list);
        System.out.print("Отдали список продуктов: ");
        for (ProductDto productDto : t) {
            System.out.print(productDto.getTitle()+" ");
        }
        System.out.println();
        return productService.getProductsByIdIn(list);
    }

}
