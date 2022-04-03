package com.example.International_Phone_Numbers.controller;
import com.example.International_Phone_Numbers.entity.Customer;
import com.example.International_Phone_Numbers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class CustomerController  {

    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/")
    public String listCustomers(Model model) {
        List<Customer> allCustomers = customerService.listCustomers();
        model.addAttribute("customers",allCustomers);
        return "index";
    }

    @RequestMapping("/country")
    public String filterByCountry(@RequestParam String country,Model model) {
        if(country.equals("All")) model.addAttribute("customers",customerService.listCustomers());
        else model.addAttribute("customers",customerService.filterByCountry(country));
        return "index";
    }
    @RequestMapping("/state")
    public String filterByState(@RequestParam String state,Model model) {
        if(state.equals("valid")) model.addAttribute("customers",customerService.getValidNumbers());
        else model.addAttribute("customers",customerService.getInValidNumbers());
        return "index";
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}