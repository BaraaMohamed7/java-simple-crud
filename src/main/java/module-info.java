module com.stdbsy.stdbsy {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires java.sql;

    opens com.stdbsy.stdbsy to javafx.fxml;
    exports com.stdbsy.stdbsy;
}