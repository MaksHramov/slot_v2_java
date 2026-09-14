module com.example.composite {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.composite to javafx.fxml;
    exports com.example.composite;
}