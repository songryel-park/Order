package com.sparta.week5.service;

import com.sparta.week5.dto.MenuDTO;
import com.sparta.week5.model.Menu;
import com.sparta.week5.model.Restaurant;
import com.sparta.week5.repository.MenuRepository;
import com.sparta.week5.repository.RestaurantRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class RestaurantService {
    private final MenuRepository mRepo;
    private final RestaurantRepository rRepo;

    public Restaurant save(Restaurant restaurant) {
        restaurant.totalPolicyTest();
        return rRepo.save(restaurant);
    }
    public Restaurant save(long restaurantId, List<Menu> menus) {
        Restaurant restaurant = rRepo.findById(restaurantId).get();
        for(Menu m : menus){
            m.priceTest();
            restaurant.addMenu(m);
        }
        restaurant.menuPolicyTest();
        return rRepo.save(restaurant);
    }
    public List<MenuDTO> getMenuDTO(long restaurantId){
        List<Menu> m = mRepo.getMenuByRestaurantId(restaurantId);
        List<MenuDTO> dtos = new ArrayList<>();
        m.stream().forEach(e->dtos.add(new MenuDTO(e)));
        return dtos;
    }
}
