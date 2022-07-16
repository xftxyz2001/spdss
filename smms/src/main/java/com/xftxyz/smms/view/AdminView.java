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
import com.xftxyz.smms.utils.FileUtil;

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

    public void initUI() {
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
            // String name = RandomUtil.choice("123456789", 2);
            // String pwd = RandomUtil.choice("123456789", 2);
            // Role[] ll = Role.values();
            // Role l = RandomUtil.choice(ll);
            // selectedUser.setName(name);
            // selectedUser.setPwd(pwd);
            // selectedUser.setRole(l);

            userService.updateUser(selectedUser);
        });
        vbUserManage = new VBox(btnUserManageAddUser, btnUserManageDeleteUser, btnUserManageUpdateUser);
        tvUserManage = new TableView<User>(userService.getObservableList());
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
