package com.example.International_Phone_Numbers.service;
import com.example.International_Phone_Numbers.entity.Customer;
import com.example.International_Phone_Numbers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private HashMap<String,String> patterns = new HashMap<>();
    private HashMap<String,List<Customer>> categories = new HashMap<>();

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        patterns.put("Cameroon","\\(237\\)\\ ?[2368]\\d{7,8}$");
        patterns.put("Ethiopia","\\(251\\)\\ ?[1-59]\\d{8}$");
        patterns.put("Morocco","\\(212\\)\\ ?[5-9]\\d{8}$");
        patterns.put("Mozambique","\\(258\\)\\ ?[28]\\d{7,8}$");
        patterns.put("Uganda","\\(256\\)\\ ?\\d{9}$");

        // initialize each filter category with the corresponding customer list
        initializeCategories();
    }

    public void initializeCategories(){
        Pattern pattern;
        boolean valid = false;

        List<Customer> cameroonList = new ArrayList<>();
        List<Customer> ethiopiaList = new ArrayList<>();
        List<Customer> moroccoList = new ArrayList<>();
        List<Customer> mozambiqueList = new ArrayList<>();
        List<Customer> ugandaList = new ArrayList<>();
        List<Customer> validCustomers = new ArrayList<>();
        List<Customer> invalidCustomers = new ArrayList<>();

        for(Customer c : customerRepository.findAll()){
            valid = false;
            // find which pattern match the customer phone number
            for (String country:patterns.keySet()) {
                pattern = Pattern.compile(patterns.get(country));
                if (pattern.matcher(c.getPhone()).find()){
                    // if a match found add the phone number to the corresponding category list
                    switch (country) {
                        case "Cameroon":
                            valid = true;
                            cameroonList.add(c);
                            validCustomers.add(c);
                            break;
                        case "Ethiopia":
                            valid = true;
                            ethiopiaList.add(c);
                            validCustomers.add(c);
                            break;
                        case "Morocco":
                            valid = true;
                            moroccoList.add(c);
                            validCustomers.add(c);
                            break;
                        case "Mozambique":
                            valid = true;
                            mozambiqueList.add(c);
                            validCustomers.add(c);
                            break;
                        case "Uganda":
                            valid = true;
                            ugandaList.add(c);
                            validCustomers.add(c);
                            break;
                        default:
                            break;
                    }
                }
                if(valid){
                    break;
                }
            }
            if(valid == false){
                // in case no match was found for this phone number, then add it to invalid customers
                invalidCustomers.add(c);
            }
        }
        categories.put("Cameroon",cameroonList);
        categories.put("Ethiopia",ethiopiaList);
        categories.put("Morocco",moroccoList);
        categories.put("Mozambique",mozambiqueList);
        categories.put("Uganda",ugandaList);
        categories.put("Valid",validCustomers);
        categories.put("Invalid",invalidCustomers);
    }
    @GetMapping
    public List<Customer> listCustomers(){
        return customerRepository.findAll();
    }

    public List<Customer> filterByCountry (String country){
        if(country.equals(null) || country.equals("All")) return customerRepository.findAll();
        return categories.get(country);
    }

    public List<Customer> getValidNumbers(){
        return categories.get("Valid");
    }
    public List<Customer> getInValidNumbers(){
        return categories.get("Invalid");
    }
}
