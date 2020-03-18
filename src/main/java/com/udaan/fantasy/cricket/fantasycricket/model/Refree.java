package com.udaan.fantasy.cricket.fantasycricket.model;

public class Refree {

    Integer winningScore = 0;

    User winner;

    public void assessScore(User ... participants) {
        for(User participant : participants){
            if(participant.getFinalScore()!= -1 && winningScore < participant.getFinalScore()){
                winningScore = participant.getFinalScore();
                winner = participant;
            }
        }
    }


    public User findWinner() {
        return winner;
    }
}
