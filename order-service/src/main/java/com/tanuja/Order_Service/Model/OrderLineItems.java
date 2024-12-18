package com.tanuja.Order_Service.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Entity
@Table(name="t_order_line_items")

public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  String skuCode;
    private BigDecimal price;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderLineItems(long id, String skuCode,BigDecimal price, Integer quantity) {
        this.id = id;
        this.skuCode=skuCode;
        this.price=price;
        this.quantity=quantity;
    }

    public OrderLineItems() {
    }
}
