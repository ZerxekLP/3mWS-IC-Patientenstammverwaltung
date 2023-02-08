module com.example.uebung_c {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.uebung_c to javafx.fxml;
    exports com.example.uebung_c;
}