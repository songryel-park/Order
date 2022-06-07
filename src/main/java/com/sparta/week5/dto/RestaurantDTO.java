package com.sparta.week5.dto;

import com.sparta.week5.model.Restaurant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDTO {
    private long id;

    private String name;

    private int minOrderPrice;

    private int deliveryFee;

    public RestaurantDTO(){
    }

    public RestaurantDTO(Restaurant restaurant){
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.minOrderPrice = restaurant.getMinOrderPrice();
        this.deliveryFee = restaurant.getDeliveryFee();
    }

    public Restaurant createRestaurant(){
        Restaurant r = new Restaurant();
        r.setName(this.name);
        r.setMinOrderPrice(this.minOrderPrice);
        r.setDeliveryFee(this.deliveryFee);
        return r;
    }
}
