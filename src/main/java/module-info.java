module com.project.julio {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.project.julio to javafx.fxml;
    exports com.project.julio;
    opens com.project.models;
    exports com.project.models;
    opens com.project.dbhandler;
    exports com.project.dbhandler;
}
