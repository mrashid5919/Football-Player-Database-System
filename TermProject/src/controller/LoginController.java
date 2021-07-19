package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class LoginController {
    private Main main;
    private static List<Player> playerList;
    private static List<String> ClubList = new ArrayList();
    private boolean a;
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: #6bd0ff;";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: #3fa0ef; -fx-text-fill: #ffffff;";

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private ImageView image;

    @FXML
    private Line Line1;

    @FXML
    private Line Line2;

    @FXML
    private Button loginButton;

    public void init() throws Exception {
        Image img = new Image(Main.class.getResourceAsStream("/view/Footballer.png"));
        image.setImage(img);
        loginButton.setStyle(IDLE_BUTTON_STYLE);
        loginButton.setOnMouseEntered(e -> loginButton.setStyle(HOVERED_BUTTON_STYLE));
        loginButton.setOnMouseExited(e -> loginButton.setStyle(IDLE_BUTTON_STYLE));
        playerList=FileOperations.readFromFile();
        for(Player p: playerList){
            a=false;
            for(String s: ClubList){
                if(p.getClub().equalsIgnoreCase(s)){
                    a=true;
                    break;
                }
            }
            if(!a){
                ClubList.add(p.getClub());
            }
        }
    }

    @FXML
    void loginAction(ActionEvent event){
        a=false;
        String userName=userText.getText();
        String password=passwordText.getText();
        for(String s: ClubList){
            if (userName.equalsIgnoreCase(s) && password.equals("123")) {
                a=true;
                try {
                    main.showHomePage(userName);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        if(!a)
            main.showAlert();
    }


    void setMain(Main main) {
        this.main = main;
    }
}
