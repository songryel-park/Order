package com.sparta.week5.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Menu extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column
    private String name;

    @Column
    private int price;

    public void priceTest() {
        if(price < 100 || price > 1000000)
            System.out.println("가격은 100 ~ 1,000,000원");
        if(price % 100 != 0)
            System.out.println("100원 단위로만 추가 입력 가능");
    }
}
