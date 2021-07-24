package server;

import DTO.GetPlayer;
import DTO.GetPlayerResponse;
import sample.Player;
import util.LoginDTO;
import util.NetworkUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadThreadServer implements Runnable{
    private final Thread thr;
    private final NetworkUtil networkUtil;
    public HashMap<String, String> userMap;

    public ReadThreadServer(HashMap<String, String> map, NetworkUtil networkUtil) {
        this.userMap = map;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        String password = userMap.get(loginDTO.getUserName());
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        networkUtil.write(loginDTO);
                    }

                    if(o instanceof GetPlayer){
                        new Thread(()-> {
                            String s = ((GetPlayer) o).getName();
                            List<Player> ClubPlayerList = new ArrayList<>();
                            for (Player p : Server.getPlayerList()) {
                                //System.out.println(p);
                                if (p.getClub().equalsIgnoreCase(s)) {
                                    ClubPlayerList.add(p);
                                }
                            }

                            GetPlayerResponse getPlayerList = new GetPlayerResponse();
                            getPlayerList.setPlayerList(ClubPlayerList);

                            try {
                                networkUtil.write(getPlayerList);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }).start();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
