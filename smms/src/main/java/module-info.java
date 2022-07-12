module com.xftxyz.smms {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires commons.dbutils;


    opens com.xftxyz.smms to javafx.fxml;
    // opens org.apache.commons.dbutils to com.xftxyz.smms.dao;

    exports com.xftxyz.smms;
    exports com.xftxyz.smms.entity;
}
