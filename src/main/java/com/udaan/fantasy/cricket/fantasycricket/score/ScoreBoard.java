package com.udaan.fantasy.cricket.fantasycricket.score;

import com.udaan.fantasy.cricket.fantasycricket.model.User;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

    List<User> allRegisteredUsers = new ArrayList<>();

    public void registerUser(User user){
        allRegisteredUsers.add(user);
    }

    public void update(Integer playerId, Integer score) {
        for(User user: allRegisteredUsers){
            user.notifyScore(playerId, score);
        }
    }
}
