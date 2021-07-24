package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import sample.FrequentMethods;
import sample.Player;
import sample.Search;

import java.util.ArrayList;
import java.util.List;

public class HomeController {
    private Main main;
    private String club;

    public static List<Player> ClubPlayerList = new ArrayList<>();

    @FXML
    private Label message;

    @FXML
    private Button logOutButton;

    @FXML
    private TableView<Player> tableView;

    @FXML
    private TableColumn<Player,String> NameCol;

    @FXML
    private TableColumn<Player, String> CountryCol;

    @FXML
    private TableColumn<Player, Integer> AgeCol;

    @FXML
    private TableColumn<Player, Double> HeightCol;

    @FXML
    private TableColumn<Player, String> ClubCol;

    @FXML
    private TableColumn<Player, String> PositionCol;

    @FXML
    private TableColumn<Player, Integer> NumberCol;

    @FXML
    private TableColumn<Player, Double> SalaryCol;

    @FXML
    private Text intro;

    @FXML
    private Text intro1;

    @FXML
    private TextField newField;

    @FXML
    private TextField newField1;

    @FXML
    private Button confirm;

    @FXML
    private Button back;


    public void init(String msg) {
        club=msg;
        message.setText("Welcome, "+msg);
        FrequentMethods.hover(logOutButton);
        FrequentMethods.hover(nameSearch);
        FrequentMethods.hover(confirm);
        FrequentMethods.hover(back);
        FrequentMethods.hover(countrySearch);
        FrequentMethods.hover(positionSearch);
        FrequentMethods.hover(salarySearch);
        FrequentMethods.hover(countryPlayerCount);
        FrequentMethods.hover(maxSalary);
        FrequentMethods.hover(maxAge);
        FrequentMethods.hover(maxHeight);
        FrequentMethods.hover(totalSalary);
        intro.setVisible(false);
        intro1.setVisible(false);
        newField.setVisible(false);
        newField1.setVisible(false);
        confirm.setVisible(false);
        back.setVisible(false);
        //ClubPlayerList.clear();
        ClubPlayerList=Main.getPlayerList();
        for(Player p:ClubPlayerList) {
            System.out.println(p);
        }
    }

    @FXML
    void logoutAction(ActionEvent event) {
        try {
            main.showLoginPage();
            ClubPlayerList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Back(ActionEvent event){
        try {
            main.showHomePage(club);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private Button nameSearch;

    @FXML
    void NameSearch(ActionEvent event) {
        intro.setText("Enter Name:");
        visible1();
        confirm.setOnAction(e->{
                    List<Player>Searched=new ArrayList<>();
                    Searched= Search.SearchPlayerName(newField.getText(),ClubPlayerList);
                    data = FXCollections.observableArrayList(Searched);

                    tableView.setEditable(true);
                    tableView.setItems(data);
                }
        );
    }

    @FXML
    private Button countrySearch;

    @FXML
    void CountrySearch(ActionEvent event) {
        intro.setText("Enter Country:");
        visible1();
        confirm.setOnAction(e->{
                    List<Player>Searched=new ArrayList<>();
                    Searched= Search.SearchCountryName(newField.getText(),ClubPlayerList);
                    data = FXCollections.observableArrayList(Searched);

                    tableView.setEditable(true);
                    tableView.setItems(data);
                }
        );
    }

    @FXML
    private Button positionSearch;

    @FXML
    void PositionSearch(ActionEvent event) {
        intro.setText("Enter Position:");
        visible1();
        confirm.setOnAction(e->{
                    List<Player>Searched=new ArrayList<>();
                    Searched= Search.SearchPosition(newField.getText(),ClubPlayerList);
                    data = FXCollections.observableArrayList(Searched);

                    tableView.setEditable(true);
                    tableView.setItems(data);
                }
        );
    }

    @FXML
    private Button salarySearch;

    @FXML
    void SalarySearch(ActionEvent event){
        intro.setText("Enter Minimum:");
        intro1.setText("Enter Maximum:");
        visible2();
        confirm.setOnAction(e->{
                    List<Player>Searched=new ArrayList<>();
                    try{
                        double x=Double.parseDouble(newField.getText());
                        double y=Double.parseDouble(newField1.getText());
                        Searched= Search.SearchSalary(x,y,ClubPlayerList);
                        data = FXCollections.observableArrayList(Searched);

                        tableView.setEditable(true);
                        tableView.setItems(data);
                    }catch (Exception ex){
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Invalid input");
                        alert.setContentText("Enter double values only");
                        alert.showAndWait();
                    }
                }
        );
    }

    @FXML
    private Button countryPlayerCount;

    @FXML
    void CountryPlayerCount(ActionEvent event){
        try {
            main.showCountryPlayerCount(club,ClubPlayerList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private Button maxSalary;

    @FXML
    void MaxSalary(ActionEvent event){
        back.setVisible(true);
        List<Player>Searched=new ArrayList<>();
        Searched= Search.MaxSalary(ClubPlayerList);
        data = FXCollections.observableArrayList(Searched);

        tableView.setEditable(true);
        tableView.setItems(data);
    }

    @FXML
    private Button maxAge;

    @FXML
    void MaxAge(ActionEvent event){
        back.setVisible(true);
        List<Player>Searched=new ArrayList<>();
        Searched= Search.MaxAge(ClubPlayerList);
        data = FXCollections.observableArrayList(Searched);

        tableView.setEditable(true);
        tableView.setItems(data);
    }

    @FXML
    private Button maxHeight;

    @FXML
    void MaxHeight(ActionEvent event){
        back.setVisible(true);
        List<Player>Searched=new ArrayList<>();
        Searched= Search.MaxHeight(ClubPlayerList);
        data = FXCollections.observableArrayList(Searched);

        tableView.setEditable(true);
        tableView.setItems(data);
    }

    @FXML
    private Button totalSalary;

    @FXML
    void TotalSalary(ActionEvent event){
        back.setVisible(true);
        double total=Search.TotalSalary(ClubPlayerList);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Yearly Salary");
        alert.setContentText("Total yearly salary of "+club+": "+total);
        alert.showAndWait();
    }


    void setMain(Main main) {
        this.main = main;
    }

    ObservableList<Player> data;

    private boolean init = true;

    private void initializeColumns() {
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        CountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));

        AgeCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        HeightCol.setCellValueFactory(new PropertyValueFactory<>("height"));

        ClubCol.setCellValueFactory(new PropertyValueFactory<>("club"));

        PositionCol.setCellValueFactory(new PropertyValueFactory<>("position"));

        NumberCol.setCellValueFactory(new PropertyValueFactory<>("number"));

        SalaryCol.setCellValueFactory(new PropertyValueFactory<>("weeklySalary"));

        //buttonCol.setCellValueFactory(new PropertyValueFactory<>("button"));
    }

    public void load() {
        if (init) {
            initializeColumns();
            init = false;
        }

        data = FXCollections.observableArrayList(ClubPlayerList);

        tableView.setEditable(true);
        tableView.setItems(data);
    }



    private void visible1(){
        intro.setVisible(true);
        newField.setVisible(true);
        confirm.setVisible(true);
        back.setVisible(true);
    }

    private void visible2(){
        visible1();
        intro1.setVisible(true);
        newField1.setVisible(true);
    }
}
