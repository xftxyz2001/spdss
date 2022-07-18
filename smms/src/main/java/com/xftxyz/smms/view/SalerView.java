package com.xftxyz.smms.view;

import java.math.BigDecimal;

import com.xftxyz.smms.entity.Goods;
import com.xftxyz.smms.entity.Sale;
import com.xftxyz.smms.service.GoodsService;
import com.xftxyz.smms.service.SaleService;
import com.xftxyz.smms.service.ServiceFactory;
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

public class SalerView {

    // Service
    private GoodsService goodsService;
    private SaleService saleService;

    // UI控件
    Stage primaryStage;
    Scene scene;
    BorderPane bpRoot;

    GridPane gp;
    Label lbGoodsName;
    ChoiceBox<String> cbGoodsName;
    Label lbPrice;
    TextField tfPrice;
    Label lbUnit;
    TextField tfUnit;
    Label lbNum;
    TextField tfNum;
    Label lbNuml;
    Label lbRepertory;

    Button btSubmit;
    Button btReset;

    Label statusBar;

    private void initService() {

        // Service初始化
        goodsService = ServiceFactory.getGoodsService();
        saleService = ServiceFactory.getSaleService();
    }

    public void initUI() {
        primaryStage = new Stage();
        bpRoot = new BorderPane();
        scene = new Scene(bpRoot, MyValues.SCENE_WIDTH, MyValues.SCENE_HEIGHT);

        gp = new GridPane();
        lbGoodsName = new Label("商品名称");
        cbGoodsName = new ChoiceBox<>();
        cbGoodsName.getItems().addAll(goodsService.getAllGoodsName());
        cbGoodsName.setOnAction(e -> {
            String goodsName = cbGoodsName.getValue();
            Goods goods = goodsService.getGoodsByName(goodsName);
            if (goods != null) {
                tfPrice.setText(goods.getPrice().toString());
                tfUnit.setText(goods.getUnit());
                lbRepertory.setText(goods.getNum().toString());

            }
        });
        lbPrice = new Label("单价");
        tfPrice = new TextField();
        lbUnit = new Label("/");
        tfUnit = new TextField();
        lbNum = new Label("数量");
        tfNum = new TextField();
        lbNuml = new Label("/");
        lbRepertory = new Label("");
        btSubmit = new Button("提交");
        btSubmit.setOnAction(e -> {
            Sale newSale = new Sale();
            try {
                newSale.setGoodName(cbGoodsName.getValue());
                newSale.setPrice(new BigDecimal(tfPrice.getText()));
                newSale.setUnit(tfUnit.getText());
                newSale.setNum(new BigDecimal(tfNum.getText()));

            } catch (Exception e1) {
                DialogUtil.showWarningDialog("警告", null, "请输入正确的数据");
                return;
            }
            boolean isSucc = saleService.addSale(newSale);
            if (!isSucc) {
                DialogUtil.showWarningDialog("警告", null, "添加失败");
                return;
            }
            DialogUtil.showInfoDialog("提示", null, "添加成功");
            cbGoodsName.setValue(null);
            tfPrice.setText("");
            tfUnit.setText("");
            tfNum.setText("");
        });

        btReset = new Button("重置");
        btReset.setOnAction(e -> {
            cbGoodsName.setValue(null);
            tfPrice.setText("");
            tfUnit.setText("");
            tfNum.setText("");
            lbRepertory.setText("");
        });
        gp.add(lbGoodsName, 0, 0);
        gp.add(cbGoodsName, 1, 0);
        gp.add(lbPrice, 0, 1);
        gp.add(tfPrice, 1, 1);
        gp.add(lbUnit, 2, 1);
        gp.add(tfUnit, 3, 1);
        gp.add(lbNum, 0, 2);
        gp.add(tfNum, 1, 2);
        gp.add(lbNuml, 2, 2);
        gp.add(lbRepertory, 3, 2);
        gp.add(btSubmit, 0, 3);
        gp.add(btReset, 1, 3);

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
