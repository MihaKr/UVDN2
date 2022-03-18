module com.example.uvdn2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;


    opens com.example.uvdn2 to javafx.fxml;
    exports com.example.uvdn2;
}