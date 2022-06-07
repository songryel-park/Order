package com.sparta.week5.dto;

import com.sparta.week5.model.Menu;
import com.sparta.week5.model.Order;
import com.sparta.week5.repository.MenuRepository;
import com.sparta.week5.repository.RestaurantRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderDTO {
    private long id;
    private long restaurantId;

    private String restaurantName;

    private List<OrderMenuDTO> foods = new ArrayList<>();

    private int deliveryFee;

    private int totalPrice;

    public OrderDTO(Order order){
        this.id = order.getId();
        order.getOrderMenu().keySet().stream().forEach(e->{
            foods.add(new OrderMenuDTO(e,order.getOrderMenu().get(e)));
        });
        this.restaurantId =order.getRestaurant().getId();
        this.restaurantName = order.getRestaurant().getName();
        this.deliveryFee = order.getDeliveryFee();
        this.totalPrice = order.getTotalPrice();
    }

    public Order createOrder(RestaurantRepository rRepo, MenuRepository mRepo) {
        Order order = new Order();
        order.setRestaurant(rRepo.findById(this.restaurantId).get());
        Map<Menu,Integer> map = new HashMap<>();
        List<Long> ids = new ArrayList<>();
        foods.stream().forEach(e->ids.add(e.getId()));
        List<Menu> menus = mRepo.getMenusByIdList(ids);
        for(Menu menu: menus){
            map.put(menu, foods.stream().filter(e->e.getId() == menu.getId()).findFirst().get().getQuantity());
        }
        order.setOrderMenu(map);
        return order;
    }

    public void addOrderMenuDTO(OrderMenuDTO dto) {
        this.foods.add(dto);
    }
}
