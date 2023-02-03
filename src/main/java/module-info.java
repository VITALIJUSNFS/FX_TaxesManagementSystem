module com.example.taxesmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;


    opens com.example.taxesmanagementsystem to javafx.fxml;
    exports com.example.taxesmanagementsystem;
}