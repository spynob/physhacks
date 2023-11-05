package com.quantumslots.physhacks.controllers;

import com.quantumslots.physhacks.model.Player;
import com.quantumslots.physhacks.model.Potentials;
import com.quantumslots.physhacks.model.potentials.InfiniteSquareWell;
import com.quantumslots.physhacks.model.potentials.PotentialFunction;
import com.quantumslots.physhacks.service.RewardService;
import com.quantumslots.physhacks.service.gui.PlotService;
import com.quantumslots.physhacks.service.utils.PlayerUtils;
import org.jfree.chart.plot.Plot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.quantumslots.physhacks.service.utils.PlayerUtils;

import java.util.Optional;


public class HomeController {

    //String HOME = "index";
    private Player player = new Player(100, -0.5f, 0.5f);

    private RewardService rewardService = new RewardService();
    private PlotService plotService;
    private PotentialFunction potential;
    private double startTime;
    private float timeMultiplier = 1;

    //@RequestMapping(value = "/", method = RequestMethod.GET)
    public HomeController(PotentialFunction potential, PlotService plotService) {
        changePotential(potential);
        this.plotService = plotService;
    }

    public void placeBet(int bet) {
        player.setBet(bet);
        startTime();
    }

    public void selectRange(float selector1, float selector2) {
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
        return potential.makeMeasurement((startTime - System.currentTimeMillis()) * timeMultiplier);
    }

    public boolean isWin(double position) {
        return position >= player.getSelector1position() && position <= player.getSelector2position();
    }

    private void generateModel(Model model) {
        model.addAttribute("player", player);
    }

    public void measure(double time) {
        double position = potential.makeMeasurement(time);
        float selector1 = player.getSelector1position();
        float selector2 = player.getSelector2position();

        if (selector1 <= position && position <= selector2) {
            // what are gains
            int gains = rewardService.getReward(potential.getPotentialStructure(), selector1, selector2, time, player.getBet());
            player.setBudget(player.getBudget() + gains);
        }
    }
}
