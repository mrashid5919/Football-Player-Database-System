package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        showLoginPage();
    }


    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.init();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showHomePage(String userName) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/home.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomeController controller = loader.getController();
        controller.init(userName);
        controller.load();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showCountryPlayerCount(String club,List<Player> playerList) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/countryplayercount.fxml"));
        Parent root = loader.load();

        // Loading the controller
        CountryPlayerController controller = loader.getController();
        controller.init(club,playerList);
        controller.load();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Country Player Count");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }
}
