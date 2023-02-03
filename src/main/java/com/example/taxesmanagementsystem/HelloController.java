package com.example.taxesmanagementsystem;

import com.example.taxesmanagementsystem.entity.Company;
import com.example.taxesmanagementsystem.entity.RepairingWorkshop;
import com.example.taxesmanagementsystem.entity.TruckDriver;
import com.example.taxesmanagementsystem.entity.Vehicle;
import com.example.taxesmanagementsystem.repository.CompanyRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button createCompany;

    @FXML
    void initialize() {

        createCompany.setOnAction(event -> {
            System.out.println("test");
            CompanyRepository companyRepository = new CompanyRepository();
            companyRepository.findAll();


        });

    }
}