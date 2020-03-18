package com.udaan.fantasy.cricket.fantasycricket.pool;

import com.udaan.fantasy.cricket.fantasycricket.model.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerPool {

    Map<Integer, Player> allPlayers = new HashMap<>();

    public void addPlayer(Player ... players) {
        for(Player player : players){
            allPlayers.put(player.getId(), player);
        }
    }

    public Player choosePlayer(Integer id){
        return allPlayers.get(id);
    }

    public void showAllPlayers(){
        System.out.print(allPlayers);
    }
}
