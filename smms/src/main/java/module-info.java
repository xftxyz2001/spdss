module com.xftxyz.smms {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.xftxyz.smms to javafx.fxml;

    exports com.xftxyz.smms;
}
