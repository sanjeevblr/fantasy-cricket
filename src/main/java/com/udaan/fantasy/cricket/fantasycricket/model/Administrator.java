package com.udaan.fantasy.cricket.fantasycricket.model;

import com.udaan.fantasy.cricket.fantasycricket.score.ScoreBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Administrator {

    String name;
    Integer id;

    //TODO - move the logic from here to AdministratorService

    ScoreBoard scoreBoard;

    public void updateScoreBoard(Integer playerId, Integer score) {
        scoreBoard.update(playerId, score);
    }
}
