package com.xftxyz.smms.view;

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
import com.xftxyz.smms.utils.Debug;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AdminView {
    // Service
    private UserService userService;
    private GoodsService goodsService;
    private SupplierService supplierService;
    private PurchaseService purchaseService;
    private SaleService saleService;

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

    BorderPane bpGoodsManage;
    VBox vbGoodsManage;
    Button btnGoodsManageAddGoods;
    Button btnGoodsManageDeleteGoods;
    Button btnGoodsManageUpdateGoods;
    TableView<Goods> tvGoodsManage;

    BorderPane bpSupplierManage;
    VBox vbSupplierManage;
    Button btnSupplierManageAddSupplier;
    Button btnSupplierManageDeleteSupplier;
    Button btnSupplierManageUpdateSupplier;
    TableView<Supplier> tvSupplierManage;

    BorderPane bpPurchaseManage;
    Button btnPurchaseManageAddPurchase;
    Button btnPurchaseManageDeletePurchase;
    Button btnPurchaseManageUpdatePurchase;
    VBox vbPurchaseManage;
    TableView<Purchase> tvPurchaseManage;

    BorderPane bpSaleManage;
    Button btnSaleManageAddSale;
    Button btnSaleManageDeleteSale;
    Button btnSaleManageUpdateSale;
    VBox vbSaleManage;
    TableView<Sale> tvSaleManage;

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
                    Debug.log("更新用户失败");
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
                    Debug.log("添加用户失败");
                } 
                this.bpUserManage.setCenter(this.tvUserManage);
            });
        }
        return this.gpUserManage_AddOrUpdateUser;
    }

    public void initUI() {
        // UI控件初始化
        primaryStage = new Stage();
        bpRoot = new BorderPane();
        scene = new Scene(bpRoot, MyValues.SCENE_WIDTH, MyValues.SCENE_HEIGHT);

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
        // HBox.setHgrow(hbManageMenu, Priority.ALWAYS);
        // hbManageMenu.spacingProperty().bind(scene.widthProperty().subtract(btnUserManage.widthProperty())
        // .subtract(btnGoodsManage.widthProperty()).subtract(btnSupplierManage.widthProperty())
        // .subtract(btnPurchaseManage.widthProperty()).subtract(btnSaleManage.widthProperty()).divide(4));
        bpRoot.setTop(hbManageMenu);

        // center
        // 用户管理面板
        btnUserManageAddUser = new Button("添加用户");
        btnUserManageAddUser.setOnAction(e -> {
            bpUserManage.setCenter(init_gpUserManage_AddOrUpdateUser(null));
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
        btnUserManageUpdateUser.setOnAction(e -> {
            User selectedUser = tvUserManage.getSelectionModel().getSelectedItem();
            if (selectedUser == null) {
                System.out.println("请选择一个用户");
            }
            selectedUser = userService.getUpdateCopy(selectedUser);
            bpUserManage.setCenter(init_gpUserManage_AddOrUpdateUser(selectedUser));
        });
        vbUserManage = new VBox(btnUserManageAddUser, btnUserManageDeleteUser, btnUserManageUpdateUser);
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

        // 商品管理面板
        btnGoodsManageAddGoods = new Button("添加商品");
        btnGoodsManageAddGoods.setOnAction(e -> {
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
            }
            goodsService.deleteGoods(selectedGoods);
        });
        btnGoodsManageUpdateGoods = new Button("修改商品");
        btnGoodsManageUpdateGoods.setOnAction(e -> {
            Goods selectedGoods = tvGoodsManage.getSelectionModel().getSelectedItem();
            if (selectedGoods == null) {
                System.out.println("请选择一个商品");
            }
            // selectedGoods = goodsService.getUpdateCopy(selectedGoods);
            // selectedGoods.setName(RandomUtil.choice("123456789", 2));
            // selectedGoods.setPrice(RandomUtil.randomInt(1, 100));
            // selectedGoods.setSupplier(RandomUtil.choice(supplierService.getObservableList()));
            // goodsService.updateGoods(selectedGoods);
        });
        vbGoodsManage = new VBox(btnGoodsManageAddGoods, btnGoodsManageDeleteGoods, btnGoodsManageUpdateGoods);
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

        // 供应商管理面板
        btnSupplierManageAddSupplier = new Button("添加供应商");
        btnSupplierManageAddSupplier.setOnAction(e -> {
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
                System.out.println("请选择一个供应商");
            }
            supplierService.deleteSupplier(selectedSupplier);
        });
        btnSupplierManageUpdateSupplier = new Button("修改供应商");
        btnSupplierManageUpdateSupplier.setOnAction(e -> {
            Supplier selectedSupplier = tvSupplierManage.getSelectionModel().getSelectedItem();
            if (selectedSupplier == null) {
                System.out.println("请选择一个供应商");
            }
            // selectedSupplier = supplierService.getUpdateCopy(selectedSupplier);
            // selectedSupplier.setName(RandomUtil.choice("123456789", 2));
            // selectedSupplier.setAddress(RandomUtil.choice("123456789", 2));
            // selectedSupplier.setPhone(RandomUtil.choice("123456789", 2));
            // supplierService.updateSupplier(selectedSupplier);
        });
        vbSupplierManage = new VBox(btnSupplierManageAddSupplier, btnSupplierManageDeleteSupplier,
                btnSupplierManageUpdateSupplier);
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

        // 采购管理面板
        btnPurchaseManageAddPurchase = new Button("添加采购");
        btnPurchaseManageAddPurchase.setOnAction(e -> {
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
                System.out.println("请选择一个采购");
            }
            purchaseService.deletePurchase(selectedPurchase);
        });
        btnPurchaseManageUpdatePurchase = new Button("修改采购");
        btnPurchaseManageUpdatePurchase.setOnAction(e -> {
            Purchase selectedPurchase = tvPurchaseManage.getSelectionModel().getSelectedItem();
            if (selectedPurchase == null) {
                System.out.println("请选择一个采购");
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
        vbPurchaseManage = new VBox(btnPurchaseManageAddPurchase, btnPurchaseManageDeletePurchase,
                btnPurchaseManageUpdatePurchase);
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

        // 销售管理面板
        btnSaleManageAddSale = new Button("添加销售");
        btnSaleManageAddSale.setOnAction(e -> {
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
                System.out.println("请选择一个销售");
            }
            saleService.deleteSale(selectedSale);
        });
        btnSaleManageUpdateSale = new Button("修改销售");
        btnSaleManageUpdateSale.setOnAction(e -> {
            Sale selectedSale = tvSaleManage.getSelectionModel().getSelectedItem();
            if (selectedSale == null) {
                System.out.println("请选择一个销售");
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
        vbSaleManage = new VBox(btnSaleManageAddSale, btnSaleManageDeleteSale, btnSaleManageUpdateSale);
        tvSaleManage = new TableView<Sale>(saleService.getObservableList());
        TableColumn<Sale, String> tvSaleManage_tcSaleGoodName = new TableColumn<>("商品名");
        tvSaleManage_tcSaleGoodName.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Sale, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Sale, String> param) {
                        return new SimpleStringProperty(param.getValue().getGoodName());
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
        primaryStage.setTitle("欢迎使用");
        primaryStage.getIcons().add(FileUtil.getImage("user.png"));

    }
}
