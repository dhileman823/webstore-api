package com.hilesoft.webstoreapi.data;

import com.hilesoft.webstoreapi.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
