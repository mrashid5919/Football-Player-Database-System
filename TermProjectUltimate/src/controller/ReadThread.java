package controller;

import DTO.BuyPlayerResponse;
import DTO.GetPlayerResponse;
import DTO.SellPlayerResponse;
import javafx.application.Platform;
import sample.Player;
import util.LoginDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadThread implements Runnable{
    private final Thread thr;
    private final Main main;
    public static List<Player> cl=new ArrayList<>();
    public static List<Player> ml=new ArrayList<>();

    public ReadThread(Main main) {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        System.out.println(loginDTO.getUserName());
                        System.out.println(loginDTO.isStatus());
                        // the following is necessary to update JavaFX UI components from user created threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loginDTO.isStatus()) {
                                    try {
                                        main.showHomePage(loginDTO.getUserName());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    main.showAlert();
                                }

                            }
                        });
                    }
                    if(o instanceof GetPlayerResponse){
                        new Thread(()->{
                            GetPlayerResponse obj=(GetPlayerResponse) o;
                            cl=obj.getPlayerList();
                            ml=obj.getMarketList();
                            Main.setPlayerList(cl);
                            Main.setMarketList(ml);
                        }).start();
                    }

                    if(o instanceof SellPlayerResponse){
                        new Thread(()->{
                            SellPlayerResponse obj=(SellPlayerResponse) o;
                            cl=obj.getPlayerList();
                            ml=obj.getMarketList();
                            String s="";
                            String c="";
                            for(Player p: cl){
                                s=p.getClub();
                            }
                            for(Player p:Main.getPlayerList())
                            {
                                c= p.getClub();
                            }
                            List<Player> ClubMarketList=new ArrayList<>();
                            for(Player p:ml){
                                if(!(p.getClub().equalsIgnoreCase(c))){
                                    ClubMarketList.add(p);
                                }
                            }
                            if(s.equalsIgnoreCase(c))
                                Main.setPlayerList(cl);
                            Main.setMarketList(ClubMarketList);
                        }).start();
                    }

                    if(o instanceof BuyPlayerResponse){
                        new Thread(()->{
                            BuyPlayerResponse obj=(BuyPlayerResponse) o;
                            cl=obj.getPlayerList();
                            ml=obj.getMarketList();
                            String s="";
                            String c="";
                            for(Player p: cl){
                                s=p.getClub();
                            }
                            for(Player p:Main.getPlayerList())
                            {
                                c= p.getClub();
                            }
                            List<Player> ClubMarketList=new ArrayList<>();
                            for(Player p:ml){
                                if(!(p.getClub().equalsIgnoreCase(c))){
                                    ClubMarketList.add(p);
                                }
                            }
                            if(s.equalsIgnoreCase(c))
                                Main.setPlayerList(cl);
                            Main.setMarketList(ClubMarketList);
                        }).start();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                main.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
