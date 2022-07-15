package com.xftxyz.smms.view;

import com.xftxyz.smms.entity.Goods;
import com.xftxyz.smms.entity.User;
import com.xftxyz.smms.service.GoodsService;
import com.xftxyz.smms.service.ServiceFactory;
import com.xftxyz.smms.service.UserService;
import com.xftxyz.smms.type.Limits;
import com.xftxyz.smms.utils.FileUtil;
import com.xftxyz.smms.utils.RandomUtil;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminView {
    // Service
    private GoodsService goodsService;
    private UserService userService;

    // UI控件
    Stage primaryStage;
    Scene scene;
    BorderPane bpRoot;

    HBox hbManageMenu;
    Button btnUserManage;
    Button btnGoodsManage;
    Button btnSupplierManage;
    Button btnPurchaseManage;
    Button btnSaleManage;

    // 中间部分的5个面板
    BorderPane bpUserManage;
    VBox vbUserManage;
    Button btnUserManageAddUser;
    Button btnUserManageDeleteUser;
    Button btnUserManageUpdateUser;
    TableView<User> tvUserManage;

    BorderPane bpGoodsManage;
    VBox vbGoodsManage;
    TableView<Goods> tvGoodsManage;

    BorderPane bpSupplierManage;
    VBox vbSupplierManage;
    TableView<User> tvSupplierManage;

    BorderPane bpPurchaseManage;
    VBox vbPurchaseManage;
    TableView<User> tvPurchaseManage;

    BorderPane bpSaleManage;
    VBox vbSaleManage;
    TableView<User> tvSaleManage;

    Label statusBar;

    public void initialize() {
        // Service初始化
        userService = ServiceFactory.getUserService();
        goodsService = ServiceFactory.getGoodsService();

        // UI控件初始化
        primaryStage = new Stage();
        bpRoot = new BorderPane();
        scene = new Scene(bpRoot);

        // top
        btnUserManage = new Button("用户管理");
        btnUserManage.setOnAction(e -> {
            bpRoot.setCenter(bpUserManage);
        });
        btnGoodsManage = new Button("商品管理");
        btnGoodsManage.setOnAction(e -> {
            bpRoot.setCenter(bpGoodsManage);
        });
        btnSupplierManage = new Button("供应商管理");
        btnSupplierManage.setOnAction(e -> {
            bpRoot.setCenter(bpSupplierManage);
        });
        btnPurchaseManage = new Button("采购管理");
        btnPurchaseManage.setOnAction(e -> {
            bpRoot.setCenter(bpPurchaseManage);
        });
        btnSaleManage = new Button("销售管理");
        btnSaleManage.setOnAction(e -> {
            bpRoot.setCenter(bpSaleManage);
        });
        hbManageMenu = new HBox(btnUserManage, btnGoodsManage, btnSupplierManage, btnPurchaseManage, btnSaleManage);
        hbManageMenu.spacingProperty().bind(scene.widthProperty().subtract(btnUserManage.widthProperty())
                .subtract(btnGoodsManage.widthProperty()).subtract(btnSupplierManage.widthProperty())
                .subtract(btnPurchaseManage.widthProperty()).subtract(btnSaleManage.widthProperty()).divide(4));
        bpRoot.setTop(hbManageMenu);

        // center
        // 用户管理面板
        btnUserManageAddUser = new Button("添加用户");
        btnUserManageAddUser.setOnAction(e -> {
            String name = RandomUtil.choice("123456789", 2);
            String pwd = RandomUtil.choice("123456789", 2);
            Limits[] ll = Limits.values();
            Limits l = ll[RandomUtil.getInt(0, ll.length - 1)];
            String limits = l.name();
            User user = new User(name, pwd, limits);
            userService.addUser(user);
        });
        btnUserManageDeleteUser = new Button("删除用户");
        btnUserManageDeleteUser.setOnAction(e -> {
            User selectedUser = tvUserManage.getSelectionModel().getSelectedItem();
            if (selectedUser == null) {
                System.out.println("请选择一个用户");
            }
            userService.deleteUser(selectedUser);
        });
        btnUserManageUpdateUser = new Button("修改用户");
        vbUserManage = new VBox(btnUserManageAddUser, btnUserManageDeleteUser, btnUserManageUpdateUser);
        tvUserManage = new TableView<User>(userService.getUserObservableList());
        bpUserManage = new BorderPane();
        bpUserManage.setLeft(vbUserManage);
        bpUserManage.setCenter(tvUserManage);

        // 商品管理面板
        vbGoodsManage = new VBox();
        tvGoodsManage = new TableView<Goods>(goodsService.getGoodsObservableList());
        bpGoodsManage = new BorderPane();
        bpGoodsManage.setLeft(vbGoodsManage);
        bpGoodsManage.setCenter(tvGoodsManage);

        // 供应商管理面板
        vbSupplierManage = new VBox();
        tvSupplierManage = new TableView<User>();
        bpSupplierManage = new BorderPane();
        bpSupplierManage.setLeft(vbSupplierManage);
        bpSupplierManage.setCenter(tvSupplierManage);

        // 采购管理面板
        vbPurchaseManage = new VBox();
        tvPurchaseManage = new TableView<User>();
        bpPurchaseManage = new BorderPane();
        bpPurchaseManage.setLeft(vbPurchaseManage);
        bpPurchaseManage.setCenter(tvPurchaseManage);

        // 销售管理面板
        vbSaleManage = new VBox();
        tvSaleManage = new TableView<User>();
        bpSaleManage = new BorderPane();
        bpSaleManage.setLeft(vbSaleManage);
        bpSaleManage.setCenter(tvSaleManage);

        bpRoot.setCenter(bpUserManage);

        // 状态栏
        statusBar = new Label("欢迎使用XX超市管理系统，当前用户" + userService.getCurrentUserName() + "（"
                + userService.getCurrentUserRoleName() + "）");
        bpRoot.setBottom(statusBar);
    }

    public void start() throws Exception {
        initialize();

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("欢迎使用");
        primaryStage.getIcons().add(FileUtil.getImage("user.png"));

    }
}
