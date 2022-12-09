module com.example.trabalhofinal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.google.gson;

    opens com.example.trabalhofinal to javafx.fxml;
    exports com.example.trabalhofinal;
}