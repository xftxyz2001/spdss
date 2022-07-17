package com.xftxyz.smms.view;

import com.xftxyz.smms.service.GoodsService;
import com.xftxyz.smms.service.ServiceFactory;
import com.xftxyz.smms.service.UserService;
import com.xftxyz.smms.type.MyValues;

import javafx.geometry.Pos;
import javafx.print.PrintColor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SalerView {
	
	//Service
	private GoodsService goodsService;
	private UserService userService;
	//UI控件
		 Stage primaryStage;
		 Scene scene;
		 BorderPane bpRoot;
		 AnchorPane apTop;
		 HBox hbMidBox;
		 Text topText;
		 Button btsubmit;
		 TextField goodsnumText;
		 Text statusText;
		 ChoiceBox<String> goodsnameBox;
		 ChoiceBox<String> unitBox;
    private void initService() {
    	
	        // Service初始化
	        goodsService = ServiceFactory.getGoodsService();
	        userService = ServiceFactory.getUserService();
	    }
    public  void initUI() {
        // UI控件初始化
        primaryStage = new Stage();
        bpRoot = new BorderPane();
        bpRoot.setPrefHeight(450);
        bpRoot.setPrefWidth(400);
        apTop = new AnchorPane();
        topText = new Text("销售平台：");
        topText.setFont(Font.font("FangSong",FontWeight.NORMAL,25));
        AnchorPane.setTopAnchor(topText,23.0);
        btsubmit = new Button();
        
        statusText = new Text("欢迎使用XX超市管理系统，当前用户" + userService.getCurrentUserName() + "（"
                + userService.getCurrentUserRoleName() + "）");
        apTop.setStyle("-fx-background-color:#00FFFF");
        apTop.setPrefHeight(80);
        apTop.getChildren().add(topText);
        bpRoot.setTop(apTop);
      
        bpRoot.setBottom(statusText);
        goodsnameBox = new ChoiceBox<>();
        goodsnameBox.getItems().addAll(goodsService.getAllGoodsName());
        unitBox= new ChoiceBox<>();
        unitBox.getItems().addAll("kg","件");
        hbMidBox = new HBox();
        goodsnumText= new TextField();
        goodsnumText.setPrefWidth(70);
        hbMidBox.getChildren().addAll(goodsnameBox,goodsnumText,unitBox);
        hbMidBox.seth
        hbMidBox.setAlignment(Pos.CENTER);
        

        
        scene = new Scene(bpRoot);
        primaryStage.setScene(scene);
       }
    public void start() throws Exception {
    	 initService();
    	initUI();
    	primaryStage.show();
    	
    }
   
}
