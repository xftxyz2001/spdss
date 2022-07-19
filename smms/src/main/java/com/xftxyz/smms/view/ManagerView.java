package com.xftxyz.smms.view;

import java.io.File;

import com.xftxyz.smms.entity.User;
import com.xftxyz.smms.type.Role;
import com.xftxyz.smms.utils.DialogUtil;
import com.xftxyz.smms.utils.FileUtil;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class ManagerView extends AdminView {
    @Override
    public GridPane init_gpUserManage_AddOrUpdateUser(User user) {
        if (this.gpUserManage_AddOrUpdateUser == null) {
            this.gpUserManage_AddOrUpdateUser = new GridPane();
            this.lbgpUserManage_AddOrUpdateUser_UserName = new Label("用户名：");
            this.lbgpUserManage_AddOrUpdateUser_UserName.setTextFill(Paint.valueOf("#0000FF"));
            this.lbgpUserManage_AddOrUpdateUser_UserName.setFont(Font.font("华文黑体", 15));
            this.tfgpUserManage_AddOrUpdateUser_UserName = new TextField();
            this.lbgpUserManage_AddOrUpdateUser_UserPassword = new Label("密码：");
            this.lbgpUserManage_AddOrUpdateUser_UserPassword.setTextFill(Paint.valueOf("#0000FF"));
            this.lbgpUserManage_AddOrUpdateUser_UserPassword.setFont(Font.font("华文黑体", 15));
            this.tfgpUserManage_AddOrUpdateUser_Password = new TextField();
            this.lbgpUserManage_AddOrUpdateUser_Role = new Label("角色：");
            this.lbgpUserManage_AddOrUpdateUser_Role.setTextFill(Paint.valueOf("#0000FF"));
            this.lbgpUserManage_AddOrUpdateUser_Role.setFont(Font.font("华文黑体", 15));
            this.cbgpUserManage_AddOrUpdateUser_Role = new ChoiceBox<Role>();
            // this.cbgpUserManage_AddOrUpdateUser_Role.getItems().addAll(Role.values());
            Role[] roles = { Role.MANAGER, Role.PURCHASER, Role.SALER };
            this.cbgpUserManage_AddOrUpdateUser_Role.setPrefWidth(150);
            this.cbgpUserManage_AddOrUpdateUser_Role.getItems().addAll(roles);
            this.btngpUserManage_AddOrUpdateUser_OK = new Button("确定",submituserImageView);
            this.btngpUserManage_AddOrUpdateUser_OK.setContentDisplay(ContentDisplay.LEFT);
            this.btngpUserManage_AddOrUpdateUser_OK.setPrefWidth(120);
            this.btngpUserManage_AddOrUpdateUser_Cancel = new Button("取消",cancelgoodsImageView);
            this.btngpUserManage_AddOrUpdateUser_Cancel.setContentDisplay(ContentDisplay.LEFT);
            this.btngpUserManage_AddOrUpdateUser_Cancel.setPrefWidth(120);
            this.btngpUserManage_AddOrUpdateUser_Cancel.setOnAction(e -> {
                this.bpUserManage.setCenter(this.tvUserManage);
            });
            this.gpUserManage_AddOrUpdateUser.add(this.lbgpUserManage_AddOrUpdateUser_UserName, 0, 0);
            this.gpUserManage_AddOrUpdateUser.add(this.tfgpUserManage_AddOrUpdateUser_UserName, 1, 0);
            this.gpUserManage_AddOrUpdateUser.add(this.lbgpUserManage_AddOrUpdateUser_UserPassword, 0, 1);
            this.gpUserManage_AddOrUpdateUser.add(this.tfgpUserManage_AddOrUpdateUser_Password, 1, 1);
            this.gpUserManage_AddOrUpdateUser.add(this.lbgpUserManage_AddOrUpdateUser_Role, 0, 2);
            this.gpUserManage_AddOrUpdateUser.add(this.cbgpUserManage_AddOrUpdateUser_Role, 1, 2);
            this.gpUserManage_AddOrUpdateUser.add(this.btngpUserManage_AddOrUpdateUser_OK, 0, 3);
            this.gpUserManage_AddOrUpdateUser.add(this.btngpUserManage_AddOrUpdateUser_Cancel, 1, 3);
            this.gpUserManage_AddOrUpdateUser.setPadding(new Insets(30.0));
            this.gpUserManage_AddOrUpdateUser.setVgap(20);
            this.gpUserManage_AddOrUpdateUser.setHgap(20);
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
                this.bpUserManage.setCenter(this.tvUserManage);
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
                this.bpUserManage.setCenter(this.tvUserManage);
            });
        }
        return this.gpUserManage_AddOrUpdateUser;
    }

    @Override
    public void initUserManage() {
    	userImageView = new ImageView("usermanage.png");
    	userImageView.setFitWidth(40);
    	userImageView.setFitHeight(40);
        btnUserManage = new Button("用户管理",userImageView);
        btnUserManage.setTextFill(Paint.valueOf("#FFFFFF"));
        btnUserManage.setContentDisplay(ContentDisplay.TOP);
        btnUserManage.setStyle("-fx-background-color:#63B8FF;"+"-fx-background-radius:15");
       
        btnUserManage.setPrefHeight(60);
        btnUserManage.setPrefWidth(80);
        btnUserManage.setOnAction(e -> {
            bpRoot.setCenter(bpUserManage);
        });

        // 用户管理面板
        btnUserManageAddUser = new Button("添加用户");
        btnUserManageAddUser.setOnAction(e -> {
            GridPane gp = init_gpUserManage_AddOrUpdateUser(null);
            if (gp != null) {
                bpUserManage.setCenter(gp);
            }
        });
        btnUserManageDeleteUser = new Button("删除用户");
        btnUserManageDeleteUser.setOnAction(e -> {
            User selectedUser = tvUserManage.getSelectionModel().getSelectedItem();
            if (selectedUser == null) {
                // System.out.println("请选择一个用户");
                DialogUtil.showWarningDialog("警告", null, "请选择一个用户");
                return;
            }
            if (selectedUser.getRole() == Role.ADMIN) {
                // 警告没有权限
                DialogUtil.showWarningDialog("警告", null, "没有权限");
                return;
            }
            userService.deleteUser(selectedUser);
        });
        btnUserManageUpdateUser = new Button("修改用户");
        btnUserManageUpdateUser.setOnAction(e -> {
            User selectedUser = tvUserManage.getSelectionModel().getSelectedItem();
            if (selectedUser == null) {
                // System.out.println("请选择一个用户");
                DialogUtil.showWarningDialog("警告", null, "请选择一个用户");
                return;
            }
            selectedUser = userService.getUpdateCopy(selectedUser);
            GridPane gp = init_gpUserManage_AddOrUpdateUser(selectedUser);
            if (gp != null) {
                bpUserManage.setCenter(gp);
            }
        });
        btnExportUser = new Button("导出用户");
        btnExportUser.setOnAction(e -> {
            File file = FileUtil.showSaveDialog();
            if (file == null) {
                DialogUtil.showWarningDialog("警告", null, "请选择一个文件");
                return;
            }
            userService.export(file);
            DialogUtil.showInfoDialog("提示", null, "导出成功");
        });
        vbUserManage = new VBox(btnUserManageAddUser, btnUserManageDeleteUser, btnUserManageUpdateUser, btnExportUser);
        vbUserManage.setPrefWidth(120);
        vbUserManage.setSpacing(20);
        vbUserManage.setStyle("-fx-background-color:#87CEFA");
        btnUserManageAddUser.setPrefWidth(120);
        btnUserManageAddUser.setPrefHeight(30);
        btnUserManageAddUser.setTextFill(Paint.valueOf("#FFFFFF"));
        btnUserManageAddUser.setStyle("-fx-background-color:#00B2EE;"+"-fx-background-radius:10");
        btnUserManageDeleteUser.setPrefWidth(120);
        btnUserManageDeleteUser.setPrefHeight(30);
        btnUserManageDeleteUser.setTextFill(Paint.valueOf("#FFFFFF"));
        btnUserManageDeleteUser.setStyle("-fx-background-color:#00B2EE;"+"-fx-background-radius:10");
        btnUserManageUpdateUser.setPrefWidth(120);
        btnUserManageUpdateUser.setPrefHeight(30);
        btnUserManageUpdateUser.setTextFill(Paint.valueOf("#FFFFFF"));
        btnUserManageUpdateUser.setStyle("-fx-background-color:#00B2EE;"+"-fx-background-radius:10");
        btnExportUser.setPrefWidth(120);
        btnExportUser.setPrefHeight(30);
        btnExportUser.setTextFill(Paint.valueOf("#FFFFFF"));
        btnExportUser.setStyle("-fx-background-color:#00B2EE;"+"-fx-background-radius:10");
        tvUserManage = new TableView<User>(userService.getObservableList());
        TableColumn<User, String> tvUserManage_tcUserName = new TableColumn<>("用户名");
        tvUserManage_tcUserName.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<User, String> param) {
                        return new SimpleStringProperty(param.getValue().getName());
                    }

                });
        TableColumn<User, String> tvUserManage_tcUserPwd = new TableColumn<>("密码");
        tvUserManage_tcUserPwd.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<User, String> param) {
                        return new SimpleStringProperty(param.getValue().getPwd());
                    }

                });
        TableColumn<User, String> tvUserManage_tcUserRole = new TableColumn<>("角色");
        tvUserManage_tcUserRole.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<User, String> param) {
                        return new SimpleStringProperty(userService.getUserRoleName(param.getValue()));
                    }
                });
        tvUserManage.getColumns().add(tvUserManage_tcUserName);
        tvUserManage.getColumns().add(tvUserManage_tcUserPwd);
        tvUserManage.getColumns().add(tvUserManage_tcUserRole);
        bpUserManage = new BorderPane();
        bpUserManage.setLeft(vbUserManage);
        bpUserManage.setCenter(tvUserManage);
        
    }

}
