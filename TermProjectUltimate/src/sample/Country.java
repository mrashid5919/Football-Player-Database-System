package sample;

import java.io.Serializable;

public class Country implements Serializable {
    private String name;
    private int playerCount;

    public Country(String name,int playerCount){
        this.name =name;
        this.playerCount=playerCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
}
