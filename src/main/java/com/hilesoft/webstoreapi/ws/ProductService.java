package com.hilesoft.webstoreapi.ws;

import com.hilesoft.webstoreapi.data.ProductRepository;
import com.hilesoft.webstoreapi.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    @GetMapping("/products/createtest")
    public Product create(){
        Product p = new Product();
        p.setName("Random product");
        p = productRepo.save(p);
        return p;
    }

    @GetMapping("/products")
    public Iterable<Product> getAll(){
        Iterable<Product> products = productRepo.findAll();
        return products;
    }

    @GetMapping("/products/{id}")
    public Product get(@PathVariable("id") long id){
        Optional<Product> p = productRepo.findById(id);
        return p.get();
    }

    @PostMapping("/products")
    public Product create(@RequestBody Product product){
        return productRepo.save(product);
    }

    @PutMapping("/products/{id}")
    public Product update(@PathVariable("id") long id, @RequestBody Product product){
        Optional<Product> p = productRepo.findById(id);
        if(p.isPresent()){
            product = productRepo.save(product);
        }
        return product;
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable("id") long id) {
        Optional<Product> p = productRepo.findById(id);
        if(p.isPresent()){
            productRepo.deleteById(id);
        }
    }
}
