package controller;

import DTO.BuyPlayer;
import DTO.SellPlayer;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeController {
    private Main main;
    private String club;

    public static List<Player> ClubPlayerList = new ArrayList<>();
    public static List<Player> ClubMarketList = new ArrayList<>();

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
        FrequentMethods.hover(SellPlayer);
        FrequentMethods.hover(PlayerMarket);
        FrequentMethods.hover(BuyPlayer);
        FrequentMethods.hover(Refresh);
        FrequentMethods.hover(PlayerInfo);
        intro.setVisible(false);
        intro1.setVisible(false);
        newField.setVisible(false);
        newField1.setVisible(false);
        confirm.setVisible(false);
        back.setVisible(false);
        Refresh.setVisible(false);
        BuyPlayer.setVisible(false);
        //ClubPlayerList.clear();
        ClubPlayerList=Main.getPlayerList();
        /*for(Player p:ClubPlayerList) {
            System.out.println(p);
        }*/
    }

    @FXML
    void logoutAction(ActionEvent event) {
        try {
            main.showLoginPage();
            ClubPlayerList.clear();
            Main.setPlayerList(ClubPlayerList);
            ClubMarketList.clear();
            Main.setMarketList(ClubMarketList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.exit(0);
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
    private Button PlayerInfo;

    @FXML
    void playerInfo(ActionEvent event){
        Player p= tableView.getSelectionModel().getSelectedItem();
        if(p==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error!");
            alert.setContentText("You have to select a player first");
            alert.showAndWait();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Player Info");
            alert.setContentText(p.toString());
            alert.showAndWait();
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
                    if(Searched.isEmpty()){
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error!");
                        alert.setContentText("There is no player with this name");
                        alert.showAndWait();
                    }
                    else {
                        data = FXCollections.observableArrayList(Searched);

                        tableView.setEditable(true);
                        tableView.setItems(data);
                    }
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
            if(Searched.isEmpty()){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error!");
                alert.setContentText("There is no player of this country");
                alert.showAndWait();
            }
            else {
                data = FXCollections.observableArrayList(Searched);

                tableView.setEditable(true);
                tableView.setItems(data);
            }
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
            if(Searched.isEmpty()){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error!");
                alert.setContentText("There is no player with this position");
                alert.showAndWait();
            }
            else {
                data = FXCollections.observableArrayList(Searched);

                tableView.setEditable(true);
                tableView.setItems(data);
            }
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
                        if(Searched.isEmpty()){
                            Alert alert=new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Error!");
                            alert.setContentText("There is no player within this salary range");
                            alert.showAndWait();
                        }
                        else {
                            data = FXCollections.observableArrayList(Searched);

                            tableView.setEditable(true);
                            tableView.setItems(data);
                        }
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
        String s="Total salary = " + String.format("%.2f",total);
        alert.setContentText(s);
        alert.showAndWait();
    }

    @FXML
    private Button SellPlayer;

    @FXML
    void sellPlayer(ActionEvent event){
        Player p= tableView.getSelectionModel().getSelectedItem();
        if(p==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error!");
            alert.setContentText("You have to select a player first");
            alert.showAndWait();
        }
        new Thread(()->{
            try {
                ClubPlayerList.remove(p);
                DTO.SellPlayer sellPlayer=new SellPlayer();
                sellPlayer.setPlayer(p);
                sellPlayer.setName(club);
                sellPlayer.setPlayerList(ClubPlayerList);
                main.getNetworkUtil().write(sellPlayer);

            } catch (IOException e) {
                e.printStackTrace();
            }
            data = FXCollections.observableArrayList(ClubPlayerList);

            tableView.setEditable(true);
            tableView.setItems(data);
        }).start();
    }

    @FXML
    private Button PlayerMarket;

    @FXML
    void playerMarket(ActionEvent event){
        back.setVisible(true);
        Refresh.setVisible(true);
        BuyPlayer.setVisible(true);
        PlayerMarket.setVisible(false);
        SellPlayer.setVisible(false);
        //new Thread(()->{
            ClubMarketList=Main.getMarketList();
        //}).start();

        data = FXCollections.observableArrayList(ClubMarketList);

        tableView.setEditable(true);
        tableView.setItems(data);
    }

    @FXML
    private Button BuyPlayer;

    @FXML
    void buyPlayer(ActionEvent event){
        Player p= tableView.getSelectionModel().getSelectedItem();
        if(p==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error!");
            alert.setContentText("You have to select a player first");
            alert.showAndWait();
        }
        else{
            boolean b=false;
            for(Player pl: Main.getMarketList()){
                if(pl.getName().equalsIgnoreCase(p.getName())){
                    b=true;
                    break;
                }
            }
            if(!b){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error!");
                alert.setContentText("Player already sold. Please refresh");
                alert.showAndWait();
            }
            else{
                new Thread(()->{
                    try {
                        p.setClub(club);
                        ClubMarketList.remove(p);
                        ClubPlayerList.add(p);
                        BuyPlayer buyPlayer=new BuyPlayer();
                        buyPlayer.setPlayer(p);
                        buyPlayer.setName(club);
                        buyPlayer.setPlayerList(ClubPlayerList);
                        main.getNetworkUtil().write(buyPlayer);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    data = FXCollections.observableArrayList(ClubMarketList);

                    tableView.setEditable(true);
                    tableView.setItems(data);
                }).start();
            }

        }
    }

    @FXML
    private Button Refresh;

    @FXML
    void refresh(){
        new Thread(()->{
            ClubMarketList=Main.getMarketList();
            data = FXCollections.observableArrayList(ClubMarketList);

            tableView.setEditable(true);
            tableView.setItems(data);
        }).start();
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

