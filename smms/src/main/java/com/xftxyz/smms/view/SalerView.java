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

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SalerView {

    // Service
    private GoodsService goodsService;
    private SaleService saleService;

    // UI控件
    Stage primaryStage;
    Scene scene;
    BorderPane bpRoot;
    HBox topBox;
    Label lbshopnameLabel;
    Label lbsalerLabel;
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
    Image submitImage;
    ImageView submitImageView;
    Image resetImage;
    ImageView resetImageView;
    Label statusBar;

    private void initService() {

        // Service初始化
        goodsService = ServiceFactory.getGoodsService();
        saleService = ServiceFactory.getSaleService();
    }

    public void initUI() {
    	lbshopnameLabel = new Label("200超市");
    	lbshopnameLabel.setTextFill(Paint.valueOf("#FFFFFF"));
    	lbshopnameLabel.setFont(Font.font("华文琥珀",20));
    	lbsalerLabel= new Label("销售平台：");
    	lbsalerLabel.setTextFill(Paint.valueOf("#FFFFFF"));
    	lbsalerLabel.setFont(Font.font("华文黑体",25));
    	topBox = new HBox();
    	topBox.setStyle("-fx-background-color:#1E90FF");
    	topBox.setSpacing(10);
    	topBox.setPadding(new Insets(10));
    	HBox.setMargin(lbshopnameLabel, new Insets(10));
    	HBox.setMargin(lbsalerLabel, new Insets(6));
        topBox.getChildren().addAll(lbshopnameLabel,lbsalerLabel);
        primaryStage = new Stage();
        bpRoot = new BorderPane();
        scene = new Scene(bpRoot,660,500);

        gp = new GridPane();
        lbGoodsName = new Label("商品名称：");
        lbGoodsName.setTextFill(Paint.valueOf("#0000FF"));
        lbGoodsName.setFont(Font.font("华文黑体", 15));
        cbGoodsName = new ChoiceBox<>();
        cbGoodsName.getItems().addAll(goodsService.getAllGoodsName());
        cbGoodsName.setPrefWidth(200);
        cbGoodsName.setOnAction(e -> {
            String goodsName = cbGoodsName.getValue();
            Goods goods = goodsService.getGoodsByName(goodsName);
            if (goods != null) {
                tfPrice.setText(goods.getPrice().toString());
                tfUnit.setText(goods.getUnit());
                lbRepertory.setText("库存："+goods.getNum().toString());

            }
        });
        lbPrice = new Label("单价：");
        lbPrice.setTextFill(Paint.valueOf("#0000FF"));
        lbPrice.setFont(Font.font("华文黑体", 15));
        tfPrice = new TextField();
        lbUnit = new Label("/");
        tfUnit = new TextField();
        lbNum = new Label("数量：");
        lbNum.setTextFill(Paint.valueOf("#0000FF"));
        lbNum.setFont(Font.font("华文黑体", 15));
        tfNum = new TextField();
        lbNuml = new Label("/");
        lbRepertory = new Label("");
        submitImage = FileUtil.getImage("submit.png");
        submitImageView = new ImageView(submitImage);
        submitImageView.setFitWidth(20);
        submitImageView.setFitHeight(20);
        btSubmit = new Button("提交",submitImageView);
        btSubmit.setContentDisplay(ContentDisplay.LEFT);
        btSubmit.setPrefWidth(120);
        
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
        resetImage = FileUtil.getImage("reset.png");
        resetImageView = new ImageView(resetImage);
        resetImageView.setFitWidth(20);
        resetImageView.setFitHeight(20);
        btReset = new Button("重置",resetImageView);
        btReset.setContentDisplay(ContentDisplay.LEFT);
        btReset.setPrefWidth(120);
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
        bpRoot.setTop(topBox);
        bpRoot.setCenter(gp);
        gp.setPadding(new Insets(30.0));
        gp.setVgap(20);
        gp.setHgap(20);
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
