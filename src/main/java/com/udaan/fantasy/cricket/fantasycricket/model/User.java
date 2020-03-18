package com.udaan.fantasy.cricket.fantasycricket.model;

import com.udaan.fantasy.cricket.fantasycricket.pool.PlayerPool;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

@EqualsAndHashCode(exclude = {"name", "playerPool", "virtualTeam", "myScore"})
public class User {
    Integer id;
    String name;


    private PlayerPool playerPool;
    VirtualTeam virtualTeam;
    int finalScore = 0;

    private static final Integer CREDIT = 100;
    private static final Integer MAX_PLAYER = 11;

    private static final Integer MAX_BATSMAN = 3;
    private static final Integer MAX_BOWLER = 3;
    private static final Integer KEEPER = 1;




    public void attachPool(PlayerPool playerPool) {
        this.playerPool = playerPool;
    }

    public void makeTeam(Integer ... ids) {
        virtualTeam = new VirtualTeam();
        for (Integer ii : ids){
            if(virtualTeam.size() == MAX_PLAYER){
                throw new PlayerCanNotBeAddedInVirtualPoolException("Size Exceeded");
            }
            if(virtualTeam.spentCredit()>=CREDIT){
                throw new PlayerCanNotBeAddedInVirtualPoolException("Credit Exceeded");
            }
            virtualTeam.addPlayer(playerPool.choosePlayer(ii));
        }
        virtualTeam.ensureRightCapacity();
    }

    public void notifyScore(Integer playerId, Integer score) {
        if(virtualTeam.isExists(playerId)){
            finalScore += score;
        }else{
            //skip the message
        }
    }




    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class VirtualTeam{

        Map<Integer, Player> teamPlayers = new HashMap<>();

        public void addPlayer(Player player){
            teamPlayers.put(player.getId(), player);
        }

        public boolean isExists(Integer playerId) {
            return teamPlayers.containsKey(playerId);
        }

        public Integer size(){
            return teamPlayers.size();
        }

        public Integer spentCredit(){
            Integer credit = 0;
            for (Player player : teamPlayers.values()){
                credit += player.getCredit();
            }
            return credit;
        }



        public void ensureRightCapacity() {
            if(!(teamPlayers.values().stream()
                    .filter(Player::isKeeper).count() >=1 && teamPlayers.values().stream()
                    .filter(Player::isBowler).count() >= 3 && teamPlayers.values().stream()
                    .filter(Player::isBatsMan).count() >= 3)){
                throw new NotRightlyChoosenVirtualTeamException("Ensure 3 batsman, 3 bowler and 1 keeper");

            }
        }

        private class NotRightlyChoosenVirtualTeamException extends RuntimeException {
            public NotRightlyChoosenVirtualTeamException(String s) {
            }
        }
    }

    private class PlayerCanNotBeAddedInVirtualPoolException extends RuntimeException {
        private String sizeExceeded;

        public PlayerCanNotBeAddedInVirtualPoolException(String sizeExceeded) {
            this.sizeExceeded = sizeExceeded;
        }
    }
}
