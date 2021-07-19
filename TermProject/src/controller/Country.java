package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Country {
    private SimpleStringProperty name;
    private SimpleIntegerProperty playerCount;

    public Country(String name,int playerCount){
        this.name = new SimpleStringProperty(name);
        this.playerCount=new SimpleIntegerProperty(playerCount);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getPlayerCount() {
        return playerCount.get();
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount.set(playerCount);
    }
}
