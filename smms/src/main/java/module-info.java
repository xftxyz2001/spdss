@SuppressWarnings("all") module com.xftxyz.smms {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;
    requires commons.dbutils;
    requires java.desktop;

    opens com.xftxyz.smms to javafx.fxml;

    exports com.xftxyz.smms;
    exports com.xftxyz.smms.entity;
    exports com.xftxyz.smms.view;
    exports com.xftxyz.smms.service;
}
