package com.xftxyz.smms;

import com.xftxyz.smms.entity.Goods;
import com.xftxyz.smms.entity.User;
import com.xftxyz.smms.utils.FileUtil;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Buyer extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Button supperliermanage = new Button("供货商");
		supperliermanage.setLayoutX(0);
		supperliermanage.setLayoutY(150);
		supperliermanage.setPrefHeight(80);
		supperliermanage.setPrefWidth(200);
		AnchorPane ap = new AnchorPane();
		AnchorPane apleft = new AnchorPane();
		AnchorPane aprightsupperlier = new AnchorPane();
		AnchorPane aprightgoods = new AnchorPane();
		AnchorPane apsupperlier = new AnchorPane();

		AnchorPane apgoods = new AnchorPane();
		supperliermanage.setOnAction(e -> {
			apgoods.setVisible(false);
			apsupperlier.setVisible(true);
		});

		Button goodsmanage = new Button("商品");
		goodsmanage.setLayoutX(0);
		goodsmanage.setLayoutY(300);
		goodsmanage.setPrefHeight(80);
		goodsmanage.setPrefWidth(200);
		goodsmanage.setOnAction(e -> {
			apgoods.setVisible(true);
			apsupperlier.setVisible(false);
		});
		ap.getChildren().addAll(apleft, apsupperlier, apgoods);
		apleft.setPrefHeight(600);
		apleft.setPrefWidth(200);
		apleft.setStyle("-fx-background-color:#708090");
		apsupperlier.getChildren().add(aprightsupperlier);
		apgoods.getChildren().add(aprightgoods);
		apsupperlier.setTopAnchor(aprightsupperlier, 0.0);
		apsupperlier.setRightAnchor(aprightsupperlier, 0.0);
		apsupperlier.setLeftAnchor(aprightsupperlier, 0.0);
		apsupperlier.setBottomAnchor(aprightsupperlier, 500.0);
		apgoods.setTopAnchor(aprightgoods, 0.0);
		apgoods.setRightAnchor(aprightgoods, 0.0);
		apgoods.setLeftAnchor(aprightgoods, 0.0);
		apgoods.setBottomAnchor(aprightgoods, 500.0);
		ap.setTopAnchor(apsupperlier, 0.0);
		ap.setRightAnchor(apsupperlier, 0.0);
		ap.setLeftAnchor(apsupperlier, 200.0);
		ap.setBottomAnchor(apsupperlier, 0.0);
		ap.setTopAnchor(apgoods, 0.0);
		ap.setRightAnchor(apgoods, 0.0);
		ap.setLeftAnchor(apgoods, 200.0);
		ap.setBottomAnchor(apgoods, 0.0);
		aprightsupperlier.setStyle("-fx-background-color:#696969");
		aprightgoods.setStyle("-fx-background-color:#696969");
		apleft.getChildren().addAll(goodsmanage, supperliermanage);
		Scene scene = new Scene(ap);

		primaryStage.setScene(scene);
		primaryStage.setHeight(600);
		primaryStage.setWidth(800);
		primaryStage.setTitle("欢迎使用");
		primaryStage.setResizable(false);
		ObservableList<User> list = FXCollections.observableArrayList();
		Label usermagelabel = new Label("供应商");
		usermagelabel.setFont(Font.font("FangSong", FontWeight.BOLD, 20));
		usermagelabel.setTextFill(Color.BLACK);
		TableView<User> tableview = new TableView<User>(list);
		TableColumn<User, String> supplier_name = new TableColumn<User, String>("供应商");
		TableColumn<User, String> supplier_id = new TableColumn<User, String>("id");
		TableColumn<User, String> supperlier_adress = new TableColumn<User, String>("地址");
		TableColumn<User, String> supplier_iphone = new TableColumn<User, String>("电话");
		tableview.getColumns().addAll(supplier_id, supplier_name, supperlier_adress, supplier_iphone);

		usermagelabel.setLayoutX(230);
		usermagelabel.setLayoutY(30);
		tableview.setLayoutX(100);
		tableview.setLayoutY(100);

		aprightsupperlier.getChildren().add(usermagelabel);
		aprightsupperlier.getChildren().add(tableview);
		ObservableList<Goods> list1 = FXCollections.observableArrayList();
		Label goodsmageLabel = new Label("商品");
		goodsmageLabel.setFont(Font.font("FangSong", FontWeight.BOLD, 20));
		goodsmageLabel.setTextFill(Color.BLACK);
		TableView<Goods> tableview1 = new TableView<Goods>(list1);
		TableColumn<Goods, String> goods_name = new TableColumn<Goods, String>("商品名");
		TableColumn<Goods, String> goods_id = new TableColumn<Goods, String>("id");
		TableColumn<Goods, String> goods_price = new TableColumn<Goods, String>("价格");
		TableColumn<Goods, String> goods_category = new TableColumn<Goods, String>("分类");
		TableColumn<Goods, String> goods_number = new TableColumn<Goods, String>("数量");
		TableColumn<Goods, String> goods_unit = new TableColumn<Goods, String>("单位");
		TableColumn<Goods, String> goods_onsell = new TableColumn<Goods, String>("在售");

		tableview1.getColumns().addAll(goods_category, goods_id, goods_name, goods_number, goods_onsell, goods_price,
				goods_unit);

		goodsmageLabel.setLayoutX(230);
		goodsmageLabel.setLayoutY(30);
		tableview1.setLayoutX(10);
		tableview1.setLayoutY(100);

		aprightgoods.getChildren().add(goodsmageLabel);
		aprightgoods.getChildren().add(tableview1);
		Button importbt = new Button("进货");
		Button remainbt = new Button("查看库存");
		importbt.setLayoutX(100);
		importbt.setLayoutY(520);
		remainbt.setLayoutX(300);
		remainbt.setLayoutY(520);
		aprightgoods.getChildren().addAll(importbt, remainbt);
		apgoods.setVisible(false);
		primaryStage.show();
		primaryStage.getIcons().add(FileUtil.getImage("user.png"));
	}
}
