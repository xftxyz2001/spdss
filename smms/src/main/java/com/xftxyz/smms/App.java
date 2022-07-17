package com.xftxyz.smms;

import com.xftxyz.smms.service.ServiceFactory;
import com.xftxyz.smms.service.UserService;
import com.xftxyz.smms.utils.Debug;
import com.xftxyz.smms.utils.DialogUtil;
import com.xftxyz.smms.utils.FileUtil;
import com.xftxyz.smms.view.AdminView;
import com.xftxyz.smms.view.ManagerView;
import com.xftxyz.smms.view.PurchaserView;
import com.xftxyz.smms.view.SalerView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private UserService userService;

    // UI控件
    private TextField tfUserName;
    private PasswordField pfPassWord;
    private TextField tfCode;
    private Button btnLogin;

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

        btnLogin.setFont(Font.font("FangSong", FontWeight.NORMAL, 15));
        btnLogin.setStyle("-fx-background-color:#6495ED");
        btnLogin.setPrefWidth(220);
        btnLogin.setLayoutX(100);
        btnLogin.setLayoutY(200);
        tfCode.setPrefWidth(60);

        btnLogin.setOnAction(e -> {
            String code = tfCode.getText();
            if (code == null || code.trim().equals("")) {
                tfCode.setStyle("-fx-border-color:red");
                DialogUtil.showWarningDialog("警告", "请输入验证码", null);
                Debug.log("验证码不能为空");
                return;
            }
            if (!userService.checkCode(code)){
                tfCode.setStyle("-fx-border-color:red");
                Debug.log("验证码不正确");
                return;
                
            }

            String username = tfUserName.getText();
            String password = pfPassWord.getText();

            // 未连接到数据库，进入测试模式
            if (!ServiceFactory.checkConnection()) {
                if (username.equals("admin") && password.equals("admin")) {
                    try {
                        new AdminView().start();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if (username.equals("manager") && password.equals("manager")) {
                    try {
                        new ManagerView().start();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if (username.equals("saler") && password.equals("saler")) {
                    try {
                        new SalerView().start();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if (username.equals("purchaser") && password.equals("purchaser")) {
                    try {
                        new PurchaserView().start();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                return;
            }

            // 链接到数据库，进行登录操作
            boolean isSucc = userService.login(username, password);
            if (!isSucc) {
                // System.out.println("登录失败（用户名或密码错误）");
                return;
            }

            // 关闭登录界面
            primaryStage.close();
            try {
                switch (userService.getCurrentUserRole()) {
                    case ADMIN:
                        new AdminView().start();
                        break;
                    case MANAGER:
                        new ManagerView().start();
                        break;
                    case PURCHASER:
                        new PurchaserView().start();
                        break;
                    case SALER:
                        new SalerView().start();
                        break;
                    default:
                        // System.out.println("请先登录");
                        break;

                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        ap.getChildren().addAll(title, Account, Pwd, Idf, tfUserName, pfPassWord, tfCode, btnLogin,
                ivCode);

        title.setLayoutX(100);
        title.setLayoutY(80);
        Account.setLayoutX(110);
        Account.setLayoutY(100);
        Pwd.setLayoutX(110);
        Pwd.setLayoutY(130);
        Idf.setLayoutX(95);
        Idf.setLayoutY(165);
        tfUserName.setLayoutX(150);
        tfUserName.setLayoutY(100);
        tfCode.setLayoutX(150);
        tfCode.setLayoutY(160);
        pfPassWord.setLayoutX(150);
        pfPassWord.setLayoutY(130);
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
        // Image codeImage = userService.getCode();
        imageView.setImage(userService.getCode());
    }

    // 初始化：获取数据库链接及service实例
    private void initialize() {
        userService = ServiceFactory.getUserService();
        tfUserName = new TextField();
        pfPassWord = new PasswordField();
        tfCode = new TextField();
        btnLogin = new Button("登录");

    }

    public static void main(String[] args) {
        launch(args);
        ServiceFactory.close();
    }

}