package com.ecommerce.order;

import com.ecommerce.kafka.OrderConfirmation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class orderController {


    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Integer> createOrder(
            @RequestBody @Valid OrderRequest request) {
        return ResponseEntity.ok( orderService.createOrder(request));
    }
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("{/order-id}")
    public ResponseEntity<OrderResponse> findById
            (@PathVariable("order-id") Integer orderId){
        return ResponseEntity.ok(orderService.findById(orderId));
    }
}
