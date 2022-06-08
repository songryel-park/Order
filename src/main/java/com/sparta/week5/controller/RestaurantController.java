package com.sparta.week5.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sparta.week5.dto.MenuDTO;
import com.sparta.week5.dto.RestaurantDTO;
import com.sparta.week5.model.Menu;
import com.sparta.week5.model.Restaurant;
import com.sparta.week5.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService service;

    @GetMapping(value = "/restaurants")
    public List<RestaurantDTO> getAllRestuarant(){
        List<RestaurantDTO> rt = new ArrayList<>();
        service.getRRepo().findAll().stream().forEach(e->rt.add(new RestaurantDTO(e)));
        return rt;
    }

    @PostMapping(value = "/restaurant/register")
    public ResponseEntity saveRestaurant(@RequestBody RestaurantDTO restaurant) {
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        try {
            RestaurantDTO dto = new RestaurantDTO(service.save(restaurant.createRestaurant()));
            ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ResponseEntity.ok(writer.writeValueAsString(dto));
        } catch (Exception e){
            e.printStackTrace();
            object.addProperty("msg",e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gson.toJson(object));
    }

    @PostMapping(value = "/restaurant/{restaurantId}/food/register")
    public ResponseEntity setMenus(@RequestBody List<MenuDTO> menu, @PathVariable("restaurantId") String restaurantid){
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        System.out.println(menu.get(0).getName());
        try {
            List<Menu> m = new ArrayList<>();
            menu.stream().forEach(e->m.add(e.createMenu()));
            Restaurant res = service.save(Long.parseLong(restaurantid),m);
            List<MenuDTO> dtos = new ArrayList<>();
            res.getMenus().stream().forEach(e->dtos.add(new MenuDTO(e)));
            return ResponseEntity.ok(null);
        } catch(Exception e){
            e.printStackTrace();
            object.addProperty("msg",e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gson.toJson(object));
    }

    @GetMapping(value = "/restaurant/{restaurantId}/foods")
    public List<MenuDTO> getMenus(@PathVariable("restaurantId")String id) {
        return service.getMenuDTO(Long.parseLong(id));
    }
}
