package DTO;

import sample.Player;

import java.io.Serializable;
import java.util.List;

public class SellPlayerResponse implements Serializable {
    private List<Player> playerList;
    private List<Player> marketList;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public List<Player> getMarketList() { return marketList; }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public void setMarketList(List<Player> marketList) {
        this.marketList = marketList;
    }
}
