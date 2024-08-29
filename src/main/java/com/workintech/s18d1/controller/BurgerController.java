package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BurgerController {

    @Autowired
    private BurgerDao burgerDao;

    @GetMapping("/burger")
    public List<Burger> getBurgers(){
        return burgerDao.findAll();
    }

    @GetMapping("/burger/{id}")
    public Burger gerBurger(@PathVariable long id){
        return burgerDao.findById(id);
    }

    @PostMapping("/burger")
    public Burger postBurger(@RequestBody Burger burger){
        return burgerDao.save(burger);
    }

    @DeleteMapping("/burger/{id}")
    public Burger deleteBurger(@PathVariable long id){
        return burgerDao.remove(id);
    }

    @PutMapping("/burger")
    public Burger updateBurger(@RequestBody Burger burger){
        return burgerDao.update(burger);
    }

    @GetMapping("/burger/price/{price}")
    public List<Burger> getByPrice(@PathVariable double price){
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/burger/breadType/{breadType}")
    public List<Burger> getByType(@PathVariable BreadType breadType){
        return burgerDao.findByBreadType(breadType);
    }

    @GetMapping("/burger/content/{content}")
    public List<Burger> getByType(@PathVariable String content){
        return burgerDao.findByContent(content);
    }
}
