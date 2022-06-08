package com.sparta.week5.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sparta.week5.dto.OrderDTO;
import com.sparta.week5.model.Order;
import com.sparta.week5.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    @PostMapping(value = "/order/request")
    public ResponseEntity saveOrder(@RequestBody OrderDTO orderDTO){
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        try {
            ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
            Order order = service.save(orderDTO);
            return ResponseEntity.ok(writer.writeValueAsString(new OrderDTO(order)));
        } catch (Exception e) {
            e.printStackTrace();
            object.addProperty("msg",e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gson.toJson(object));
    }

    @GetMapping(value = "/order")
    public List<OrderDTO> getOrder(){
        List<OrderDTO> rt = new ArrayList<>();
        service.getORepo().findAll().stream().forEach(e->rt.add(new OrderDTO(e)));
        return rt;
    }
}
