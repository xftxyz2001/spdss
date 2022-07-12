package com.xftxyz.smms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.xftxyz.smms.utils.JDBCUtils;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    // private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // try {
        //     Connection connection = JDBCUtils.getConnection();
        //     System.out.println(connection);
        // } catch (ClassNotFoundException | SQLException | IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        // scene = new Scene(loadFXML("primary"), 640, 480);
        // stage.setScene(scene);
        Scene scene = new Scene(new Label("hello"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("超市管理系统");
        stage.show();
    }

    // static void setRoot(String fxml) throws IOException {
    // scene.setRoot(loadFXML(fxml));
    // }

    // private static Parent loadFXML(String fxml) throws IOException {
    // FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml +
    // ".fxml"));
    // return fxmlLoader.load();
    // }

    public static void main(String[] args) {
        launch();
        // InputStream resourceAsStream = App.class.getResourceAsStream("primary.fxml");
        // System.out.println(resourceAsStream);
        // try {
        // byte[] b = new byte[resourceAsStream.available()];
        // resourceAsStream.read(b);
        // System.out.println(new String(b));
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

    }

}