package com.example.taxesmanagementsystem;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.taxesmanagementsystem.repository.CompanyRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TabCompanyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAddCompany;

    @FXML
    private Button btnAllCompanies;

    @FXML
    private Button btnDeleteCompany;

    @FXML
    private Button btnFindCompany;

    @FXML
    private Button btnModifyCompany;

    @FXML
    private TextField txtAddressCompany;

    @FXML
    private TextField txtNameCompany;

    @FXML
    void initialize() {

        CompanyRepository companyRepository=new CompanyRepository();

        btnAllCompanies.setOnAction(event -> {
            companyRepository.findAllCompanies();
            System.out.println(companyRepository.findAllCompanies());
        });

    }

}

