package controller;

import DTO.GetPlayer;
import sample.FileOperations;
import sample.FrequentMethods;
import sample.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import util.LoginDTO;
import util.NetworkUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    private Main main;
    private NetworkUtil networkUtil;

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;

    @FXML
    private ImageView image;

    @FXML
    private Line Line1;

    @FXML
    private Line Line2;

    public void init(){
        Image img = new Image(Main.class.getResourceAsStream("/view/Footballer.png"));
        image.setImage(img);
        FrequentMethods.hover(loginButton);
        FrequentMethods.hover(resetButton);
    }

    @FXML
    void loginAction(ActionEvent event) {
        new Thread(()->{
            String userName = userText.getText();
            userName=FrequentMethods.capitalizeWord(userName);
            String password = passwordText.getText();
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setUserName(userName);
            loginDTO.setPassword(password);
            try {
                main.getNetworkUtil().write(loginDTO);
                GetPlayer getPlayer=new GetPlayer();
                getPlayer.setName(userName);
                main.getNetworkUtil().write(getPlayer);
                //main.getNetworkUtil().write(loginDTO);
            } catch (IOException e) {
                e.printStackTrace();
            }}).start();
    }

    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);
        passwordText.setText(null);
    }

    void setMain(Main main) {
        this.main = main;
    }
}
