package com.ecommerce.order;

import com.ecommerce.customer.CustomerClient;
import com.ecommerce.exception.BusinessException;
import com.ecommerce.kafka.OrderConfirmation;
import com.ecommerce.kafka.OrderProducer;
import com.ecommerce.orderline.OrderLine;
import com.ecommerce.orderline.OrderLineRequest;
import com.ecommerce.orderline.OrderLineService;
import com.ecommerce.payment.PaymentClient;
import com.ecommerce.payment.PaymentRequest;
import com.ecommerce.product.ProductClient;
import com.ecommerce.product.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final ProductMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;


    public Integer createOrder(OrderRequest request) {
        // check for the customer using OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Customer not found :: OrderCreation Operation Failed"));

        /// purchase the products --> RestTemplate
        var purchasedProducts = this.productClient.purchaseProducts(request.products());

        // persist the order
        var order = this.orderRepository.save(mapper.toOrder(request));
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        // todo --> start payment process
        // todo send the order confirmation to kafka

        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethode(),
                order.getId(),
                request.reference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethode(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new BusinessException("Order not found :: Order Id Not Found"));
    }
}
