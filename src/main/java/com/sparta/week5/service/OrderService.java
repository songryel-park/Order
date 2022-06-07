package com.sparta.week5.service;

import com.sparta.week5.dto.OrderDTO;
import com.sparta.week5.model.Order;
import com.sparta.week5.repository.MenuRepository;
import com.sparta.week5.repository.OrderRepository;
import com.sparta.week5.repository.RestaurantRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository oRepo;
    private final RestaurantRepository rRepo;

    private final MenuRepository mRepo;
    public Order save(OrderDTO dto) {
        Order order = dto.createOrder(rRepo, mRepo);
        order.totalPolicyTest();
        return oRepo.save(order);
    }
}
