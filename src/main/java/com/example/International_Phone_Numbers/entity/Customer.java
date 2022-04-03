package com.example.International_Phone_Numbers.entity;


import javax.persistence.*;

@Entity
@Table(name="customer")
public class Customer {

    @SequenceGenerator(name="cust_sequence",sequenceName = "cust_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cust_sequence")
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;

    public Customer(Integer id,
                    String name,
                    String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Customer() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
