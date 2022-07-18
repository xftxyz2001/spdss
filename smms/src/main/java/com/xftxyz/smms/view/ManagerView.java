package com.xftxyz.smms.view;

import com.xftxyz.smms.entity.User;
import com.xftxyz.smms.type.Role;
import com.xftxyz.smms.utils.DialogUtil;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ManagerView extends AdminView {
    @Override
    public GridPane init_gpUserManage_AddOrUpdateUser(User user) {
        if (this.gpUserManage_AddOrUpdateUser == null) {
            this.gpUserManage_AddOrUpdateUser = new GridPane();
            this.lbgpUserManage_AddOrUpdateUser_UserName = new Label("用户名：");
            this.tfgpUserManage_AddOrUpdateUser_UserName = new TextField();
            this.lbgpUserManage_AddOrUpdateUser_UserPassword = new Label("密码：");
            this.tfgpUserManage_AddOrUpdateUser_Password = new TextField();
            this.lbgpUserManage_AddOrUpdateUser_Role = new Label("角色：");
            this.cbgpUserManage_AddOrUpdateUser_Role = new ChoiceBox<Role>();
            // this.cbgpUserManage_AddOrUpdateUser_Role.getItems().addAll(Role.values());
            Role[] roles = { Role.MANAGER, Role.PURCHASER, Role.SALER };
            this.cbgpUserManage_AddOrUpdateUser_Role.getItems().addAll(roles);
            this.btngpUserManage_AddOrUpdateUser_OK = new Button("确定");

            this.btngpUserManage_AddOrUpdateUser_Cancel = new Button("取消");
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
}
