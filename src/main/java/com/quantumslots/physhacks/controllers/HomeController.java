package com.quantumslots.physhacks.controllers;

import com.quantumslots.physhacks.model.Player;
import com.quantumslots.physhacks.model.Potentials;
import com.quantumslots.physhacks.model.potentials.PotentialFunction;
import com.quantumslots.physhacks.service.RewardService;

public class HomeController {
    private Player player;
    private RewardService rewardService;
    private PotentialFunction potential;

    public void placeBet(int bet){
        player.setBet(bet);
    }

    public void selectRange(float selector1, float selector2){
    }

    public void tick(){

    }

    public void changePotential(Potentials potential){}
    public void measure(){}

}
