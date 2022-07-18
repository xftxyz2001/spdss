package com.xftxyz.smms.view;

import java.io.File;
import java.math.BigDecimal;

import com.xftxyz.smms.entity.Goods;
import com.xftxyz.smms.entity.User;
import com.xftxyz.smms.service.GoodsService;
import com.xftxyz.smms.service.ServiceFactory;
import com.xftxyz.smms.service.UserService;
import com.xftxyz.smms.type.Role;
import com.xftxyz.smms.utils.DialogUtil;
import com.xftxyz.smms.utils.FileUtil;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ManagerView {
    private GoodsService goodsService;
    private UserService userService;

    GridPane gpUserManage_AddOrUpdateUser;
    Label lbgpUserManage_AddOrUpdateUser_UserName;
    TextField tfgpUserManage_AddOrUpdateUser_UserName;
    Label lbgpUserManage_AddOrUpdateUser_UserPassword;
    TextField tfgpUserManage_AddOrUpdateUser_Password;
    Label lbgpUserManage_AddOrUpdateUser_Role;
    ChoiceBox<Role> cbgpUserManage_AddOrUpdateUser_Role;
    Button btngpUserManage_AddOrUpdateUser_OK;
    Button btngpUserManage_AddOrUpdateUser_Cancel;
    User ugpUserManage_AddOrUpdateUser_selectedUser;

    GridPane gpGoodsManage_AddOrUpdateGoods;
    Label lbgpGoodsManage_AddOrUpdateGoods_GoodsName;
    TextField tfgpGoodsManage_AddOrUpdateGoods_GoodsName;
    Label lbgpGoodsManage_AddOrUpdateGoods_GoodsPrice;
    TextField tfgpGoodsManage_AddOrUpdateGoods_GoodsPrice;
    Label lbgpGoodsManage_AddOrUpdateGoods_GoodsNum;
    TextField tfgpGoodsManage_AddOrUpdateGoods_GoodsNum;
    Label lbgpGoodsManage_AddOrUpdateGoods_GoodsUnit;
    TextField tfgpGoodsManage_AddOrUpdateGoods_GoodsUnit;
    Label lbgpGoodsManage_AddOrUpdateGoods_GoodsDescribe;
    TextField tfgpGoodsManage_AddOrUpdateGoods_GoodsDescribe;
    Button btngpGoodsManage_AddOrUpdateGoods_OK;
    Button btngpGoodsManage_AddOrUpdateGoods_Cancel;
    Goods ggpGoodsManage_AddOrUpdateGoods_selectedGoods;

    public void initialize() {
        userService = ServiceFactory.getUserService();
        goodsService = ServiceFactory.getGoodsService();
    }

    public GridPane init_gpUserManage_AddOrUpdateUser(User user) {
        if (this.gpUserManage_AddOrUpdateUser == null) {
            this.gpUserManage_AddOrUpdateUser = new GridPane();
            this.lbgpUserManage_AddOrUpdateUser_UserName = new Label("用户名：");
            this.tfgpUserManage_AddOrUpdateUser_UserName = new TextField();
            this.lbgpUserManage_AddOrUpdateUser_UserPassword = new Label("密码：");
            this.tfgpUserManage_AddOrUpdateUser_Password = new TextField();
            this.lbgpUserManage_AddOrUpdateUser_Role = new Label("角色：");
            this.cbgpUserManage_AddOrUpdateUser_Role = new ChoiceBox<Role>();
            Role[] roles = { Role.MANAGER, Role.PURCHASER, Role.SALER };
            this.cbgpUserManage_AddOrUpdateUser_Role.getItems().addAll(roles);
            this.btngpUserManage_AddOrUpdateUser_OK = new Button("确定");

            this.btngpUserManage_AddOrUpdateUser_Cancel = new Button("取消");
            this.btngpUserManage_AddOrUpdateUser_Cancel.setOnAction(e -> {
                // this.bpUserManage.setCenter(this.tvUserManage);
                this.gpUserManage_AddOrUpdateUser.setVisible(false);
            });
            this.gpUserManage_AddOrUpdateUser.add(this.lbgpUserManage_AddOrUpdateUser_UserName, 0, 0);
            this.gpUserManage_AddOrUpdateUser.add(this.tfgpUserManage_AddOrUpdateUser_UserName, 1, 0);
            this.gpUserManage_AddOrUpdateUser.add(this.lbgpUserManage_AddOrUpdateUser_UserPassword, 0, 1);
            this.gpUserManage_AddOrUpdateUser.add(this.tfgpUserManage_AddOrUpdateUser_Password, 1, 1);
            this.gpUserManage_AddOrUpdateUser.add(this.lbgpUserManage_AddOrUpdateUser_Role, 0, 2);
            this.gpUserManage_AddOrUpdateUser.add(this.cbgpUserManage_AddOrUpdateUser_Role, 1, 2);
            this.gpUserManage_AddOrUpdateUser.add(this.btngpUserManage_AddOrUpdateUser_OK, 0, 3);
            this.gpUserManage_AddOrUpdateUser.add(this.btngpUserManage_AddOrUpdateUser_Cancel, 1, 3);
        }
        if (user != null) {
            if (user.getRole() == Role.ADMIN) {
                // 警告没有权限
                DialogUtil.showWarningDialog("警告", null, "没有权限");
                return null;
            }

            this.ugpUserManage_AddOrUpdateUser_selectedUser = userService.getUpdateCopy(user);

            this.tfgpUserManage_AddOrUpdateUser_UserName.setText(user.getName());
            this.tfgpUserManage_AddOrUpdateUser_Password.setText(user.getPwd());
            this.cbgpUserManage_AddOrUpdateUser_Role.setValue(user.getRole());

            this.btngpUserManage_AddOrUpdateUser_OK.setOnAction(e -> {
                this.ugpUserManage_AddOrUpdateUser_selectedUser
                        .setName(this.tfgpUserManage_AddOrUpdateUser_UserName.getText());
                this.ugpUserManage_AddOrUpdateUser_selectedUser
                        .setPwd(this.tfgpUserManage_AddOrUpdateUser_Password.getText());
                this.ugpUserManage_AddOrUpdateUser_selectedUser
                        .setRole(this.cbgpUserManage_AddOrUpdateUser_Role.getValue());
                boolean isSucc = userService.updateUser(this.ugpUserManage_AddOrUpdateUser_selectedUser);
                if (!isSucc) {
                    // Debug.log("更新用户失败");
                    DialogUtil.showWarningDialog("警告", null, "更新用户失败");
                }
                // this.bpUserManage.setCenter(this.tvUserManage);
                this.gpUserManage_AddOrUpdateUser.setVisible(false);

            });
        } else {

            this.tfgpUserManage_AddOrUpdateUser_UserName.setText("");
            this.tfgpUserManage_AddOrUpdateUser_Password.setText("");
            this.cbgpUserManage_AddOrUpdateUser_Role.setValue(Role.MANAGER);

            this.btngpUserManage_AddOrUpdateUser_OK.setOnAction(e -> {
                User newUser = new User();
                newUser.setName(this.tfgpUserManage_AddOrUpdateUser_UserName.getText());
                newUser.setPwd(this.tfgpUserManage_AddOrUpdateUser_Password.getText());
                newUser.setRole(this.cbgpUserManage_AddOrUpdateUser_Role.getValue());
                boolean isSucc = userService.addUser(newUser);
                if (!isSucc) {
                    // Debug.log("添加用户失败");
                    DialogUtil.showWarningDialog("警告", null, "添加用户失败");
                }
                // this.bpUserManage.setCenter(this.tvUserManage);
                this.gpUserManage_AddOrUpdateUser.setVisible(false);

            });
        }
        return this.gpUserManage_AddOrUpdateUser;
    }

    public GridPane init_gpGoodsManage_AddOrUpdateGoods(Goods goods) {
        if (this.gpGoodsManage_AddOrUpdateGoods == null) {
            this.gpGoodsManage_AddOrUpdateGoods = new GridPane();
            this.lbgpGoodsManage_AddOrUpdateGoods_GoodsName = new Label("商品名：");
            this.tfgpGoodsManage_AddOrUpdateGoods_GoodsName = new TextField();
            this.lbgpGoodsManage_AddOrUpdateGoods_GoodsPrice = new Label("单价：");
            this.tfgpGoodsManage_AddOrUpdateGoods_GoodsPrice = new TextField();
            this.lbgpGoodsManage_AddOrUpdateGoods_GoodsNum = new Label("库存数量：");
            this.tfgpGoodsManage_AddOrUpdateGoods_GoodsNum = new TextField();
            this.lbgpGoodsManage_AddOrUpdateGoods_GoodsUnit = new Label("单位：");
            this.tfgpGoodsManage_AddOrUpdateGoods_GoodsUnit = new TextField();
            this.lbgpGoodsManage_AddOrUpdateGoods_GoodsDescribe = new Label("描述：");
            this.tfgpGoodsManage_AddOrUpdateGoods_GoodsDescribe = new TextField();
            this.btngpGoodsManage_AddOrUpdateGoods_OK = new Button("确定");
            this.btngpGoodsManage_AddOrUpdateGoods_Cancel = new Button("取消");
            this.btngpGoodsManage_AddOrUpdateGoods_Cancel.setOnAction(e -> {
                // this.bpGoodsManage.setCenter(this.tvGoodsManage);
                // this.gp
                this.gpGoodsManage_AddOrUpdateGoods.setVisible(false);
            });
            this.gpGoodsManage_AddOrUpdateGoods.add(this.lbgpGoodsManage_AddOrUpdateGoods_GoodsName, 0, 0);
            this.gpGoodsManage_AddOrUpdateGoods.add(this.tfgpGoodsManage_AddOrUpdateGoods_GoodsName, 1, 0);
            this.gpGoodsManage_AddOrUpdateGoods.add(this.lbgpGoodsManage_AddOrUpdateGoods_GoodsPrice, 0, 1);
            this.gpGoodsManage_AddOrUpdateGoods.add(this.tfgpGoodsManage_AddOrUpdateGoods_GoodsPrice, 1, 1);
            this.gpGoodsManage_AddOrUpdateGoods.add(this.lbgpGoodsManage_AddOrUpdateGoods_GoodsNum, 0, 2);
            this.gpGoodsManage_AddOrUpdateGoods.add(this.tfgpGoodsManage_AddOrUpdateGoods_GoodsNum, 1, 2);
            this.gpGoodsManage_AddOrUpdateGoods.add(this.lbgpGoodsManage_AddOrUpdateGoods_GoodsUnit, 0, 3);
            this.gpGoodsManage_AddOrUpdateGoods.add(this.tfgpGoodsManage_AddOrUpdateGoods_GoodsUnit, 1, 3);
            this.gpGoodsManage_AddOrUpdateGoods.add(this.lbgpGoodsManage_AddOrUpdateGoods_GoodsDescribe, 0, 4);
            this.gpGoodsManage_AddOrUpdateGoods.add(this.tfgpGoodsManage_AddOrUpdateGoods_GoodsDescribe, 1, 4);
            this.gpGoodsManage_AddOrUpdateGoods.add(this.btngpGoodsManage_AddOrUpdateGoods_OK, 0, 5);
            this.gpGoodsManage_AddOrUpdateGoods.add(this.btngpGoodsManage_AddOrUpdateGoods_Cancel, 1, 5);

        }
        if (goods != null) {
            this.ggpGoodsManage_AddOrUpdateGoods_selectedGoods = goodsService.getUpdateCopy(goods);

            this.tfgpGoodsManage_AddOrUpdateGoods_GoodsName.setText(goods.getName());
            this.tfgpGoodsManage_AddOrUpdateGoods_GoodsPrice.setText(goods.getPrice().toString());
            this.tfgpGoodsManage_AddOrUpdateGoods_GoodsNum.setText(goods.getNum().toString());
            this.tfgpGoodsManage_AddOrUpdateGoods_GoodsUnit.setText(goods.getUnit());
            this.tfgpGoodsManage_AddOrUpdateGoods_GoodsDescribe.setText(goods.getDescribe());
            this.btngpGoodsManage_AddOrUpdateGoods_OK.setOnAction(e -> {
                this.ggpGoodsManage_AddOrUpdateGoods_selectedGoods
                        .setName(this.tfgpGoodsManage_AddOrUpdateGoods_GoodsName.getText());
                this.ggpGoodsManage_AddOrUpdateGoods_selectedGoods
                        .setPrice(new BigDecimal(this.tfgpGoodsManage_AddOrUpdateGoods_GoodsPrice.getText()));
                this.ggpGoodsManage_AddOrUpdateGoods_selectedGoods
                        .setNum(new BigDecimal(this.tfgpGoodsManage_AddOrUpdateGoods_GoodsNum.getText()));
                this.ggpGoodsManage_AddOrUpdateGoods_selectedGoods
                        .setUnit(this.tfgpGoodsManage_AddOrUpdateGoods_GoodsUnit.getText());
                this.ggpGoodsManage_AddOrUpdateGoods_selectedGoods
                        .setDescribe(this.tfgpGoodsManage_AddOrUpdateGoods_GoodsDescribe.getText());
                boolean isSucc = goodsService.updateGoods(this.ggpGoodsManage_AddOrUpdateGoods_selectedGoods);
                if (!isSucc) {
                    // Debug.log("更新商品失败");
                    DialogUtil.showWarningDialog("警告", null, "更新商品失败");
                }
                // this.bpGoodsManage.setCenter(this.tvGoodsManage);
                this.gpGoodsManage_AddOrUpdateGoods.setVisible(false);
            });
        } else {
            this.tfgpGoodsManage_AddOrUpdateGoods_GoodsName.setText("");
            this.tfgpGoodsManage_AddOrUpdateGoods_GoodsPrice.setText("");
            this.tfgpGoodsManage_AddOrUpdateGoods_GoodsNum.setText("");
            this.tfgpGoodsManage_AddOrUpdateGoods_GoodsUnit.setText("");
            this.tfgpGoodsManage_AddOrUpdateGoods_GoodsDescribe.setText("");
            this.btngpGoodsManage_AddOrUpdateGoods_OK.setOnAction(e -> {
                Goods newGoods = new Goods();
                newGoods.setName(this.tfgpGoodsManage_AddOrUpdateGoods_GoodsName.getText());
                newGoods.setPrice(new BigDecimal(this.tfgpGoodsManage_AddOrUpdateGoods_GoodsPrice.getText()));
                newGoods.setNum(new BigDecimal(this.tfgpGoodsManage_AddOrUpdateGoods_GoodsNum.getText()));
                newGoods.setUnit(this.tfgpGoodsManage_AddOrUpdateGoods_GoodsUnit.getText());
                newGoods.setDescribe(this.tfgpGoodsManage_AddOrUpdateGoods_GoodsDescribe.getText());
                boolean isSucc = goodsService.addGoods(newGoods);
                if (!isSucc) {
                    // Debug.log("添加商品失败");
                    DialogUtil.showWarningDialog("警告", null, "添加商品失败");
                }
                // this.bpGoodsManage.setCenter(this.tvGoodsManage);
                this.gpGoodsManage_AddOrUpdateGoods.setVisible(false);
            });

        }
        return this.gpGoodsManage_AddOrUpdateGoods;
    }

    public void start() throws Exception {
        initialize();

        Stage primaryStage = new Stage();
        Button usermanage = new Button("用户管理");
        usermanage.setLayoutX(0);
        usermanage.setLayoutY(150);
        usermanage.setPrefHeight(80);
        usermanage.setPrefWidth(200);
        AnchorPane ap = new AnchorPane();
        AnchorPane apleft = new AnchorPane();
        AnchorPane aprightuser = new AnchorPane();
        AnchorPane aprightgoods = new AnchorPane();
        AnchorPane apuser = new AnchorPane();

        AnchorPane apgoods = new AnchorPane();
        usermanage.setOnAction(e -> {
            apgoods.setVisible(false);
            apuser.setVisible(true);
        });

        Button goodsmanage = new Button("商品管理");
        goodsmanage.setLayoutX(0);
        goodsmanage.setLayoutY(300);
        goodsmanage.setPrefHeight(80);
        goodsmanage.setPrefWidth(200);
        goodsmanage.setOnAction(e -> {
            apgoods.setVisible(true);
            apuser.setVisible(false);
        });
        ap.getChildren().addAll(apleft, apuser, apgoods);
        apleft.setPrefHeight(600);
        apleft.setPrefWidth(200);
        apleft.setStyle("-fx-background-color:#708090");
        apuser.getChildren().add(aprightuser);
        apgoods.getChildren().add(aprightgoods);

        AnchorPane.setTopAnchor(aprightuser, 0.0);
        AnchorPane.setRightAnchor(aprightuser, 0.0);
        AnchorPane.setLeftAnchor(aprightuser, 0.0);
        AnchorPane.setBottomAnchor(aprightuser, 500.0);
        AnchorPane.setTopAnchor(aprightgoods, 0.0);
        AnchorPane.setRightAnchor(aprightgoods, 0.0);
        AnchorPane.setLeftAnchor(aprightgoods, 0.0);
        AnchorPane.setBottomAnchor(aprightgoods, 500.0);
        AnchorPane.setTopAnchor(apuser, 0.0);
        AnchorPane.setRightAnchor(apuser, 0.0);
        AnchorPane.setLeftAnchor(apuser, 200.0);
        AnchorPane.setBottomAnchor(apuser, 0.0);
        AnchorPane.setTopAnchor(apgoods, 0.0);
        AnchorPane.setRightAnchor(apgoods, 0.0);
        AnchorPane.setLeftAnchor(apgoods, 200.0);
        AnchorPane.setBottomAnchor(apgoods, 0.0);
        aprightuser.setStyle("-fx-background-color:#696969");
        aprightgoods.setStyle("-fx-background-color:#696969");
        apleft.getChildren().addAll(goodsmanage, usermanage);
        Scene scene = new Scene(ap);

        primaryStage.setScene(scene);
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
        primaryStage.setTitle("欢迎使用");
        primaryStage.setResizable(false);
        Label usermagelabel = new Label("用户管理");
        usermagelabel.setFont(Font.font("FangSong", FontWeight.BOLD, 20));
        usermagelabel.setTextFill(Color.BLACK);
        TableView<User> tvUsers = new TableView<User>(userService.getObservableList());
        TableColumn<User, String> userName = new TableColumn<User, String>("姓名");
        // TableColumn<User, String> user_id = new TableColumn<User, String>("id");
        TableColumn<User, String> userPwd = new TableColumn<User, String>("密码");
        // TableColumn<User, String> user_time = new TableColumn<User, String>("创建时间");
        TableColumn<User, String> userRole = new TableColumn<User, String>("身份");
        userPwd.setEditable(true);
        // tableview.;
        // tableview.getColumns().add(user_id);
        tvUsers.getColumns().add(userName);
        tvUsers.getColumns().add(userPwd); // 这里密码是不是不要显示比较好？
        tvUsers.getColumns().add(userRole);
        // tvUsers.getColumns().add(user_time);

        usermagelabel.setLayoutX(230);
        usermagelabel.setLayoutY(30);
        tvUsers.setLayoutX(100);
        tvUsers.setLayoutY(100);

        aprightuser.getChildren().add(usermagelabel);
        aprightuser.getChildren().add(tvUsers);
        Button createbt = new Button("创建用户");
        Button deletebt = new Button("删除用户");
        Button changebt = new Button("修改信息");
        createbt.setLayoutX(450);
        createbt.setLayoutY(140);
        deletebt.setLayoutX(450);
        deletebt.setLayoutY(220);
        changebt.setLayoutX(450);
        changebt.setLayoutY(300);
        // BorderPane
        // HBox
        // VBox
        aprightuser.getChildren().addAll(createbt, deletebt, changebt);
        // ObservableList<Goods> list1 = FXCollections.observableArrayList();
        userName.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<User, String> param) {
                        return new SimpleStringProperty(param.getValue().getName());
                    }
                });
        // user_id.setCellValueFactory(
        // new Callback<TableColumn.CellDataFeatures<User, String>,
        // ObservableValue<String>>() {
        // @Override
        // public ObservableValue<String> call(CellDataFeatures<User, String> param) {
        // return new SimpleStringProperty(String.valueOf(param.getValue().getId()));
        // }
        // });
        userPwd.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<User, String> param) {
                        return new SimpleStringProperty(param.getValue().getPwd());
                    }
                });
        // user_time.setCellValueFactory(
        // new Callback<TableColumn.CellDataFeatures<User, String>,
        // ObservableValue<String>>() {
        // @Override
        // public ObservableValue<String> call(CellDataFeatures<User, String> param) {
        // return new SimpleStringProperty(param.getValue().getCreateAt().toString());
        // }
        // });
        userRole.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<User, String> param) {
                        return new SimpleStringProperty(userService.getUserRoleName(param.getValue()));
                    }
                });

        createbt.setOnAction(e -> {
            init_gpUserManage_AddOrUpdateUser(null);

            // String name = RandomUtil.choice("123456789", 2);
            // String pwd = RandomUtil.choice("123456789", 2);
            // Role[] ll = Role.values();
            // Role l = RandomUtil.choice(ll);
            // User user = new User();
            // user.setName(name);
            // user.setPwd(pwd);
            // user.setRole(l);
            // userService.addUser(user);
        });

        deletebt.setOnAction(e -> {
            User selectedUser = tvUsers.getSelectionModel().getSelectedItem();
            if (selectedUser == null) {
                // System.out.println("请选择一个用户");
                DialogUtil.showWarningDialog("警告", null, "请选择一个用户");
            }
            if (selectedUser.getRole() == Role.ADMIN) {
                DialogUtil.showWarningDialog("警告", null, "权限不足");
            }
            userService.deleteUser(selectedUser);
        });

        changebt.setOnAction(e -> {
            User selectedUser = tvUsers.getSelectionModel().getSelectedItem();
            if (selectedUser == null) {
                // System.out.println("请选择一个用户");
                DialogUtil.showWarningDialog("警告", null, "请选择一个用户");
            }
            init_gpUserManage_AddOrUpdateUser(selectedUser);
        });

        Label goodsmageLabel = new Label("商品管理");
        goodsmageLabel.setFont(Font.font("FangSong", FontWeight.BOLD, 20));
        goodsmageLabel.setTextFill(Color.BLACK);

        TableView<Goods> tableview1 = new TableView<Goods>(goodsService.getObservableList());
        // TableColumn<Goods, String> goods_id = new TableColumn<Goods, String>("id");
        // // 感觉不用显示出来
        TableColumn<Goods, String> goods_name = new TableColumn<Goods, String>("商品名");
        TableColumn<Goods, String> goods_price = new TableColumn<Goods, String>("价格");
        TableColumn<Goods, String> goods_category = new TableColumn<Goods, String>("分类");
        TableColumn<Goods, String> goods_number = new TableColumn<Goods, String>("数量");
        TableColumn<Goods, String> goods_unit = new TableColumn<Goods, String>("单位");
        TableColumn<Goods, String> goods_onsell = new TableColumn<Goods, String>("在售");

        // tableview1.getColumns().add(goods_id);
        tableview1.getColumns().add(goods_name);
        tableview1.getColumns().add(goods_price);
        tableview1.getColumns().add(goods_category);
        tableview1.getColumns().add(goods_number);
        tableview1.getColumns().add(goods_unit);
        tableview1.getColumns().add(goods_onsell);
        goodsmageLabel.setLayoutX(230);
        goodsmageLabel.setLayoutY(30);
        tableview1.setLayoutX(10);
        tableview1.setLayoutY(100);

        aprightgoods.getChildren().add(goodsmageLabel);
        aprightgoods.getChildren().add(tableview1);
        Button importbt = new Button("进货表");
        Button remainbt = new Button("库存");
        Button sellbt = new Button("销售表");
        Button derivebt = new Button("导出");
        derivebt.setOnAction(e -> {
            File file = FileUtil.showSaveDialog();
            if (file == null) {
                DialogUtil.showWarningDialog("警告", null, "请选择一个文件");
                return;
            }
            goodsService.export(file);
            DialogUtil.showInfoDialog("提示", null, "导出成功");
        });
        importbt.setLayoutX(60);
        importbt.setLayoutY(520);
        remainbt.setLayoutX(170);
        remainbt.setLayoutY(520);
        sellbt.setLayoutX(280);
        sellbt.setLayoutY(520);
        derivebt.setLayoutX(390);
        derivebt.setLayoutY(520);
        aprightgoods.getChildren().addAll(importbt, remainbt, sellbt, derivebt);
        apgoods.setVisible(false);
        primaryStage.show();
        primaryStage.getIcons().add(FileUtil.getImage("user.png"));

    }

}
