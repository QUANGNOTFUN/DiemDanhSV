module com.example.diemdanhsv {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.diemdanhsv to javafx.fxml;
    opens com.example.diemdanhsv.controllers to javafx.fxml;
    opens com.example.diemdanhsv.models to javafx.base;



    exports com.example.diemdanhsv;
}