package com.tanuja.Order_Service.Service;

import com.tanuja.Order_Service.Config.WebClientConfig;
import com.tanuja.Order_Service.Model.Order;
import com.tanuja.Order_Service.Model.OrderLineItems;
import com.tanuja.Order_Service.Repository.OrderRepository;
import com.tanuja.Order_Service.dto.InventoryResponse;
import com.tanuja.Order_Service.dto.OrderLineItemsDto;
import com.tanuja.Order_Service.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest){

        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        //map orderlineitems()
        List<OrderLineItems> orderLineItems =orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItems);

        //check product is in stock or not and place order;

        List<String> skuCodes=order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();


        InventoryResponse [] inventoryResponses = (webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block());
        assert inventoryResponses != null;
        boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::getIsInStock);
        if(allProductsInStock)
        orderRepository.save(order);
        else {
            throw new IllegalArgumentException("Product is Out of Stock");
        }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems=new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;

    }
}
