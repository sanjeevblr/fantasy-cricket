package com.udaan.fantasy.cricket.fantasycricket;

import com.udaan.fantasy.cricket.fantasycricket.model.Administrator;
import com.udaan.fantasy.cricket.fantasycricket.model.Player;
import com.udaan.fantasy.cricket.fantasycricket.model.Refree;
import com.udaan.fantasy.cricket.fantasycricket.model.User;
import com.udaan.fantasy.cricket.fantasycricket.pool.PlayerPool;
import com.udaan.fantasy.cricket.fantasycricket.score.ScoreBoard;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class FantasyCricketE2E {

    @Test
    public void testE2E(){
        //Given
        Player player1 = Player.builder().id(1).name("Player1").batsMan(true).credit(5).build();
        Player player2 = Player.builder().id(2).name("Player1").batsMan(true).credit(5).build();
        Player player3 = Player.builder().id(3).name("Player1").batsMan(true).credit(5).build();

        Player player4 = Player.builder().id(4).name("Player1").bowler(true).credit(5).build();
        Player player5 = Player.builder().id(5).name("Player1").bowler(true).credit(5).build();
        Player player6 = Player.builder().id(6).name("Player1").bowler(true).credit(5).build();

        Player player7 = Player.builder().id(7).name("Player1").batsMan(true).credit(5).build();
        Player player8 = Player.builder().id(8).name("Player1").bowler(true).credit(5).build();
        Player player9 = Player.builder().id(9).name("Player1").keeper(true).credit(5).build();

        Player player10 = Player.builder().id(10).name("Player1").batsMan(true).credit(5).build();
        Player player11 = Player.builder().id(11).name("Player1").bowler(true).credit(5).build();
        Player player12 = Player.builder().id(12).name("Player1").bowler(true).credit(5).build();


        User firstUser = User.builder().id(1).name("User1").build();
        User secondUser = User.builder().id(2).name("User1").build();

        PlayerPool playerPool = new PlayerPool();
        playerPool.addPlayer(player1, player2, player3, player4, player5, player6, player7, player8, player9, player10, player11, player12);

        //Debug
        playerPool.showAllPlayers();

        firstUser.attachPool(playerPool);
        secondUser.attachPool(playerPool);

        firstUser.makeTeam(player1.getId(),player2.getId(),
                            player3.getId(),player4.getId(),
                            player5.getId(),player6.getId(),
                            player7.getId(),player8.getId(),
                            player9.getId(),player10.getId(), player11.getId());

        secondUser.makeTeam(player2.getId(),
                player3.getId(),player4.getId(),
                player5.getId(),player6.getId(),
                player7.getId(),player8.getId(),
                player9.getId(),player10.getId(), player11.getId(),
                player12.getId());

        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.registerUser(firstUser);
        scoreBoard.registerUser(secondUser);

        Administrator administrator = Administrator.builder()
                .id(1).scoreBoard(scoreBoard).build();
        //When

        administrator.updateScoreBoard(player1.getId(), 1);
        administrator.updateScoreBoard(player2.getId(), 5);
        administrator.updateScoreBoard(player12.getId(), 7);


        Refree refree = new Refree();
        refree.assessScore(firstUser, secondUser);
        User winnerParticipant = refree.findWinner();

        //Then
        Assertions.assertThat(secondUser).isEqualTo(winnerParticipant);
        Assertions.assertThat(firstUser.getFinalScore()).isEqualTo(6);
        Assertions.assertThat(secondUser.getFinalScore()).isEqualTo(12);

    }
}
