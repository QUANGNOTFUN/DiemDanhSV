module com.example.diemdanhsv {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;


    opens com.example.diemdanhsv to javafx.fxml;
    opens com.example.diemdanhsv.controllers to javafx.fxml;
    opens com.example.diemdanhsv.models to javafx.base;
    opens com.example.diemdanhsv.repository to javafx.base;



    exports com.example.diemdanhsv;
    exports com.example.diemdanhsv.controllers;
}