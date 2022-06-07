package com.sparta.week5.dto;

import com.sparta.week5.model.Menu;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDTO {
    private long id;

    private String name;

    private int price;

    public MenuDTO(){
    }

    public MenuDTO(Menu menu){
        this.id = menu.getId();
        this.name = menu.getName();
        this.price = menu.getPrice();
    }

    public Menu createMenu(){
        Menu m = new Menu();
        m.setName(this.name);
        m.setPrice(this.price);
        return m;
    }
}
