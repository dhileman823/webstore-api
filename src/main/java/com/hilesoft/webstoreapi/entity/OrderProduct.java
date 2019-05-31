package com.hilesoft.webstoreapi.entity;

import javax.persistence.*;

@Entity
public class OrderProduct {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long orderProductId;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private CustomerOrder customerOrder;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    private int quantity;

    public long getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(long orderProductId) {
        this.orderProductId = orderProductId;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
