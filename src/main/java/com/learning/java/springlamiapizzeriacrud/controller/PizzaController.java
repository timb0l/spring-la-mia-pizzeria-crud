package com.learning.java.springlamiapizzeriacrud.controller;

import com.learning.java.springlamiapizzeriacrud.model.Pizza;
import com.learning.java.springlamiapizzeriacrud.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class PizzaController {
    @Autowired
    private PizzaRepo pizzaRepo;

    @GetMapping
    public String home(Model model){
        List<Pizza> pizzas = pizzaRepo.findAll();
        model.addAttribute("pizzaList", pizzas);
        return "index";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("pizza", new Pizza());
        return "create";
    }
    @PostMapping("/create")
    public String store(@ModelAttribute("pizza")Pizza formPizza, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "create";
        }
        pizzaRepo.save(formPizza);
        return "redirect:/";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id")Integer id, Model model){
        List<Pizza> pizzas = pizzaRepo.findAll();
        if (id <= pizzas.size()){
            model.addAttribute("pizza", pizzaRepo.findById(id).get());
            return "show";
        } else {
            return "error";
        }
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id")Integer id){
        pizzaRepo.deleteBy(id);
        return "redirect:/";
    }
    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id")Integer id, Model model){
        model.addAttribute("pizza", pizzaRepo.findById(id).get());
        return "edit";
    }
    @PostMapping("edit/{id}")
    public String update(@PathVariable("pizza")Pizza formPizza, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "edit";
        } pizzaRepo.save(formPizza);
        return "redirect:/";
    }
}
