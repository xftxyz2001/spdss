package com.xftxyz.smms.view;

import java.util.Map;

import com.xftxyz.smms.utils.CodeUtil;
import com.xftxyz.smms.utils.FileUtil;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//登录界面
public class Register extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	public void setImage(ImageView imageView) {
		Map<String, Object> map = CodeUtil.get();
		// System.out.println(map.get("code"));
		imageView.setImage((Image) map.get("image"));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ImageView imageView = new ImageView();
		setImage(imageView);
		imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setImage(imageView);
			}
		});
		imageView.setLayoutX(220);
		imageView.setLayoutY(160);
		imageView.setFitWidth(80);
		imageView.setFitHeight(25);
		AnchorPane ap = new AnchorPane();// 网格布局类，作为根节点
		ap.setStyle("-fx-background-color:#FFFAF0");// 设置背景色
		Scene scene = new Scene(ap);// 设置场景
		Text title = new Text("用户登录");
		title.setFont(Font.font("FangSong", FontWeight.NORMAL, 20));
		title.setFill(Color.CYAN);
		Label Account = new Label("账号");
		Account.setFont(Font.font("FangSong", FontWeight.NORMAL, 15));
		Label Pwd = new Label("密码");
		Pwd.setFont(Font.font("FangSong", FontWeight.NORMAL, 15));
		Label Idf = new Label("验证码");
		Idf.setFont(Font.font("FangSong", FontWeight.NORMAL, 15));
		TextField Accounttextfield = new TextField();
		TextField identifytextfield = new TextField();
		Button rgsbutton = new Button("登录");
		rgsbutton.setFont(Font.font("FangSong", FontWeight.NORMAL, 15));
		rgsbutton.setStyle("-fx-background-color:#6495ED");
		rgsbutton.setPrefWidth(220);
		rgsbutton.setLayoutX(100);
		rgsbutton.setLayoutY(200);
		identifytextfield.setPrefWidth(60);
		PasswordField passwordfield = new PasswordField();
		ap.getChildren().addAll(title, Account, Pwd, Idf, Accounttextfield, identifytextfield, passwordfield, rgsbutton,
				imageView);
		title.setLayoutX(100);
		title.setLayoutY(80);
		Account.setLayoutX(110);
		Account.setLayoutY(100);
		Pwd.setLayoutX(110);
		Pwd.setLayoutY(130);
		Idf.setLayoutX(95);
		Idf.setLayoutY(165);
		Accounttextfield.setLayoutX(150);
		Accounttextfield.setLayoutY(100);
		identifytextfield.setLayoutX(150);
		identifytextfield.setLayoutY(160);
		passwordfield.setLayoutX(150);
		passwordfield.setLayoutY(130);
		primaryStage.setScene(scene);
		primaryStage.setHeight(350);
		primaryStage.setWidth(400);
		primaryStage.setTitle("用户登录");
		primaryStage.setResizable(false);
		// primaryStage.getIcons()
				// .add(new Image("file:/D:/sp111/spds/smms/src/main/java/com/xftxyz/smms/view/register.png"));
		primaryStage.getIcons().add(FileUtil.getImage("chaoshi.png"));
		primaryStage.show();

	}

}