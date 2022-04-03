package com.example.International_Phone_Numbers.service;
import com.example.International_Phone_Numbers.entity.Customer;
import com.example.International_Phone_Numbers.repository.CustomerRepository;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService(customerRepository);
    }


    @Test
    @Description("assert that when the service layer list customers then will return a list equals the one when we get all customers from Repository ")
    void canListCustomers() {
        //when
        List<Customer> customerList = customerService.listCustomers();
        //then
        assertEquals(customerRepository.findAll() ,customerList);
    }

    @Test
    @Description("assert that given a country name and the pattern of its phone numbers when the service layer list customers from this country then will return a list" +
            " with phone numbers that matches the corresponding pattern of the country")
    void canFilterByCountry(){
        //given
        HashMap<String,String> countryPatternHashMap = new HashMap<>();
        countryPatternHashMap.put("Cameroon","\\(237\\)\\ ?[2368]\\d{7,8}$");
        countryPatternHashMap.put("Ethiopia","\\(251\\)\\ ?[1-59]\\d{8}$");
        countryPatternHashMap.put("Morocco","\\(212\\)\\ ?[5-9]\\d{8}$");
        countryPatternHashMap.put("Mozambique","\\(258\\)\\ ?[28]\\d{7,8}$");
        countryPatternHashMap.put("Uganda","\\(256\\)\\ ?\\d{9}$");

        for (String country: countryPatternHashMap.keySet()) {
            // when
            List<Customer> customerList = customerService.filterByCountry(country);
            //then
            for (Customer c : customerList) {
                assertThat(c.getPhone(), matchesPattern(countryPatternHashMap.get(country)));
            }
        }
    }

    @Test
    @Description("assert that any phone number from the repository with that matches any valid pattern" +
            "will be contained in the valid customer list from the service layer otherwise will be in the invalid list ")
    void canFilterByState() {
        //when
        List<Customer> actualValidCustomerList = customerService.getValidNumbers();
        List<Customer> actualInValidCustomerList = customerService.getValidNumbers();
        List<Customer> AllCustomerList = customerRepository.findAll();
        for (Customer c: AllCustomerList) {
            // if the customer phone number matches any of the valid pattern
            if (c.getPhone().matches("\\(237\\)\\ ?[2368]\\d{7,8}$")
                    || c.getPhone().matches("\\(251\\)\\ ?[1-59]\\d{8}$")
                    || c.getPhone().matches("\\(212\\)\\ ?[5-9]\\d{8}$")
                    || c.getPhone().matches("\\(258\\)\\ ?[28]\\d{7,8}$")
                    || c.getPhone().matches("\\(256\\)\\ ?\\d{9}$"))
            {
                //then
                // assert that is in the actual valid list and not in the invalid list
                assertTrue(actualValidCustomerList.contains(c.getPhone()));
                assertFalse(actualInValidCustomerList.contains(c.getPhone()));
            }
            else{
                // otherwise it will be in the invalid list
                assertTrue(actualInValidCustomerList.contains(c.getPhone()));
                assertFalse(actualValidCustomerList.contains(c.getPhone()));
            }
        }
    }

}