package com.udaan.fantasy.cricket.fantasycricket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player { // Player will be one of these - no all-rounder
    Integer id;
    String name;
    Integer credit;

    boolean batsMan;
    boolean bowler;
    boolean keeper;

    boolean star;

}
