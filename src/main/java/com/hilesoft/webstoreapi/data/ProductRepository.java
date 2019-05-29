package com.hilesoft.webstoreapi.data;

import com.hilesoft.webstoreapi.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
