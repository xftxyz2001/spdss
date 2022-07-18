package com.xftxyz.smms.view;

import java.math.BigDecimal;

import com.xftxyz.smms.entity.Goods;
import com.xftxyz.smms.entity.Purchase;
import com.xftxyz.smms.service.GoodsService;
import com.xftxyz.smms.service.PurchaseService;
import com.xftxyz.smms.service.ServiceFactory;
import com.xftxyz.smms.service.SupplierService;
import com.xftxyz.smms.type.MyValues;
import com.xftxyz.smms.utils.DialogUtil;
import com.xftxyz.smms.utils.FileUtil;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PurchaserView {
    // Service
    protected GoodsService goodsService;
    protected SupplierService supplierService;
    protected PurchaseService purchaseService;

    // UI控件
    Stage primaryStage;
    Scene scene;
    BorderPane bpRoot;

    GridPane gp;
    Label lbSupplierName;
    ChoiceBox<String> cbSupplierName;
    Label lbGoodsName;
    TextField tfGoodsName;
    Label lbPrice;
    TextField tfPrice;
    Label lbUnit;
    TextField tfUnit;
    Label lbNum;
    TextField tfNum;
    // Label lbDescription;
    // TextField tfDescription;

    Button btSubmit;
    Button btReset;

    Label statusBar;

    private void initService() {
        // Service初始化
        goodsService = ServiceFactory.getGoodsService();
        supplierService = ServiceFactory.getSupplierService();
        purchaseService = ServiceFactory.getPurchaseService();
    }

    private void initUI() {
        primaryStage = new Stage();
        bpRoot = new BorderPane();
        scene = new Scene(bpRoot, MyValues.SCENE_WIDTH, MyValues.SCENE_HEIGHT);

        gp = new GridPane();
        lbSupplierName = new Label("供应商名称");
        cbSupplierName = new ChoiceBox<String>();
        cbSupplierName.getItems().addAll(supplierService.getAllSupplierName());
        lbGoodsName = new Label("商品名称");
        tfGoodsName = new TextField();
        tfGoodsName.textProperty().addListener((observable, oldValue, newValue) -> {
            Goods goods = goodsService.getGoodsByName(newValue);
            if (goods != null) {
                tfUnit.setText(goods.getUnit());
            }
        });
        lbPrice = new Label("单价");
        tfPrice = new TextField();
        lbUnit = new Label("/");
        tfUnit = new TextField();
        lbNum = new Label("数量");
        tfNum = new TextField();
        // lbDescription = new Label("描述");
        // tfDescription = new TextField();
        btSubmit = new Button("提交");
        btSubmit.setOnAction(e -> {
            Purchase newPurchase = new Purchase();
            try {

                newPurchase.setSupplierName(cbSupplierName.getValue());
                newPurchase.setGoodsName(tfGoodsName.getText());
                newPurchase.setPrice(new BigDecimal(tfPrice.getText()));
                newPurchase.setUnit(tfUnit.getText());
                newPurchase.setNum(new BigDecimal(tfNum.getText()));
                // newPurchase.setTime(Timestamp.valueOf(LocalDateTime.now()));
            } catch (Exception e1) {
                DialogUtil.showWarningDialog("警告", null, "请输入正确的数据");
                return;
            }

            boolean isSucc = purchaseService.addPurchase(newPurchase);
            if (!isSucc) {
                DialogUtil.showWarningDialog("警告", null, "添加失败");
                return;
            }
            DialogUtil.showInfoDialog("提示", null, "添加成功");
            cbSupplierName.setValue(null);
            tfGoodsName.setText("");
            tfPrice.setText("");
            tfUnit.setText("");
            tfNum.setText("");
            // tfDescription.setText("");
        });
        btReset = new Button("重置");
        btReset.setOnAction(e -> {
            cbSupplierName.setValue(null);
            tfGoodsName.setText("");
            tfPrice.setText("");
            tfUnit.setText("");
            tfNum.setText("");
            // tfDescription.setText("");
        });
        gp.add(lbSupplierName, 0, 0);
        gp.add(cbSupplierName, 1, 0);
        gp.add(lbGoodsName, 0, 1);
        gp.add(tfGoodsName, 1, 1);
        gp.add(lbPrice, 0, 2);
        gp.add(tfPrice, 1, 2);
        gp.add(lbUnit, 2, 2);
        gp.add(tfUnit, 3, 2);
        gp.add(lbNum, 0, 3);
        gp.add(tfNum, 1, 3);
        gp.add(btSubmit, 0, 4);
        gp.add(btReset, 1, 4);

        bpRoot.setCenter(gp);

        // 状态栏
        statusBar = new Label("欢迎使用XX超市管理系统，当前用户" + ServiceFactory.getUserService().getCurrentUserName() + "（"
                + ServiceFactory.getUserService().getCurrentUserRoleName() + "）");
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
