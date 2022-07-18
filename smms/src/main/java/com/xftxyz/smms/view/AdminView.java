package com.xftxyz.smms.view;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.xftxyz.smms.entity.Goods;
import com.xftxyz.smms.entity.Purchase;
import com.xftxyz.smms.entity.Sale;
import com.xftxyz.smms.entity.Supplier;
import com.xftxyz.smms.entity.User;
import com.xftxyz.smms.service.GoodsService;
import com.xftxyz.smms.service.PurchaseService;
import com.xftxyz.smms.service.SaleService;
import com.xftxyz.smms.service.ServiceFactory;
import com.xftxyz.smms.service.SupplierService;
import com.xftxyz.smms.service.UserService;
import com.xftxyz.smms.type.MyValues;
import com.xftxyz.smms.type.Role;
import com.xftxyz.smms.utils.DialogUtil;
import com.xftxyz.smms.utils.FileUtil;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AdminView {
    // Service
    protected UserService userService;
    protected GoodsService goodsService;
    protected SupplierService supplierService;
    protected PurchaseService purchaseService;
    protected SaleService saleService;

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

    // 用户管理面板
    BorderPane bpUserManage;
    VBox vbUserManage;
    Button btnUserManageAddUser;
    Button btnUserManageDeleteUser;
    Button btnUserManageUpdateUser;
    Button btnExportUser;
    TableView<User> tvUserManage;
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

    // 商品管理面板
    BorderPane bpGoodsManage;
    VBox vbGoodsManage;
    Button btnGoodsManageAddGoods;
    Button btnGoodsManageDeleteGoods;
    Button btnGoodsManageUpdateGoods;
    Button btnExportGoods;
    TableView<Goods> tvGoodsManage;
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

    // 供应商管理面板
    BorderPane bpSupplierManage;
    VBox vbSupplierManage;
    Button btnSupplierManageAddSupplier;
    Button btnSupplierManageDeleteSupplier;
    Button btnSupplierManageUpdateSupplier;
    Button btnExportSupplier;
    TableView<Supplier> tvSupplierManage;
    GridPane gpSupplierManage_AddOrUpdateSupplier;
    Label lbgpSupplierManage_AddOrUpdateSupplier_SupplierName;
    TextField tfgpSupplierManage_AddOrUpdateSupplier_SupplierName;
    Label lbgpSupplierManage_AddOrUpdateSupplier_SupplierTel;
    TextField tfgpSupplierManage_AddOrUpdateSupplier_SupplierTel;
    Label lbgpSupplierManage_AddOrUpdateSupplier_SupplierAddress;
    TextField tfgpSupplierManage_AddOrUpdateSupplier_SupplierAddress;
    Button btngpSupplierManage_AddOrUpdateSupplier_OK;
    Button btngpSupplierManage_AddOrUpdateSupplier_Cancel;
    Supplier sgpSupplierManage_AddOrUpdateSupplier_selectedSupplier;

    // 采购管理面板
    BorderPane bpPurchaseManage;
    Button btnPurchaseManageAddPurchase;
    Button btnPurchaseManageDeletePurchase;
    Button btnPurchaseManageUpdatePurchase;
    Button btnExportPurchase;
    VBox vbPurchaseManage;
    TableView<Purchase> tvPurchaseManage;
    GridPane gpPurchaseManage_AddOrUpdatePurchase;
    Label lbgpPurchaseManage_AddOrUpdatePurchase_SupplierName;
    // TextField tfgpPurchaseManage_AddOrUpdatePurchase_SupplierName;
    ChoiceBox<String> cbgpPurchaseManage_AddOrUpdatePurchase_SupplierName;
    Label lbgpPurchaseManage_AddOrUpdatePurchase_GoodsPrice;
    TextField tfgpPurchaseManage_AddOrUpdatePurchase_GoodsPrice;
    Label lbgpPurchaseManage_AddOrUpdatePurchase_GoodsName;
    TextField tfgpPurchaseManage_AddOrUpdatePurchase_GoodsName;
    Label lbgpPurchaseManage_AddOrUpdatePurchase_GoodsNum;
    TextField tfgpPurchaseManage_AddOrUpdatePurchase_GoodsNum;
    Label lbgpPurchaseManage_AddOrUpdatePurchase_GoodsUnit;
    TextField tfgpPurchaseManage_AddOrUpdatePurchase_GoodsUnit;
    Label lbgpPurchaseManage_AddOrUpdatePurchase_PurchaseTime;
    TextField tfgpPurchaseManage_AddOrUpdatePurchase_PurchaseTime;
    Button btngpPurchaseManage_AddOrUpdatePurchase_OK;
    Button btngpPurchaseManage_AddOrUpdatePurchase_Cancel;
    Purchase pgpPurchaseManage_AddOrUpdatePurchase_selectedPurchase;

    // 销售管理面板
    BorderPane bpSaleManage;
    Button btnSaleManageAddSale;
    Button btnSaleManageDeleteSale;
    Button btnSaleManageUpdateSale;
    Button btnExportSale;
    VBox vbSaleManage;
    TableView<Sale> tvSaleManage;
    GridPane gpSaleManage_AddOrUpdateSale;
    Label lbgpSaleManage_AddOrUpdateSale_GoodsName;
    ChoiceBox<String> cbgpSaleManage_AddOrUpdateSale_GoodsName;
    Label lbgpSaleManage_AddOrUpdateSale_GoodsPrice;
    TextField tfgpSaleManage_AddOrUpdateSale_GoodsPrice;
    Label lbgpSaleManage_AddOrUpdateSale_GoodsNum;
    TextField tfgpSaleManage_AddOrUpdateSale_GoodsNum;
    Label lbgpSaleManage_AddOrUpdateSale_GoodsUnit;
    TextField tfgpSaleManage_AddOrUpdateSale_GoodsUnit;
    Label lbgpSaleManage_AddOrUpdateSale_SaleTime;
    TextField tfgpSaleManage_AddOrUpdateSale_SaleTime;
    Button btngpSaleManage_AddOrUpdateSale_OK;
    Button btngpSaleManage_AddOrUpdateSale_Cancel;
    Sale sgpSaleManage_AddOrUpdateSale_selectedSale;

    Label statusBar;

    private void initService() {
        // Service初始化
        userService = ServiceFactory.getUserService();
        goodsService = ServiceFactory.getGoodsService();
        supplierService = ServiceFactory.getSupplierService();
        purchaseService = ServiceFactory.getPurchaseService();
        saleService = ServiceFactory.getSaleService();
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
            this.cbgpUserManage_AddOrUpdateUser_Role.getItems().addAll(Role.values());
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
                this.bpGoodsManage.setCenter(this.tvGoodsManage);
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
                this.bpGoodsManage.setCenter(this.tvGoodsManage);
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
                this.bpGoodsManage.setCenter(this.tvGoodsManage);
            });

        }
        return this.gpGoodsManage_AddOrUpdateGoods;
    }

    public GridPane init_gpSupplierManage_AddOrUpdateSupplier(Supplier supplier) {
        if (this.gpSupplierManage_AddOrUpdateSupplier == null) {
            this.gpSupplierManage_AddOrUpdateSupplier = new GridPane();
            this.lbgpSupplierManage_AddOrUpdateSupplier_SupplierName = new Label("供应商名：");
            this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierName = new TextField();
            this.lbgpSupplierManage_AddOrUpdateSupplier_SupplierTel = new Label("联系方式：");
            this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierTel = new TextField();
            this.lbgpSupplierManage_AddOrUpdateSupplier_SupplierAddress = new Label("地址：");
            this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierAddress = new TextField();
            this.btngpSupplierManage_AddOrUpdateSupplier_OK = new Button("确定");
            this.btngpSupplierManage_AddOrUpdateSupplier_Cancel = new Button("取消");
            this.btngpSupplierManage_AddOrUpdateSupplier_Cancel.setOnAction(e -> {
                this.bpSupplierManage.setCenter(this.tvSupplierManage);
            });
            this.gpSupplierManage_AddOrUpdateSupplier.add(this.lbgpSupplierManage_AddOrUpdateSupplier_SupplierName, 0,
                    0);
            this.gpSupplierManage_AddOrUpdateSupplier.add(this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierName, 1,
                    0);
            this.gpSupplierManage_AddOrUpdateSupplier.add(this.lbgpSupplierManage_AddOrUpdateSupplier_SupplierTel, 0,
                    1);
            this.gpSupplierManage_AddOrUpdateSupplier.add(this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierTel, 1,
                    1);
            this.gpSupplierManage_AddOrUpdateSupplier.add(this.lbgpSupplierManage_AddOrUpdateSupplier_SupplierAddress,
                    0, 2);
            this.gpSupplierManage_AddOrUpdateSupplier.add(this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierAddress,
                    1, 2);
            this.gpSupplierManage_AddOrUpdateSupplier.add(this.btngpSupplierManage_AddOrUpdateSupplier_OK, 0, 3);
            this.gpSupplierManage_AddOrUpdateSupplier.add(this.btngpSupplierManage_AddOrUpdateSupplier_Cancel, 1, 3);

        }
        if (supplier != null) {
            this.sgpSupplierManage_AddOrUpdateSupplier_selectedSupplier = supplierService.getUpdateCopy(supplier);

            this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierName.setText(supplier.getName());
            this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierTel.setText(supplier.getTel());
            this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierAddress.setText(supplier.getAddress());
            this.btngpSupplierManage_AddOrUpdateSupplier_OK.setOnAction(e -> {
                this.sgpSupplierManage_AddOrUpdateSupplier_selectedSupplier
                        .setName(this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierName.getText());
                this.sgpSupplierManage_AddOrUpdateSupplier_selectedSupplier
                        .setTel(this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierTel.getText());
                this.sgpSupplierManage_AddOrUpdateSupplier_selectedSupplier
                        .setAddress(this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierAddress.getText());
                boolean isSucc = supplierService
                        .updateSupplier(this.sgpSupplierManage_AddOrUpdateSupplier_selectedSupplier);
                if (!isSucc) {
                    // Debug.log("更新供应商失败");
                    DialogUtil.showWarningDialog("警告", null, "更新供应商失败");
                }
                this.bpSupplierManage.setCenter(this.tvSupplierManage);
            });
        } else {
            this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierName.setText("");
            this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierTel.setText("");
            this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierAddress.setText("");
            this.btngpSupplierManage_AddOrUpdateSupplier_OK.setOnAction(e -> {
                Supplier newSupplier = new Supplier();
                newSupplier.setName(this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierName.getText());
                newSupplier.setTel(this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierTel.getText());
                newSupplier.setAddress(this.tfgpSupplierManage_AddOrUpdateSupplier_SupplierAddress.getText());
                boolean isSucc = supplierService.addSupplier(newSupplier);
                if (!isSucc) {
                    // Debug.log("添加供应商失败");
                    DialogUtil.showWarningDialog("警告", null, "添加供应商失败");
                }
                this.bpSupplierManage.setCenter(this.tvSupplierManage);
            });

        }
        return this.gpSupplierManage_AddOrUpdateSupplier;
    }

    public GridPane init_gpPurchaseManage_AddOrUpdatePurchase(Purchase purchase) {
        if (this.gpPurchaseManage_AddOrUpdatePurchase == null) {
            this.gpPurchaseManage_AddOrUpdatePurchase = new GridPane();
            this.lbgpPurchaseManage_AddOrUpdatePurchase_SupplierName = new Label("供应商名：");
            // this.tfgpPurchaseManage_AddOrUpdatePurchase_SupplierName = new TextField();
            this.cbgpPurchaseManage_AddOrUpdatePurchase_SupplierName = new ChoiceBox<>();
            this.lbgpPurchaseManage_AddOrUpdatePurchase_GoodsName = new Label("商品名：");
            this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsName = new TextField();
            this.lbgpPurchaseManage_AddOrUpdatePurchase_GoodsPrice = new Label("单价：");
            this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsPrice = new TextField();
            this.lbgpPurchaseManage_AddOrUpdatePurchase_GoodsNum = new Label("数量：");
            this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsNum = new TextField();
            this.lbgpPurchaseManage_AddOrUpdatePurchase_GoodsUnit = new Label("单位：");
            this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsUnit = new TextField();
            this.lbgpPurchaseManage_AddOrUpdatePurchase_PurchaseTime = new Label("采购时间：");
            this.tfgpPurchaseManage_AddOrUpdatePurchase_PurchaseTime = new TextField(
                    Timestamp.valueOf(LocalDateTime.now()).toString());
            this.btngpPurchaseManage_AddOrUpdatePurchase_OK = new Button("确定");
            this.btngpPurchaseManage_AddOrUpdatePurchase_Cancel = new Button("取消");
            this.btngpPurchaseManage_AddOrUpdatePurchase_Cancel.setOnAction(e -> {
                this.bpPurchaseManage.setCenter(this.tvPurchaseManage);
            });
            this.gpPurchaseManage_AddOrUpdatePurchase.add(this.lbgpPurchaseManage_AddOrUpdatePurchase_SupplierName, 0,
                    0);
            this.gpPurchaseManage_AddOrUpdatePurchase.add(this.cbgpPurchaseManage_AddOrUpdatePurchase_SupplierName, 1,
                    0);
            this.gpPurchaseManage_AddOrUpdatePurchase.add(this.lbgpPurchaseManage_AddOrUpdatePurchase_GoodsName, 0, 1);
            this.gpPurchaseManage_AddOrUpdatePurchase.add(this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsName, 1, 1);
            this.gpPurchaseManage_AddOrUpdatePurchase.add(this.lbgpPurchaseManage_AddOrUpdatePurchase_GoodsPrice, 0, 2);
            this.gpPurchaseManage_AddOrUpdatePurchase.add(this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsPrice, 1, 2);
            this.gpPurchaseManage_AddOrUpdatePurchase.add(this.lbgpPurchaseManage_AddOrUpdatePurchase_GoodsNum, 0, 3);
            this.gpPurchaseManage_AddOrUpdatePurchase.add(this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsNum, 1, 3);
            this.gpPurchaseManage_AddOrUpdatePurchase.add(this.lbgpPurchaseManage_AddOrUpdatePurchase_GoodsUnit, 0, 4);
            this.gpPurchaseManage_AddOrUpdatePurchase.add(this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsUnit, 1, 4);
            this.gpPurchaseManage_AddOrUpdatePurchase.add(this.lbgpPurchaseManage_AddOrUpdatePurchase_PurchaseTime, 0,
                    5);
            this.gpPurchaseManage_AddOrUpdatePurchase.add(this.tfgpPurchaseManage_AddOrUpdatePurchase_PurchaseTime, 1,
                    5);
            this.gpPurchaseManage_AddOrUpdatePurchase.add(this.btngpPurchaseManage_AddOrUpdatePurchase_OK, 0, 6);
            this.gpPurchaseManage_AddOrUpdatePurchase.add(this.btngpPurchaseManage_AddOrUpdatePurchase_Cancel, 1, 6);
        }
        ObservableList<String> items = this.cbgpPurchaseManage_AddOrUpdatePurchase_SupplierName.getItems();
        items.clear();
        items.addAll(supplierService.getAllSupplierName());
        if (purchase != null) {
            this.pgpPurchaseManage_AddOrUpdatePurchase_selectedPurchase = purchaseService.getUpdateCopy(purchase);

            this.cbgpPurchaseManage_AddOrUpdatePurchase_SupplierName.setValue(purchase.getSupplierName());
            this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsName.setText(purchase.getGoodsName());
            this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsPrice.setText(purchase.getPrice().toString());
            this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsNum.setText(purchase.getNum().toString());
            this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsUnit.setText(purchase.getUnit());
            this.tfgpPurchaseManage_AddOrUpdatePurchase_PurchaseTime.setText(purchase.getTime().toString());
            this.btngpPurchaseManage_AddOrUpdatePurchase_OK.setOnAction(e -> {
                this.pgpPurchaseManage_AddOrUpdatePurchase_selectedPurchase
                        .setSupplierName(this.cbgpPurchaseManage_AddOrUpdatePurchase_SupplierName.getValue());
                this.pgpPurchaseManage_AddOrUpdatePurchase_selectedPurchase
                        .setGoodsName(this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsName.getText());
                this.pgpPurchaseManage_AddOrUpdatePurchase_selectedPurchase
                        .setPrice(new BigDecimal(this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsPrice.getText()));
                this.pgpPurchaseManage_AddOrUpdatePurchase_selectedPurchase
                        .setNum(new BigDecimal(this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsNum.getText()));
                this.pgpPurchaseManage_AddOrUpdatePurchase_selectedPurchase
                        .setUnit(this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsUnit.getText());
                this.pgpPurchaseManage_AddOrUpdatePurchase_selectedPurchase
                        .setTime(Timestamp.valueOf(this.tfgpPurchaseManage_AddOrUpdatePurchase_PurchaseTime.getText()));
                boolean isSucc = purchaseService
                        .updatePurchase(this.pgpPurchaseManage_AddOrUpdatePurchase_selectedPurchase);
                if (!isSucc) {
                    // Debug.log("更新采购记录失败");
                    DialogUtil.showWarningDialog("警告", null, "更新采购记录失败");
                }
                this.bpPurchaseManage.setCenter(this.tvPurchaseManage);
            });

        } else {
            this.cbgpPurchaseManage_AddOrUpdatePurchase_SupplierName.setValue(null);
            this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsName.setText("");
            this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsPrice.setText("");
            this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsNum.setText("");
            this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsUnit.setText("");
            this.tfgpPurchaseManage_AddOrUpdatePurchase_PurchaseTime.setText(
                    Timestamp.valueOf(LocalDateTime.now()).toString());

            this.btngpPurchaseManage_AddOrUpdatePurchase_OK.setOnAction(e -> {
                Purchase newPurchase = new Purchase();
                newPurchase.setSupplierName(this.cbgpPurchaseManage_AddOrUpdatePurchase_SupplierName.getValue());
                newPurchase.setGoodsName(this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsName.getText());
                newPurchase.setPrice(new BigDecimal(this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsPrice.getText()));
                newPurchase.setNum(new BigDecimal(this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsNum.getText()));
                newPurchase.setUnit(this.tfgpPurchaseManage_AddOrUpdatePurchase_GoodsUnit.getText());
                newPurchase
                        .setTime(Timestamp.valueOf(this.tfgpPurchaseManage_AddOrUpdatePurchase_PurchaseTime.getText()));
                boolean isSucc = purchaseService.addPurchase(newPurchase);
                if (!isSucc) {
                    // Debug.log("添加采购记录失败");
                    DialogUtil.showWarningDialog("警告", null, "添加采购记录失败");
                }
                this.bpPurchaseManage.setCenter(this.tvPurchaseManage);

            });
        }

        return this.gpPurchaseManage_AddOrUpdatePurchase;
    }

    public GridPane init_gpSaleManage_AddOrUpdateSale(Sale sale) {
        if (this.gpSaleManage_AddOrUpdateSale == null) {
            this.gpSaleManage_AddOrUpdateSale = new GridPane();
            this.lbgpSaleManage_AddOrUpdateSale_GoodsName = new Label("商品名：");
            this.cbgpSaleManage_AddOrUpdateSale_GoodsName = new ChoiceBox<>();
            this.cbgpSaleManage_AddOrUpdateSale_GoodsName.getItems().addAll(goodsService.getAllGoodsName());
            this.lbgpSaleManage_AddOrUpdateSale_GoodsPrice = new Label("单价：");
            this.tfgpSaleManage_AddOrUpdateSale_GoodsPrice = new TextField();
            this.lbgpSaleManage_AddOrUpdateSale_GoodsNum = new Label("数量：");
            this.tfgpSaleManage_AddOrUpdateSale_GoodsNum = new TextField();
            this.lbgpSaleManage_AddOrUpdateSale_GoodsUnit = new Label("单位：");
            this.tfgpSaleManage_AddOrUpdateSale_GoodsUnit = new TextField();
            this.lbgpSaleManage_AddOrUpdateSale_SaleTime = new Label("销售时间：");
            this.tfgpSaleManage_AddOrUpdateSale_SaleTime = new TextField();
            this.btngpSaleManage_AddOrUpdateSale_OK = new Button("确定");
            this.btngpSaleManage_AddOrUpdateSale_Cancel = new Button("取消");
            this.btngpSaleManage_AddOrUpdateSale_Cancel.setOnAction(e -> {
                this.bpSaleManage.setCenter(this.tvSaleManage);
            });

            this.gpSaleManage_AddOrUpdateSale.add(this.lbgpSaleManage_AddOrUpdateSale_GoodsName, 0, 0);
            this.gpSaleManage_AddOrUpdateSale.add(this.cbgpSaleManage_AddOrUpdateSale_GoodsName, 1, 0);
            this.gpSaleManage_AddOrUpdateSale.add(this.lbgpSaleManage_AddOrUpdateSale_GoodsPrice, 0, 1);
            this.gpSaleManage_AddOrUpdateSale.add(this.tfgpSaleManage_AddOrUpdateSale_GoodsPrice, 1, 1);
            this.gpSaleManage_AddOrUpdateSale.add(this.lbgpSaleManage_AddOrUpdateSale_GoodsNum, 0, 2);
            this.gpSaleManage_AddOrUpdateSale.add(this.tfgpSaleManage_AddOrUpdateSale_GoodsNum, 1, 2);
            this.gpSaleManage_AddOrUpdateSale.add(this.lbgpSaleManage_AddOrUpdateSale_GoodsUnit, 0, 3);
            this.gpSaleManage_AddOrUpdateSale.add(this.tfgpSaleManage_AddOrUpdateSale_GoodsUnit, 1, 3);
            this.gpSaleManage_AddOrUpdateSale.add(this.lbgpSaleManage_AddOrUpdateSale_SaleTime, 0, 4);
            this.gpSaleManage_AddOrUpdateSale.add(this.tfgpSaleManage_AddOrUpdateSale_SaleTime, 1, 4);
            this.gpSaleManage_AddOrUpdateSale.add(this.btngpSaleManage_AddOrUpdateSale_OK, 0, 5);
            this.gpSaleManage_AddOrUpdateSale.add(this.btngpSaleManage_AddOrUpdateSale_Cancel, 1, 5);

        }
        if (sale != null) {
            this.sgpSaleManage_AddOrUpdateSale_selectedSale = saleService.getUpdateCopy(sale);

            this.cbgpSaleManage_AddOrUpdateSale_GoodsName.setValue(sale.getGoodsName());
            this.tfgpSaleManage_AddOrUpdateSale_GoodsPrice.setText(sale.getPrice().toString());
            this.tfgpSaleManage_AddOrUpdateSale_GoodsNum.setText(sale.getNum().toString());
            this.tfgpSaleManage_AddOrUpdateSale_GoodsUnit.setText(sale.getUnit());
            this.tfgpSaleManage_AddOrUpdateSale_SaleTime.setText(sale.getTime().toString());
            this.btngpSaleManage_AddOrUpdateSale_OK.setOnAction(e -> {
                this.sgpSaleManage_AddOrUpdateSale_selectedSale
                        .setGoodName(this.cbgpSaleManage_AddOrUpdateSale_GoodsName.getValue());
                this.sgpSaleManage_AddOrUpdateSale_selectedSale
                        .setPrice(new BigDecimal(this.tfgpSaleManage_AddOrUpdateSale_GoodsPrice.getText()));
                this.sgpSaleManage_AddOrUpdateSale_selectedSale
                        .setNum(new BigDecimal(this.tfgpSaleManage_AddOrUpdateSale_GoodsNum.getText()));
                this.sgpSaleManage_AddOrUpdateSale_selectedSale
                        .setUnit(this.tfgpSaleManage_AddOrUpdateSale_GoodsUnit.getText());
                this.sgpSaleManage_AddOrUpdateSale_selectedSale
                        .setTime(Timestamp.valueOf(this.tfgpSaleManage_AddOrUpdateSale_SaleTime.getText()));
                boolean isSucc = saleService.updateSale(this.sgpSaleManage_AddOrUpdateSale_selectedSale);
                if (!isSucc) {
                    // Debug.log("更新销售记录失败");
                    DialogUtil.showWarningDialog("警告", null, "更新销售记录失败");
                }
                this.bpSaleManage.setCenter(this.tvSaleManage);
            });
        } else {
            this.cbgpSaleManage_AddOrUpdateSale_GoodsName.setValue(null);
            this.tfgpSaleManage_AddOrUpdateSale_GoodsPrice.setText("");
            this.tfgpSaleManage_AddOrUpdateSale_GoodsNum.setText("");
            this.tfgpSaleManage_AddOrUpdateSale_GoodsUnit.setText("");
            this.tfgpSaleManage_AddOrUpdateSale_SaleTime.setText(Timestamp.valueOf(LocalDateTime.now()).toString());

            this.btngpSaleManage_AddOrUpdateSale_OK.setOnAction(e -> {
                Sale newSale = new Sale();
                newSale.setGoodName(this.cbgpSaleManage_AddOrUpdateSale_GoodsName.getValue());
                newSale.setPrice(new BigDecimal(this.tfgpSaleManage_AddOrUpdateSale_GoodsPrice.getText()));
                newSale.setNum(new BigDecimal(this.tfgpSaleManage_AddOrUpdateSale_GoodsNum.getText()));
                newSale.setUnit(this.tfgpSaleManage_AddOrUpdateSale_GoodsUnit.getText());
                newSale.setTime(Timestamp.valueOf(this.tfgpSaleManage_AddOrUpdateSale_SaleTime.getText()));
                boolean isSucc = saleService.addSale(newSale);
                if (!isSucc) {
                    // Debug.log("添加销售记录失败");
                    DialogUtil.showWarningDialog("警告", null, "添加销售记录失败");
                }
                this.bpSaleManage.setCenter(this.tvSaleManage);
            });
        }
        return this.gpSaleManage_AddOrUpdateSale;
    }

    // 加载管理页面
    public void initUserManage() {
        btnUserManage = new Button("用户管理");
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

    public void initGoodsManage() {
        btnGoodsManage = new Button("商品管理");
        btnGoodsManage.setOnAction(e -> {
            bpRoot.setCenter(bpGoodsManage);
        });

        // 商品管理面板
        btnGoodsManageAddGoods = new Button("添加商品");
        btnGoodsManageAddGoods.setOnAction(e -> {
            GridPane gp = init_gpGoodsManage_AddOrUpdateGoods(null);
            if (gp != null) {
                bpGoodsManage.setCenter(gp);
            }
            // Goods goods = new Goods();
            // goods.setName(RandomUtil.choice("123456789", 2));
            // goods.setPrice(RandomUtil.randomInt(1, 100));
            // goods.setSupplier(RandomUtil.choice(supplierService.getObservableList()));
            // goodsService.addGoods(goods);
        });
        btnGoodsManageDeleteGoods = new Button("删除商品");
        btnGoodsManageDeleteGoods.setOnAction(e -> {
            Goods selectedGoods = tvGoodsManage.getSelectionModel().getSelectedItem();
            if (selectedGoods == null) {
                System.out.println("请选择一个商品");
                DialogUtil.showWarningDialog("警告", null, "请选择一个商品");
                return;
            }
            goodsService.deleteGoods(selectedGoods);
        });
        btnGoodsManageUpdateGoods = new Button("修改商品");
        btnGoodsManageUpdateGoods.setOnAction(e -> {
            Goods selectedGoods = tvGoodsManage.getSelectionModel().getSelectedItem();
            if (selectedGoods == null) {
                System.out.println("请选择一个商品");
                DialogUtil.showWarningDialog("警告", null, "请选择一个商品");
                return;
            }
            GridPane gp = init_gpGoodsManage_AddOrUpdateGoods(selectedGoods);
            if (gp != null) {
                bpGoodsManage.setCenter(gp);
            }
            // selectedGoods = goodsService.getUpdateCopy(selectedGoods);
            // selectedGoods.setName(RandomUtil.choice("123456789", 2));
            // selectedGoods.setPrice(RandomUtil.randomInt(1, 100));
            // selectedGoods.setSupplier(RandomUtil.choice(supplierService.getObservableList()));
            // goodsService.updateGoods(selectedGoods);
        });
        btnExportGoods = new Button("导出商品");
        btnExportGoods.setOnAction(e -> {
            File file = FileUtil.showSaveDialog();
            if (file == null) {
                DialogUtil.showWarningDialog("警告", null, "请选择一个文件");
                return;
            }
            goodsService.export(file);
            DialogUtil.showInfoDialog("提示", null, "导出成功");
        });
        vbGoodsManage = new VBox(btnGoodsManageAddGoods, btnGoodsManageDeleteGoods, btnGoodsManageUpdateGoods,
                btnExportGoods);
        tvGoodsManage = new TableView<Goods>(goodsService.getObservableList());
        TableColumn<Goods, String> tvGoodsManage_tcGoodsName = new TableColumn<>("商品名");
        tvGoodsManage_tcGoodsName.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Goods, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Goods, String> param) {
                        return new SimpleStringProperty(param.getValue().getName());
                    }

                });
        TableColumn<Goods, String> tvGoodsManage_tcGoodsPrice = new TableColumn<>("单价");
        tvGoodsManage_tcGoodsPrice.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Goods, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Goods, String> param) {
                        return new SimpleStringProperty(param.getValue().getPrice().toString());
                    }

                });
        TableColumn<Goods, String> tvGoodsManage_tcGoodsNum = new TableColumn<>("库存数量");
        tvGoodsManage_tcGoodsNum.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Goods, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Goods, String> param) {
                        return new SimpleStringProperty(param.getValue().getNum().toString());
                    }

                });
        TableColumn<Goods, String> tvGoodsManage_tcGoodsUnit = new TableColumn<>("单位");
        tvGoodsManage_tcGoodsUnit.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Goods, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Goods, String> param) {
                        return new SimpleStringProperty(param.getValue().getUnit());
                    }

                });
        TableColumn<Goods, String> tvGoodsManage_tcGoodsDescribe = new TableColumn<>("描述");
        tvGoodsManage_tcGoodsDescribe.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Goods, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Goods, String> param) {
                        return new SimpleStringProperty(param.getValue().getDescribe());
                    }

                });
        tvGoodsManage.getColumns().add(tvGoodsManage_tcGoodsName);
        tvGoodsManage.getColumns().add(tvGoodsManage_tcGoodsPrice);
        tvGoodsManage.getColumns().add(tvGoodsManage_tcGoodsNum);
        tvGoodsManage.getColumns().add(tvGoodsManage_tcGoodsUnit);
        tvGoodsManage.getColumns().add(tvGoodsManage_tcGoodsDescribe);
        bpGoodsManage = new BorderPane();
        bpGoodsManage.setLeft(vbGoodsManage);
        bpGoodsManage.setCenter(tvGoodsManage);

    }

    public void initSupplierManage() {
        btnSupplierManage = new Button("供应商管理");
        btnSupplierManage.setOnAction(e -> {
            bpRoot.setCenter(bpSupplierManage);
        });

        // 供应商管理面板
        btnSupplierManageAddSupplier = new Button("添加供应商");
        btnSupplierManageAddSupplier.setOnAction(e -> {
            GridPane gp = init_gpSupplierManage_AddOrUpdateSupplier(null);
            if (gp != null) {
                bpSupplierManage.setCenter(gp);
            }
            // Supplier supplier = new Supplier();
            // supplier.setName(RandomUtil.choice("123456789", 2));
            // supplier.setAddress(RandomUtil.choice("123456789", 2));
            // supplier.setPhone(RandomUtil.choice("123456789", 2));
            // supplierService.addSupplier(supplier);
        });
        btnSupplierManageDeleteSupplier = new Button("删除供应商");
        btnSupplierManageDeleteSupplier.setOnAction(e -> {
            Supplier selectedSupplier = tvSupplierManage.getSelectionModel().getSelectedItem();
            if (selectedSupplier == null) {
                // System.out.println("请选择一个供应商");
                DialogUtil.showWarningDialog("警告", null, "请选择一个供应商");
                return;
            }
            supplierService.deleteSupplier(selectedSupplier);
        });
        btnSupplierManageUpdateSupplier = new Button("修改供应商");
        btnSupplierManageUpdateSupplier.setOnAction(e -> {
            Supplier selectedSupplier = tvSupplierManage.getSelectionModel().getSelectedItem();
            if (selectedSupplier == null) {
                // System.out.println("请选择一个供应商");
                DialogUtil.showWarningDialog("警告", null, "请选择一个供应商");
                return;
            }
            GridPane gp = init_gpSupplierManage_AddOrUpdateSupplier(selectedSupplier);
            if (gp != null) {
                bpSupplierManage.setCenter(gp);
            }
            // selectedSupplier = supplierService.getUpdateCopy(selectedSupplier);
            // selectedSupplier.setName(RandomUtil.choice("123456789", 2));
            // selectedSupplier.setAddress(RandomUtil.choice("123456789", 2));
            // selectedSupplier.setPhone(RandomUtil.choice("123456789", 2));
            // supplierService.updateSupplier(selectedSupplier);
        });
        btnExportSupplier = new Button("导出供应商");
        btnExportSupplier.setOnAction(e -> {
            File file = FileUtil.showSaveDialog();
            if (file == null) {
                DialogUtil.showWarningDialog("警告", null, "请选择一个文件");
                return;
            }
            supplierService.export(file);
            DialogUtil.showWarningDialog("提示", null, "导出成功");
        });
        vbSupplierManage = new VBox(btnSupplierManageAddSupplier, btnSupplierManageDeleteSupplier,
                btnSupplierManageUpdateSupplier, btnExportSupplier);
        tvSupplierManage = new TableView<Supplier>(supplierService.getObservableList());
        TableColumn<Supplier, String> tvSupplierManage_tcSupplierName = new TableColumn<>("供应商名");
        tvSupplierManage_tcSupplierName.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Supplier, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Supplier, String> param) {
                        return new SimpleStringProperty(param.getValue().getName());
                    }

                });
        TableColumn<Supplier, String> tvSupplierManage_tcSupplierPhone = new TableColumn<>("联系方式");
        tvSupplierManage_tcSupplierPhone.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Supplier, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Supplier, String> param) {
                        return new SimpleStringProperty(param.getValue().getTel());
                    }

                });
        TableColumn<Supplier, String> tvSupplierManage_tcSupplierAddress = new TableColumn<>("地址");
        tvSupplierManage_tcSupplierAddress.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Supplier, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Supplier, String> param) {
                        return new SimpleStringProperty(param.getValue().getAddress());
                    }

                });
        tvSupplierManage.getColumns().add(tvSupplierManage_tcSupplierName);
        tvSupplierManage.getColumns().add(tvSupplierManage_tcSupplierPhone);
        tvSupplierManage.getColumns().add(tvSupplierManage_tcSupplierAddress);
        bpSupplierManage = new BorderPane();
        bpSupplierManage.setLeft(vbSupplierManage);
        bpSupplierManage.setCenter(tvSupplierManage);

    }

    public void initPurchaseManage() {
        btnPurchaseManage = new Button("采购管理");
        btnPurchaseManage.setOnAction(e -> {
            bpRoot.setCenter(bpPurchaseManage);
        });
        // 采购管理面板
        btnPurchaseManageAddPurchase = new Button("添加采购");
        btnPurchaseManageAddPurchase.setOnAction(e -> {
            GridPane gp = init_gpPurchaseManage_AddOrUpdatePurchase(null);
            if (gp != null) {
                bpPurchaseManage.setCenter(gp);
            }
            // Purchase purchase = new Purchase();
            // purchase.setGoods(RandomUtil.choice(goodsService.getObservableList()));
            // purchase.setSupplier(RandomUtil.choice(supplierService.getObservableList()));
            // purchase.setNum(RandomUtil.randomInt(1, 100));
            // purchase.setPrice(RandomUtil.randomInt(1, 100));
            // purchase.setTime(RandomUtil.randomDate(LocalDate.now(),
            // LocalDate.now().plusDays(1)));
            // purchaseService.addPurchase(purchase);
        });
        btnPurchaseManageDeletePurchase = new Button("删除采购");
        btnPurchaseManageDeletePurchase.setOnAction(e -> {
            Purchase selectedPurchase = tvPurchaseManage.getSelectionModel().getSelectedItem();
            if (selectedPurchase == null) {
                // System.out.println("请选择一个采购");
                DialogUtil.showWarningDialog("警告", null, "请选择一个采购");
                return;
            }
            purchaseService.deletePurchase(selectedPurchase);
        });
        btnPurchaseManageUpdatePurchase = new Button("修改采购");
        btnPurchaseManageUpdatePurchase.setOnAction(e -> {
            Purchase selectedPurchase = tvPurchaseManage.getSelectionModel().getSelectedItem();
            if (selectedPurchase == null) {
                // System.out.println("请选择一个采购");
                DialogUtil.showWarningDialog("警告", null, "请选择一个采购");
                return;
            }
            GridPane gp = init_gpPurchaseManage_AddOrUpdatePurchase(selectedPurchase);
            if (gp != null) {
                bpPurchaseManage.setCenter(gp);
            }
            // selectedPurchase = purchaseService.getUpdateCopy(selectedPurchase);
            // selectedPurchase.setGoods(RandomUtil.choice(goodsService.getObservableList()));
            // selectedPurchase.setSupplier(RandomUtil.choice(supplierService.getObservableList()));
            // selectedPurchase.setNum(RandomUtil.randomInt(1, 100));
            // selectedPurchase.setPrice(RandomUtil.randomInt(1, 100));
            // selectedPurchase.setTime(RandomUtil.randomDate(LocalDate.now(),
            // LocalDate.now().plusDays(1)));
            // purchaseService.updatePurchase(selectedPurchase);
        });
        btnExportPurchase = new Button("导出采购");
        btnExportPurchase.setOnAction(e -> {
            File file = FileUtil.showSaveDialog();
            if (file == null) {
                DialogUtil.showWarningDialog("警告", null, "请选择一个文件");
                return;
            }
            purchaseService.export(file);
            DialogUtil.showWarningDialog("提示", null, "导出成功");
        });
        vbPurchaseManage = new VBox(btnPurchaseManageAddPurchase, btnPurchaseManageDeletePurchase,
                btnPurchaseManageUpdatePurchase, btnExportPurchase);
        tvPurchaseManage = new TableView<Purchase>(purchaseService.getObservableList());
        TableColumn<Purchase, String> tvPurchaseManage_tcPurchaseSupplierName = new TableColumn<>("供应商名");
        tvPurchaseManage_tcPurchaseSupplierName.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Purchase, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Purchase, String> param) {
                        return new SimpleStringProperty(param.getValue().getSupplierName());
                    }

                });
        TableColumn<Purchase, String> tvPurchaseManage_tcPurchaseGoodsName = new TableColumn<>("商品名");
        tvPurchaseManage_tcPurchaseGoodsName.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Purchase, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Purchase, String> param) {
                        return new SimpleStringProperty(param.getValue().getGoodsName());
                    }

                });
        TableColumn<Purchase, String> tvPurchaseManage_tcPurchasePrice = new TableColumn<>("单价");
        tvPurchaseManage_tcPurchasePrice.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Purchase, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Purchase, String> param) {
                        return new SimpleStringProperty(param.getValue().getPrice().toString());
                    }

                });
        TableColumn<Purchase, String> tvPurchaseManage_tcPurchaseNum = new TableColumn<>("数量");
        tvPurchaseManage_tcPurchaseNum.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Purchase, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Purchase, String> param) {
                        return new SimpleStringProperty(param.getValue().getNum().toString());
                    }

                });
        TableColumn<Purchase, String> tvPurchaseManage_tcPurchaseUnit = new TableColumn<>("单位");
        tvPurchaseManage_tcPurchaseUnit.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Purchase, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Purchase, String> param) {
                        return new SimpleStringProperty(param.getValue().getUnit());
                    }

                });
        TableColumn<Purchase, String> tvPurchaseManage_tcPurchaseTime = new TableColumn<>("采购时间");
        tvPurchaseManage_tcPurchaseTime.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Purchase, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Purchase, String> param) {
                        return new SimpleStringProperty(param.getValue().getTime().toString());
                    }

                });
        tvPurchaseManage.getColumns().add(tvPurchaseManage_tcPurchaseSupplierName);
        tvPurchaseManage.getColumns().add(tvPurchaseManage_tcPurchaseGoodsName);
        tvPurchaseManage.getColumns().add(tvPurchaseManage_tcPurchasePrice);
        tvPurchaseManage.getColumns().add(tvPurchaseManage_tcPurchaseNum);
        tvPurchaseManage.getColumns().add(tvPurchaseManage_tcPurchaseUnit);
        tvPurchaseManage.getColumns().add(tvPurchaseManage_tcPurchaseTime);
        bpPurchaseManage = new BorderPane();
        bpPurchaseManage.setLeft(vbPurchaseManage);
        bpPurchaseManage.setCenter(tvPurchaseManage);

    }

    public void initSaleManage() {
        btnSaleManage = new Button("销售管理");
        btnSaleManage.setOnAction(e -> {
            bpRoot.setCenter(bpSaleManage);
        });

        // 销售管理面板
        btnSaleManageAddSale = new Button("添加销售");
        btnSaleManageAddSale.setOnAction(e -> {
            GridPane gp = init_gpSaleManage_AddOrUpdateSale(null);
            if (gp != null) {
                bpSaleManage.setCenter(gp);
            }
            // Sale sale = new Sale();
            // sale.setGoods(RandomUtil.choice(goodsService.getObservableList()));
            // sale.setCustomer(RandomUtil.choice(customerService.getObservableList()));
            // sale.setNum(RandomUtil.randomInt(1, 100));
            // sale.setPrice(RandomUtil.randomInt(1, 100));
            // sale.setTime(RandomUtil.randomDate(LocalDate.now(),
            // LocalDate.now().plusDays(1)));
            // saleService.addSale(sale);
        });
        btnSaleManageDeleteSale = new Button("删除销售");
        btnSaleManageDeleteSale.setOnAction(e -> {
            Sale selectedSale = tvSaleManage.getSelectionModel().getSelectedItem();
            if (selectedSale == null) {
                // System.out.println("请选择一个销售");
                DialogUtil.showWarningDialog("警告", null, "请选择一个销售");
                return;
            }
            saleService.deleteSale(selectedSale);
        });
        btnSaleManageUpdateSale = new Button("修改销售");
        btnSaleManageUpdateSale.setOnAction(e -> {
            Sale selectedSale = tvSaleManage.getSelectionModel().getSelectedItem();
            if (selectedSale == null) {
                // System.out.println("请选择一个销售");
                DialogUtil.showWarningDialog("警告", null, "请选择一个销售");
                return;
            }
            GridPane gp = init_gpSaleManage_AddOrUpdateSale(selectedSale);
            if (gp != null) {
                bpSaleManage.setCenter(gp);
            }
            // selectedSale = saleService.getUpdateCopy(selectedSale);
            // selectedSale.setGoods(RandomUtil.choice(goodsService.getObservableList()));
            // selectedSale.setCustomer(RandomUtil.choice(customerService.getObservableList()));
            // selectedSale.setNum(RandomUtil.randomInt(1, 100));
            // selectedSale.setPrice(RandomUtil.randomInt(1, 100));
            // selectedSale.setTime(RandomUtil.randomDate(LocalDate.now(),
            // LocalDate.now().plusDays(1)));
            // saleService.updateSale(selectedSale);
        });
        btnExportSale = new Button("导出销售");
        btnExportSale.setOnAction(e -> {
            File file = FileUtil.showSaveDialog();
            if (file == null) {
                DialogUtil.showWarningDialog("警告", null, "请选择一个文件");
                return;
            }
            saleService.export(file);
            DialogUtil.showWarningDialog("提示", null, "导出成功");
        });
        vbSaleManage = new VBox(btnSaleManageAddSale, btnSaleManageDeleteSale, btnSaleManageUpdateSale, btnExportSale);
        tvSaleManage = new TableView<Sale>(saleService.getObservableList());
        TableColumn<Sale, String> tvSaleManage_tcSaleGoodName = new TableColumn<>("商品名");
        tvSaleManage_tcSaleGoodName.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Sale, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Sale, String> param) {
                        return new SimpleStringProperty(param.getValue().getGoodsName());
                    }

                });
        TableColumn<Sale, String> tvSaleManage_tcSalePrice = new TableColumn<>("单价");
        tvSaleManage_tcSalePrice.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Sale, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Sale, String> param) {
                        return new SimpleStringProperty(param.getValue().getPrice().toString());
                    }

                });
        TableColumn<Sale, String> tvSaleManage_tcSaleNum = new TableColumn<>("数量");
        tvSaleManage_tcSaleNum.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Sale, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Sale, String> param) {
                        return new SimpleStringProperty(param.getValue().getNum().toString());
                    }

                });
        TableColumn<Sale, String> tvSaleManage_tcSaleUnit = new TableColumn<>("单位");
        tvSaleManage_tcSaleUnit.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Sale, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Sale, String> param) {
                        return new SimpleStringProperty(param.getValue().getUnit());
                    }

                });
        TableColumn<Sale, String> tvSaleManage_tcSaleTime = new TableColumn<>("销售时间");
        tvSaleManage_tcSaleTime.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Sale, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Sale, String> param) {
                        return new SimpleStringProperty(param.getValue().getTime().toString());
                    }

                });
        tvSaleManage.getColumns().add(tvSaleManage_tcSaleGoodName);
        tvSaleManage.getColumns().add(tvSaleManage_tcSalePrice);
        tvSaleManage.getColumns().add(tvSaleManage_tcSaleNum);
        tvSaleManage.getColumns().add(tvSaleManage_tcSaleUnit);
        tvSaleManage.getColumns().add(tvSaleManage_tcSaleTime);
        bpSaleManage = new BorderPane();
        bpSaleManage.setLeft(vbSaleManage);
        bpSaleManage.setCenter(tvSaleManage);

    }

    public void initUI() {
        // UI控件初始化
        primaryStage = new Stage();
        bpRoot = new BorderPane();
        scene = new Scene(bpRoot, MyValues.SCENE_WIDTH, MyValues.SCENE_HEIGHT);

        // top
        initUserManage();
        initGoodsManage();
        initSupplierManage();
        initPurchaseManage();
        initSaleManage();

        // btnUserManage, btnGoodsManage, btnSupplierManage, btnPurchaseManage,
        // btnSaleManage
        hbManageMenu = new HBox();
        if (btnUserManage != null) {
            hbManageMenu.getChildren().add(btnUserManage);
        }
        if (btnGoodsManage != null) {
            hbManageMenu.getChildren().add(btnGoodsManage);
        }
        if (btnSupplierManage != null) {
            hbManageMenu.getChildren().add(btnSupplierManage);
        }
        if (btnPurchaseManage != null) {
            hbManageMenu.getChildren().add(btnPurchaseManage);
        }
        if (btnSaleManage != null) {
            hbManageMenu.getChildren().add(btnSaleManage);
        }

        // HBox.setHgrow(hbManageMenu, Priority.ALWAYS);
        // hbManageMenu.spacingProperty().bind(scene.widthProperty().subtract(btnUserManage.widthProperty())
        // .subtract(btnGoodsManage.widthProperty()).subtract(btnSupplierManage.widthProperty())
        // .subtract(btnPurchaseManage.widthProperty()).subtract(btnSaleManage.widthProperty()).divide(4));
        bpRoot.setTop(hbManageMenu);

        // center
        bpRoot.setCenter(bpUserManage);

        // 状态栏
        statusBar = new Label("欢迎使用XX超市管理系统，当前用户" + userService.getCurrentUserName() + "（"
                + userService.getCurrentUserRoleName() + "）");
        bpRoot.setBottom(statusBar);
    }

    public void start() throws Exception {
        initService();

        initUI();

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("欢迎使用  " + getClass().getName());
        primaryStage.getIcons().add(FileUtil.getImage("user.png"));

    }
}
