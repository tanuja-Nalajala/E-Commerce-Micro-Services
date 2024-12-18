package com.tanuja.Order_Service.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
//@Entity
//@Table(name="t_order_line_items")
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  String skuCode;
    private BigDecimal price;
    private Integer quantity;

//    public OrderLineItemsDto(long id, String skuCode, BigDecimal price, Integer quantity) {
//        this.id = id;
//        this.skuCode = skuCode;
//        this.price = price;
//        this.quantity = quantity;
//    }
//
//    public OrderLineItemsDto() {
//    }

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
}
