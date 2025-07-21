package com.dp.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.dp.order_service.dto.OrderResponceDTO;
import com.dp.order_service.dto.ProductDTO;
import com.dp.order_service.entity.Order;
import com.dp.order_service.repository.OrderRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private WebClient.Builder webClientBuilder;

	// create a method to place order
	@PostMapping("/placeOrder")
	public Mono<ResponseEntity<OrderResponceDTO>> placeOrder(@RequestBody Order order) {

		if (order.getQuantity() == null) {
			throw new IllegalArgumentException("Quantity cannot be null");
		}

		// fetch product details from product service
		return webClientBuilder.build().get().uri("http://localhost:8081/products/" + order.getProductId()).retrieve()
				.bodyToMono(ProductDTO.class).map(productDTO -> {
					OrderResponceDTO responceDTO = new OrderResponceDTO();
		
					responceDTO.setProductId(order.getProductId());
					responceDTO.setQuantity(order.getQuantity());

					// set product details
					responceDTO.setProductName(productDTO.getName());
					responceDTO.setProductPrice(productDTO.getPrice());
					responceDTO.setTotalPrice(order.getQuantity() * productDTO.getPrice());

					// save order details to DB
					orderRepository.save(order);
					responceDTO.setOrderId(order.getId());
					return ResponseEntity.ok(responceDTO);
				});
	}

	@GetMapping
	public List<Order> getAllOrder() {
		return orderRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id)
	{
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
		return ResponseEntity.ok(order);
	}

}
