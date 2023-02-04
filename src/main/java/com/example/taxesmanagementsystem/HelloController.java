package com.example.taxesmanagementsystem;

import com.example.taxesmanagementsystem.entity.Company;
import com.example.taxesmanagementsystem.repository.CompanyRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HelloController {

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
    private TextArea txtOutput;
    @FXML
    private TextField txtBudget;
    @FXML
    private TextField txtCountry;

    @FXML
    void initialize() {
        CompanyRepository companyRepository = new CompanyRepository();

        btnAllCompanies.setOnAction(event -> {
            List<Company> allCompaniesSortedById = companyRepository.findAllCompanies().stream()
                    .sorted(Comparator.comparing(Company::getId))
                    .collect(Collectors.toList());
            showInPanel(allCompaniesSortedById);
        });

        btnFindCompany.setOnAction(event -> {
            try {
                List<Company> companyByName = companyRepository.findCompanyByName(txtNameCompany.getText().trim()).stream()
                        .sorted(Comparator.comparing(Company::getId))
                        .collect(Collectors.toList());
                showInPanel(companyByName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        btnDeleteCompany.setOnAction(event -> {
            companyRepository.deleteCompany(txtNameCompany.getText().trim());
            sortedById(companyRepository);
        });
        btnAddCompany.setOnAction(event -> {
            companyRepository.saveNewCompany(Integer.valueOf(txtBudget.getText()), txtCountry.getText().trim(), txtNameCompany.getText().trim());
            sortedById(companyRepository);
        });


    }

    private void sortedById(CompanyRepository companyRepository) {
        List<Company> companyByName = null;
        List<Company> allCompaniesSortedById = companyRepository.findAllCompanies().stream()
                .sorted(Comparator.comparing(Company::getId))
                .collect(Collectors.toList());
        showInPanel(allCompaniesSortedById);
    }

    private void showInPanel(List<Company> allCompaniesSortedById) {
        String name = "";
        for (Company company : allCompaniesSortedById) {
            name += company.getId() + "\t\t" + company.getBudget() + "\t\t" + company.getCountry() + "\t\t" + company.getName() + "\n";
            txtOutput.setText("Id\t\tBudget\t\tCountry\t\tName " + "\n" + name);
        }
    }

}
