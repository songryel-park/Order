package com.sparta.week5.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Restaurant extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column
    private String name;

    @Column
    private int minOrderPrice;

    @Column
    private int deliveryFee;

    @OneToMany(targetEntity = Menu.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    public void addMenu(Menu menu) {
        this.menus.add(menu);
    }
    public void totalPolicyTest() {
        menuPolicyTest();
        minOrderPricePolicyTest();
        deliveryFeePolicyTest();
    }
    public void menuPolicyTest() {
        if(menus.stream().filter(disctinctByKey(e->e.getName())).count() != menus.size())
            throw new IllegalArgumentException("중복된 이름으로 메뉴를 추가할 수 없습니다");
    }
    public void minOrderPricePolicyTest() {
        if(minOrderPrice < 1000 || minOrderPrice > 100000)
            throw new IllegalArgumentException("최소주문가격은 1000 ~ 100,000원");
        if(minOrderPrice % 100 != 0)
            throw new IllegalArgumentException("100원 단위로만 추가 입력 가능");
    }
    public void deliveryFeePolicyTest() {
        if(deliveryFee < 0 || deliveryFee > 10000)
            throw new IllegalArgumentException("기본배달비는 0 ~ 10,000원");
        if(deliveryFee % 500 != 0)
            throw new IllegalArgumentException("기본배달비는 500원 단위로만 추가 입력 가능");
    }

    public static <T> Predicate<T> disctinctByKey(Function<?super T, Object> keyExtractor){
        Map<Object,Boolean> map = new HashMap<>();
        return t->map.putIfAbsent(keyExtractor.apply(t),Boolean.TRUE)==null;
    }
}
