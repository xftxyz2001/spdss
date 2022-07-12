package com.xftxyz.smms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.xftxyz.smms.dao.UserDao;
import com.xftxyz.smms.dao.impl.UserDaoImpl;
import com.xftxyz.smms.entity.User;
import com.xftxyz.smms.utils.JDBCUtils;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    // private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // try {
        // Connection connection = JDBCUtils.getConnection();
        // System.out.println(connection);
        // } catch (ClassNotFoundException | SQLException | IOException e) {
        // e.printStackTrace();
        // }
        // scene = new Scene(loadFXML("primary"), 640, 480);
        // stage.setScene(scene);
        Button b1 = new Button("get");
        b1.setOnAction(e -> {
            try {
                Connection connection = JDBCUtils.getConnection();
                UserDao userDao = new UserDaoImpl();
                User user = new User();
                user.setName("admin");
                user.setPwd("admin888");
                User user2 = userDao.getUser(connection, user.getName(), user.getPwd());
                System.out.println(user2);
                JDBCUtils.close(connection);
            } catch (ClassNotFoundException | SQLException | IOException e1) {
                e1.printStackTrace();
            }
        });
        Button b2 = new Button("add");
        b2.setOnAction(e -> {
            try {
                Connection connection = JDBCUtils.getConnection();
                UserDao userDao = new UserDaoImpl();
                User user = new User();
                user.setName("admin");
                user.setPwd("admin888");
                user.setLimits("admin");
                user.setCreateAt(Timestamp.valueOf(LocalDateTime.now()));
                userDao.saveUser(connection, user);
                System.out.println(user);
                JDBCUtils.close(connection);
            } catch (ClassNotFoundException | SQLException | IOException e1) {
                e1.printStackTrace();
            }
        });
        // StackPane stackPanel = new StackPane();
        VBox vbox = new VBox();
        vbox.getChildren().add(b1);
        vbox.getChildren().add(b2);
        Scene scene = new Scene(vbox, 640, 480);
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