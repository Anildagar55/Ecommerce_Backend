package com.example.Eco_Backend.Service;

import com.example.Eco_Backend.DTO.OrderItemRequest;
import com.example.Eco_Backend.DTO.OrderItemsResponse;
import com.example.Eco_Backend.DTO.OrderRequest;
import com.example.Eco_Backend.DTO.OrderResponse;
import com.example.Eco_Backend.Entity.*;
import com.example.Eco_Backend.Enums.OrderStatus;
import com.example.Eco_Backend.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final ProductVariantRepository productVariantRepository;
    private final InventoryRepository inventoryRepository;

    public OrderService(
            OrderRepository orderRepository,
            UserRepository userRepository,
            SellerRepository sellerRepository,
            ProductVariantRepository productVariantRepository,
            InventoryRepository inventoryRepository) {

        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
        this.productVariantRepository = productVariantRepository;
        this.inventoryRepository = inventoryRepository;
    }
    @Transactional
    public OrderResponse createOrder(OrderRequest request){
User user=userRepository.findById(request.getUserId())
        .orElseThrow(()->new RuntimeException("User not found : "+request.getUserId()));
        Seller seller=sellerRepository.findById(request.getSellerId())
                .orElseThrow(()->new RuntimeException("Seller not found : "+request.getSellerId()));
    Order order=Order.builder()
            .user(user)
            .seller(seller)
            .status(OrderStatus.PLACED)
            .totalAmount(0.0)
            .items(new ArrayList<>())
            .build();
    double total=0.0;
        List<OrderItems>items=new ArrayList<>();
        for (OrderItemRequest itemReq:request.getItems()){
            ProductVariant variant=productVariantRepository.findById(itemReq.getVariantId())
                    .orElseThrow(()->new RuntimeException("Variant not found : "+itemReq.getVariantId()));
            Inventory inventory=inventoryRepository.findByVariantId(variant.getId())
                    .orElseThrow(()->new RuntimeException("Inventory not found for variant : "+variant.getId()));
            if (inventory.getQuantity()<itemReq.getQuantity()){
                throw new IllegalStateException("Insufficient stock for SKU : "+variant.getSku());
            }
            inventory.setQuantity(inventory.getQuantity()-itemReq.getQuantity());
            inventoryRepository.save(inventory);

            OrderItems item=OrderItems.builder()
                    .order(order)
                    .variant(variant)
                    .quantity(itemReq.getQuantity())
                    .price(variant.getPrice())
                    .build();
            items.add(item);
            total +=variant.getPrice()*itemReq.getQuantity();
        }
        order.setItems(items);
        order.setTotalAmount(total);
        Order saved=orderRepository.save(order);
        return mapToResponse(saved);
    }
    public OrderResponse mapToResponse(Order order){
        List<OrderItemsResponse>itemsResponses=order.getItems().stream()
                .map(item->OrderItemsResponse.builder()
                        .id(item.getId())
                        .productTitle(item.getVariant().getProduct().getTitle())
                        .sku(item.getVariant().getSku())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .build()
                ).collect(Collectors.toList());
        return OrderResponse.builder()
                .id(order.getId())
                .status(order.getStatus().name())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreatedAt())
                .items(itemsResponses)
                .build();
    }
    public OrderResponse getOrderById(Long id){
        Order order=orderRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Order not found : "+id));
        return mapToResponse(order);
    }
    public List<OrderResponse>getOrderByUser(Long userId){
        return orderRepository.findByUserId(userId)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }
    public List<OrderResponse>getOrderBySeller(Long sellerId){
        return orderRepository.findBySellerId(sellerId)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    @Transactional
    public OrderResponse updateOrderStatus(Long id,OrderStatus status){
        Order order=orderRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Order not found :"+id));
        order.setStatus(status);
        return mapToResponse(orderRepository.save(order));
    }
}
