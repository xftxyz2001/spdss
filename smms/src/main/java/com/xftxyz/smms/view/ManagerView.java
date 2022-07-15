package com.xftxyz.smms.view;

import com.xftxyz.smms.entity.Goods;
import com.xftxyz.smms.entity.User;
import com.xftxyz.smms.service.GoodsService;
import com.xftxyz.smms.service.ServiceFactory;
import com.xftxyz.smms.service.UserService;
import com.xftxyz.smms.type.Limits;
import com.xftxyz.smms.utils.FileUtil;
import com.xftxyz.smms.utils.RandomUtil;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ManagerView {
	@SuppressWarnings("unused")
	private GoodsService goodsService;
	private UserService userService;

	public void initialize() {
		userService = ServiceFactory.getUserService();
	}

	public void start() throws Exception {
		initialize();

		Stage primaryStage = new Stage();
		Button usermanage = new Button("用户管理");
		usermanage.setLayoutX(0);
		usermanage.setLayoutY(150);
		usermanage.setPrefHeight(80);
		usermanage.setPrefWidth(200);
		AnchorPane ap = new AnchorPane();
		AnchorPane apleft = new AnchorPane();
		AnchorPane aprightuser = new AnchorPane();
		AnchorPane aprightgoods = new AnchorPane();
		AnchorPane apuser = new AnchorPane();

		AnchorPane apgoods = new AnchorPane();
		usermanage.setOnAction(e -> {
			apgoods.setVisible(false);
			apuser.setVisible(true);
		});

		Button goodsmanage = new Button("商品管理");
		goodsmanage.setLayoutX(0);
		goodsmanage.setLayoutY(300);
		goodsmanage.setPrefHeight(80);
		goodsmanage.setPrefWidth(200);
		goodsmanage.setOnAction(e -> {
			apgoods.setVisible(true);
			apuser.setVisible(false);
		});
		ap.getChildren().addAll(apleft, apuser, apgoods);
		apleft.setPrefHeight(600);
		apleft.setPrefWidth(200);
		apleft.setStyle("-fx-background-color:#708090");
		apuser.getChildren().add(aprightuser);
		apgoods.getChildren().add(aprightgoods);

		AnchorPane.setTopAnchor(aprightuser, 0.0);
		AnchorPane.setRightAnchor(aprightuser, 0.0);
		AnchorPane.setLeftAnchor(aprightuser, 0.0);
		AnchorPane.setBottomAnchor(aprightuser, 500.0);
		AnchorPane.setTopAnchor(aprightgoods, 0.0);
		AnchorPane.setRightAnchor(aprightgoods, 0.0);
		AnchorPane.setLeftAnchor(aprightgoods, 0.0);
		AnchorPane.setBottomAnchor(aprightgoods, 500.0);
		AnchorPane.setTopAnchor(apuser, 0.0);
		AnchorPane.setRightAnchor(apuser, 0.0);
		AnchorPane.setLeftAnchor(apuser, 200.0);
		AnchorPane.setBottomAnchor(apuser, 0.0);
		AnchorPane.setTopAnchor(apgoods, 0.0);
		AnchorPane.setRightAnchor(apgoods, 0.0);
		AnchorPane.setLeftAnchor(apgoods, 200.0);
		AnchorPane.setBottomAnchor(apgoods, 0.0);
		aprightuser.setStyle("-fx-background-color:#696969");
		aprightgoods.setStyle("-fx-background-color:#696969");
		apleft.getChildren().addAll(goodsmanage, usermanage);
		Scene scene = new Scene(ap);

		primaryStage.setScene(scene);
		primaryStage.setHeight(600);
		primaryStage.setWidth(800);
		primaryStage.setTitle("欢迎使用");
		primaryStage.setResizable(false);
		Label usermagelabel = new Label("用户管理");
		usermagelabel.setFont(Font.font("FangSong", FontWeight.BOLD, 20));
		usermagelabel.setTextFill(Color.BLACK);
		TableView<User> tableview = new TableView<User>(userService.getUserObservableList());
		TableColumn<User, String> user_name = new TableColumn<User, String>("姓名");
		// TableColumn<User, String> user_id = new TableColumn<User, String>("id");
		TableColumn<User, String> user_pwd = new TableColumn<User, String>("密码");
		TableColumn<User, String> user_time = new TableColumn<User, String>("创建时间");
		TableColumn<User, String> user_limit = new TableColumn<User, String>("权限");
		user_pwd.setEditable(true);
		// tableview.;
		// tableview.getColumns().add(user_id);
		tableview.getColumns().add(user_name);
		tableview.getColumns().add(user_pwd); // 这里密码是不是不要显示比较好？
		tableview.getColumns().add(user_limit);
		tableview.getColumns().add(user_time);

		usermagelabel.setLayoutX(230);
		usermagelabel.setLayoutY(30);
		tableview.setLayoutX(100);
		tableview.setLayoutY(100);
		

		aprightuser.getChildren().add(usermagelabel);
		aprightuser.getChildren().add(tableview);
		Button createbt = new Button("创建用户");
		Button deletebt = new Button("删除用户");
		Button changebt = new Button("修改信息");
		createbt.setLayoutX(450);
		createbt.setLayoutY(140);
		deletebt.setLayoutX(450);
		deletebt.setLayoutY(220);
		changebt.setLayoutX(450);
		changebt.setLayoutY(300);
		// BorderPane
		// HBox
		// VBox
		aprightuser.getChildren().addAll(createbt, deletebt, changebt);
		ObservableList<Goods> list1 = FXCollections.observableArrayList();
		user_name.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<User, String> param) {
						return new SimpleStringProperty(param.getValue().getName());
					}
				});
		// user_id.setCellValueFactory(
		// 		new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
		// 			@Override
		// 			public ObservableValue<String> call(CellDataFeatures<User, String> param) {
		// 				return new SimpleStringProperty(String.valueOf(param.getValue().getId()));
		// 			}
		// 		});
		user_pwd.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<User, String> param) {
						return new SimpleStringProperty(param.getValue().getPwd());
					}
				});
		user_time.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<User, String> param) {
						return new SimpleStringProperty(param.getValue().getCreateAt().toString());
					}
				});
		user_limit.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<User, String> param) {
						return new SimpleStringProperty(userService.getUserRoleName(param.getValue()));
					}
				});

		createbt.setOnAction(e -> {

			String name = RandomUtil.choice("123456789", 2);
			String pwd = RandomUtil.choice("123456789", 2);
			Limits[] ll = Limits.values();
			Limits l = ll[RandomUtil.getInt(0, ll.length - 1)];
			String limits = l.name();
			User user = new User(name, pwd, limits);
			userService.addUser(user);
		});

		deletebt.setOnAction(e -> {
			User selectedUser = tableview.getSelectionModel().getSelectedItem();
			if (selectedUser == null) {
				System.out.println("请选择一个用户");
			}
			userService.deleteUser(selectedUser);
		});

		Label goodsmageLabel = new Label("商品管理");
		goodsmageLabel.setFont(Font.font("FangSong", FontWeight.BOLD, 20));
		goodsmageLabel.setTextFill(Color.BLACK);

		TableView<Goods> tableview1 = new TableView<Goods>(list1);
		TableColumn<Goods, String> goods_id = new TableColumn<Goods, String>("id"); // 感觉不用显示出来
		TableColumn<Goods, String> goods_name = new TableColumn<Goods, String>("商品名");
		TableColumn<Goods, String> goods_price = new TableColumn<Goods, String>("价格");
		TableColumn<Goods, String> goods_category = new TableColumn<Goods, String>("分类");
		TableColumn<Goods, String> goods_number = new TableColumn<Goods, String>("数量");
		TableColumn<Goods, String> goods_unit = new TableColumn<Goods, String>("单位");
		TableColumn<Goods, String> goods_onsell = new TableColumn<Goods, String>("在售");

		tableview1.getColumns().add(goods_id);
		tableview1.getColumns().add(goods_name);
		tableview1.getColumns().add(goods_price);
		tableview1.getColumns().add(goods_category);
		tableview1.getColumns().add(goods_number);
		tableview1.getColumns().add(goods_unit);
		tableview1.getColumns().add(goods_onsell);
		goodsmageLabel.setLayoutX(230);
		goodsmageLabel.setLayoutY(30);
		tableview1.setLayoutX(10);
		tableview1.setLayoutY(100);
	

		aprightgoods.getChildren().add(goodsmageLabel);
		aprightgoods.getChildren().add(tableview1);
		Button importbt = new Button("进货表");
		Button remainbt = new Button("库存");
		Button sellbt = new Button("销售表");
		Button derivebt = new Button("导出");
		importbt.setLayoutX(60);
		importbt.setLayoutY(520);
		remainbt.setLayoutX(170);
		remainbt.setLayoutY(520);
		sellbt.setLayoutX(280);
		sellbt.setLayoutY(520);
		derivebt.setLayoutX(390);
		derivebt.setLayoutY(520);
		aprightgoods.getChildren().addAll(importbt, remainbt, sellbt, derivebt);
		apgoods.setVisible(false);
		primaryStage.show();
		primaryStage.getIcons().add(FileUtil.getImage("user.png"));

	}

}
