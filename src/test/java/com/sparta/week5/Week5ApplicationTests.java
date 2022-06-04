package com.sparta.week5;

import com.sparta.week5.dto.OrderDTO;
import com.sparta.week5.dto.OrderMenuDTO;
import com.sparta.week5.dto.RestaurantDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Week5ApplicationTests {

    @Test
    void contextLoads() {
        OrderDTO dto = new OrderDTO();
        OrderMenuDTO dtos = new OrderMenuDTO();
        OrderMenuDTO dtos2 = new OrderMenuDTO();
        RestaurantDTO rs = new RestaurantDTO();
        rs.setId(86);
        rs.setName("test rest");
        rs.setDeliveryFee(100);
        rs.setMinOrderPrice(100);
        dtos.setId(3);
        dtos.setName("test menu");
        dtos.setQuantity(7);
        dtos2.setId(8);
        dtos.setName("test menu2");
        dtos.setQuantity(2);
        dto.setId(33);
        dto.setRestaurantId(rs.getId());
        dto.addOrderMenuDTO(dtos);
        dto.addOrderMenuDTO(dtos2);
        System.out.println(dto.toString());
    }

}
