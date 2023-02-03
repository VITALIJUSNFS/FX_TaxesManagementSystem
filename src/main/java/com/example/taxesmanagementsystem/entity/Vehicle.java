package com.example.taxesmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle")
public class Vehicle extends Exception{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String regNumber;
    private String emissionLevel;


    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "company_id")
    private Company company;


    @ManyToMany
    @ToString.Exclude
    @JoinTable(name = "vehicle_driver",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "truckDriver_id"))
    private List<TruckDriver> TruckDrivers = new ArrayList<>();

    @OneToOne(mappedBy = "vehicle")
    @ToString.Exclude
    private RepairingWorkshop repairingWorkshop;


}
