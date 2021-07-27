package server;

import sample.FileOperations;
import sample.Player;
import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    public HashMap<String, String> userMap;

    public static void setPlayerList(List<Player> playerList) {
        Server.playerList = playerList;
    }

    public static void setMarketList(List<Player> marketList) {
        Server.marketList = marketList;
    }

    public static List<Player> playerList;
    public static List<Player> marketList;
    public static List<NetworkUtil> clientList;

    Server() throws Exception{
        playerList=new ArrayList<>();
        marketList=new ArrayList<>();
        clientList=new ArrayList<>();
        playerList= FileOperations.readFromFile();
        userMap = new HashMap<>();
        for(Player p:playerList){
            boolean c=false;
            for(String sr:userMap.keySet()){
                if(sr.equalsIgnoreCase(p.getClub())){
                    c=true;
                    break;
                }
            }
            if(!c){
                userMap.put(p.getClub(),"123");
            }
        }

        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        clientList.add(networkUtil);
        new ReadThreadServer(userMap, networkUtil,clientList);
    }

    public static List<Player> getPlayerList() {
        return playerList;
    }

    public static List<Player> getMarketList(){
        return marketList;
    }

    public static void main(String[] args) throws Exception {
        new Server();
    }
}
