package com.sparta.week5.dto;

import com.sparta.week5.model.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderMenuDTO {
    private long id;

    private int quantity;

    private String name;

    private int price;

    public OrderMenuDTO(Menu menu, int quantity){
        this.id = menu.getId();
        this.name = menu.getName();
        this.quantity = quantity;
        this.price = menu.getPrice() * quantity;
    }
}
