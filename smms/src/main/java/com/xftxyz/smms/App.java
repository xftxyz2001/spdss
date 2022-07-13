package com.xftxyz.smms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.xftxyz.smms.utils.CodeUtil;
import com.xftxyz.smms.utils.JDBCUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Connection conn;

    public void setImage(ImageView imageView) {
        Map<String, Object> map = CodeUtil.get();
        System.out.println(map.get("code"));
        imageView.setImage((Image) map.get("image"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();
        ImageView imageView = new ImageView();
        setImage(imageView);
        Button btn = new Button("Test");
        btn.setOnAction(e -> {
            setImage(imageView);
        });
        vBox.getChildren().addAll(imageView, btn);
        Scene scene = new Scene(vBox, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.setTitle("XX超市管理系统");
        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            conn = JDBCUtil.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch(args);
        JDBCUtil.close(conn);
    }

}