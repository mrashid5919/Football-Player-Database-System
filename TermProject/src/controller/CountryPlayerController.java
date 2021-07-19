package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class CountryPlayerController {

    private static List<Country> CountryList=new ArrayList<>();
    private String club;
    private Main main;

    public void init(String club,List<Player> playerList){
        this.club=club;
        FrequentMethods.hover(back);
        for(Player p:playerList){
            boolean a=false;
            for(Country c:CountryList){
                if(p.getCountry().equalsIgnoreCase(c.getName())){
                    a=true;
                    c.setPlayerCount(c.getPlayerCount()+1);
                }
            }
            if(!a){
                Country c=new Country(p.getCountry(),1);
                CountryList.add(c);
            }
        }
    }

    @FXML
    private TableView<Country> tableView;

    @FXML
    private TableColumn<Country,String> NameCol;

    @FXML
    private TableColumn<Country, Integer> CountCol;

    @FXML
    private Button back;

    @FXML
    void Back(ActionEvent event){
        try{
            main.showHomePage(club);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void setMain(Main main) {
        this.main = main;
    }

    ObservableList<Country> data;

    private boolean init = true;

    private void initializeColumns() {
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        CountCol.setCellValueFactory(new PropertyValueFactory<>("playerCount"));
    }

    public void load() {
        if (init) {
            initializeColumns();
            init = false;
        }

        data = FXCollections.observableArrayList(CountryList);

        tableView.setEditable(true);
        tableView.setItems(data);
    }
}
