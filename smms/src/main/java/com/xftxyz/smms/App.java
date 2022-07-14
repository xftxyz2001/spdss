package com.xftxyz.smms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.xftxyz.smms.service.GoodsService;
import com.xftxyz.smms.service.UserService;
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
    private GoodsService goodsService;
    private UserService userService;

    public void setImage(ImageView imageView) {
        Map<String, Object> map = CodeUtil.get();
        System.out.println(map.get("code"));
        imageView.setImage((Image) map.get("image"));
    }

    private void initialize() {
        try {
            conn = JDBCUtil.getConnection();
            userService = new UserService(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initialize();
        VBox vBox = new VBox();
        ImageView imageView = new ImageView();
        setImage(imageView);
        Button btn = new Button("Test");
        btn.setOnAction(e -> {
            setImage(imageView);
            // UserService us = new UserService(conn);
            // boolean isSucc = us.addUser();
            // if(isSucc) {
            // System.out.println("添加成功");
            // } else {
            // System.out.println("添加失败");
            // }
            // Limits v = Limits.valueOf(new User("clx", "123",
            // Limits.MANAGER.name()).getLimits());
            // System.out.println(v);
        });
        vBox.getChildren().addAll(imageView, btn);
        Scene scene = new Scene(vBox, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.setTitle("XX超市管理系统");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        JDBCUtil.close(conn);
    }

}