package com.xftxyz.smms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.xftxyz.smms.service.GoodsService;
import com.xftxyz.smms.service.UserService;
import com.xftxyz.smms.utils.FileUtil;
import com.xftxyz.smms.utils.JDBCUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    // Service
    private static Connection conn;
    @SuppressWarnings("unused")
    private GoodsService goodsService;
    private UserService userService;

    // UI控件

    @Override
    public void start(Stage primaryStage) throws Exception {
        initialize();
        ImageView ivCode = new ImageView();
        setCode(ivCode);
        ivCode.setOnMouseClicked(e -> setCode(ivCode));
        ivCode.setLayoutX(220);
        ivCode.setLayoutY(160);
        ivCode.setFitWidth(80);
        ivCode.setFitHeight(25);
        AnchorPane ap = new AnchorPane();// 网格布局类，作为根节点
        ap.setStyle("-fx-background-color:#FFFAF0");// 设置背景色
        Scene scene = new Scene(ap);// 设置场景
        Text title = new Text("用户登录");
        title.setFont(Font.font("FangSong", FontWeight.NORMAL, 20));
        title.setFill(Color.CYAN);
        Label Account = new Label("账号");
        Account.setFont(Font.font("FangSong", FontWeight.NORMAL, 15));
        Label Pwd = new Label("密码");
        Pwd.setFont(Font.font("FangSong", FontWeight.NORMAL, 15));
        Label Idf = new Label("验证码");
        Idf.setFont(Font.font("FangSong", FontWeight.NORMAL, 15));
        TextField Accounttextfield = new TextField();
        TextField identifytextfield = new TextField();
        Button btnLogin = new Button("登录");

        btnLogin.setFont(Font.font("FangSong", FontWeight.NORMAL, 15));
        btnLogin.setStyle("-fx-background-color:#6495ED");
        btnLogin.setPrefWidth(220);
        btnLogin.setLayoutX(100);
        btnLogin.setLayoutY(200);
        identifytextfield.setPrefWidth(60);
        PasswordField passwordfield = new PasswordField();

        btnLogin.setOnAction(e -> {
            // 验证码
            String identify = identifytextfield.getText();
            if (!userService.checkCode(identify)) {
                setCode(ivCode);
                return;
            }
            // 用户名
            String account = Accounttextfield.getText();
            // 密码
            String pwd = passwordfield.getText();
            // ...
            boolean isSucc = userService.login(account, pwd);
            if (!isSucc) {
                setCode(ivCode);
                return;
            }
            // 登录成功
            System.out.println("登录成功");
            // primaryStage.close();
            // 打开主界面
        });

        ap.getChildren().addAll(title, Account, Pwd, Idf, Accounttextfield, identifytextfield, passwordfield, btnLogin,
                ivCode);

        title.setLayoutX(100);
        title.setLayoutY(80);
        Account.setLayoutX(110);
        Account.setLayoutY(100);
        Pwd.setLayoutX(110);
        Pwd.setLayoutY(130);
        Idf.setLayoutX(95);
        Idf.setLayoutY(165);
        Accounttextfield.setLayoutX(150);
        Accounttextfield.setLayoutY(100);
        identifytextfield.setLayoutX(150);
        identifytextfield.setLayoutY(160);
        passwordfield.setLayoutX(150);
        passwordfield.setLayoutY(130);
        primaryStage.setScene(scene);
        primaryStage.setHeight(350);
        primaryStage.setWidth(400);

        primaryStage.setTitle("用户登录");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(FileUtil.getImage("chaoshi.png"));
        primaryStage.show();
    }

    // 重置验证码
    private void setCode(ImageView imageView) {
        Image codeImage = userService.getCode();
        imageView.setImage(codeImage);
    }

    // 初始化：获取数据库链接及service实例
    private void initialize() {
        try {
            conn = JDBCUtil.getConnection();
            userService = new UserService(conn);
            goodsService = new GoodsService(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userService = new UserService(conn);
        goodsService = new GoodsService(conn);
    }

    public static void main(String[] args) {
        launch(args);
        JDBCUtil.close(conn);
    }

}