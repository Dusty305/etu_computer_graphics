module com.example.compgrahp_lr1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.compgrahp_lr1 to javafx.fxml;
    exports com.example.compgrahp_lr1;
}