module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires javafx.graphics;


    opens com.example.dbFirstPrc to javafx.fxml;
    exports com.example.dbFirstPrc;
    exports com.example.dbFirstPrc.Controller;
    opens com.example.dbFirstPrc.Controller to javafx.fxml;
}