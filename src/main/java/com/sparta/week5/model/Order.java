package com.sparta.week5.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Entity
public class Order extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne(targetEntity = Restaurant.class,fetch = FetchType.LAZY)
    @JoinColumn
    private Restaurant restaurant;

    @ElementCollection
    @CollectionTable(name="order_menu",joinColumns = {@JoinColumn(name = "order_id",referencedColumnName = "id")})
    @MapKeyColumn(name="menu_id")
    @Column(name ="count")
    private Map<Menu,Integer> orderMenu = new HashMap<>();

    public void OrderMenu(Menu menu, int count) {
        this.orderMenu.put(menu, orderMenu.getOrDefault(menu,0)+count);
    }
    public void totalPricePolicyTest() {
        if(getTotalOrderPrice() < restaurant.getMinOrderPrice())
            System.out.println("최소주문가격 보다 적습니다");
    }
    public void orderMenuCountPolicyTest() {
        if(orderMenu.values().stream().filter(e-> e <= 0).count() > 0 || orderMenu.keySet().stream().count() == 0)
            System.out.println("음식을 주문되지 않았습니다");
        if(orderMenu.values().stream().filter(e-> e > 100).count() > 0)
            System.out.println("주문 수량은 100개까지");
    }
    public void totalPolicyTest() {
        totalPricePolicyTest();
        orderMenuCountPolicyTest();
    }
    public int getDeliveryFee() {
        return this.restaurant.getDeliveryFee();
    }
    public int getTotalOrderPrice() {
        AtomicInteger price = new AtomicInteger(0);
        orderMenu.keySet().stream().forEach(e-> price.addAndGet((e.getPrice() * orderMenu.get(e))));
        return price.get();
    }
    public int getTotalPrice() {
        AtomicInteger price = new AtomicInteger(getDeliveryFee());
        orderMenu.keySet().stream().forEach(e-> price.addAndGet((e.getPrice() * orderMenu.get(e))));
        return price.get();
    }
}
