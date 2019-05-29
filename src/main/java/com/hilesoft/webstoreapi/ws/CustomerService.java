package com.hilesoft.webstoreapi.ws;

import com.hilesoft.webstoreapi.data.CustomerRepository;
import com.hilesoft.webstoreapi.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Optional;

@RestController
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    @GetMapping("/customers/createtest")
    public Customer create() throws InvalidKeySpecException, NoSuchAlgorithmException {
        Customer c = new Customer();
        c.setFirstName("Dave");
        c.setLastName("Hileman");

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String password = "MyPassword";
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        c.setKey(new String(hash));
        c.setSalt(new String(salt));

        customerRepo.save(c);
        return c;
    }

    @GetMapping("/customers")
    public Iterable<Customer> getAll(){
        Iterable<Customer> customers = customerRepo.findAll();
        return customers;
    }

    @GetMapping("/customers/{id}")
    public Customer get(@PathVariable("id") long id){
        Optional<Customer> optional = customerRepo.findById(id);
        return optional.get();
    }

    @PostMapping("/customers")
    public Customer create(@RequestBody Customer c){
        return customerRepo.save(c);
    }

    @PutMapping("/customers/{id}")
    public Customer update(@PathVariable("id") long id, @RequestBody Customer customer){
        Optional<Customer> optional = customerRepo.findById(id);
        if(optional.isPresent()){
            customer = customerRepo.save(customer);
        }
        return customer;
    }

    @DeleteMapping("/customers/{id}")
    public void delete(@PathVariable("id") long id) {
        Optional<Customer> optional = customerRepo.findById(id);
        if(optional.isPresent()){
            customerRepo.deleteById(id);
        }
    }
}
