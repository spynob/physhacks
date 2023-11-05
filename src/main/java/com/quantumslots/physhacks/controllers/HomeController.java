package com.quantumslots.physhacks.controllers;

import com.quantumslots.physhacks.model.Player;
import com.quantumslots.physhacks.model.potentials.PotentialFunction;
import com.quantumslots.physhacks.service.RewardService;
import com.quantumslots.physhacks.service.gui.PlotService;


public class HomeController {

    //String HOME = "index";
    private Player player = new Player(100, -0.5f, 0.5f);

    private RewardService rewardService = new RewardService();
    private PlotService plotService;
    private PotentialFunction potential;
    private double startTime;
    private double runTime;
    private float timeMultiplier = 1;

    //@RequestMapping(value = "/", method = RequestMethod.GET)
    public HomeController(PotentialFunction potential, PlotService plotService) {
        changePotential(potential);
        this.plotService = plotService;
    }

    public void placeBet(int bet, float selector1, float selector2) {
        player.setBet(bet);
        player.setBudget(player.getBudget()-bet);
        selectRange(selector1,selector2);
        startTime();
    }

    private void selectRange(float selector1, float selector2) {
        player.setSelector1position(selector1);
        player.setSelector2position(selector2);
    }

    public void startTime() {
        startTime = System.currentTimeMillis();
    }

    public void changePotential(PotentialFunction potential) {
        this.potential = potential;
    }

    //@RequestMapping(value = "/measure", method = RequestMethod.POST)
    public double makeAMeasurement() {
        runTime = System.currentTimeMillis() - startTime;
        double position = potential.makeMeasurement((runTime) * timeMultiplier);
        plotService.updateGraph(position);
        playerWins(position);
        return position;
    }

    private boolean isWin(double position) {
        return position >= player.getSelector1position() && position <= player.getSelector2position();
    }

    private void playerWins(double position) {
        if (isWin(position)){
            player.setBudget(player.getBudget() + rewardService.getReward(potential.getPotentialStructure(),player.getSelector1position(),player.getSelector2position(), runTime, player.getBet()));
        }
    }

    public String getCash() {
        return String.valueOf(player.getBudget());
    }
}
