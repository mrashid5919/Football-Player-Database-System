package DTO;

import sample.Player;

import java.io.Serializable;
import java.util.List;

public class GetPlayerResponse implements Serializable {
    private List<Player> playerList;

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
