package com.quantumslots.physhacks.controllers;

import com.quantumslots.physhacks.model.Player;
import com.quantumslots.physhacks.model.Potentials;
import com.quantumslots.physhacks.model.potentials.InfiniteSquareWell;
import com.quantumslots.physhacks.model.potentials.PotentialFunction;
import com.quantumslots.physhacks.service.RewardService;
import com.quantumslots.physhacks.service.utils.PlayerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    //Spring and Thymeleaf constants
    String HOME = "index";

    // Model components
    private Player player;
    private PotentialFunction potential;
    private double timer;

    // Services
    @Autowired
    private RewardService rewardService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String init(Model model){
        player = PlayerUtils.getDefaultPlayer();
        potential = new InfiniteSquareWell();
        generateModel(model);
        return HOME;
    }

    private void generateModel(Model model) {
        model.addAttribute("player", player);
    }

    public void placeBet(int bet){
        player.setBet(bet);
    }

    public void selectRange(float selector1, float selector2){
    }

    @RequestMapping()
    public void tick(){
    }

    public void changePotential(Potentials potential){}

    @RequestMapping(value = "/measure", method = RequestMethod.POST)
    public String ROLL(Model model, @ModelAttribute("player") Player player){
        player.setBudget(player.getBudget());
        player.setBet(player.getBet());
        player.setSelector1position(player.getSelector1position());
        player.setSelector2position(player.getSelector2position());
        //measure(timer);
        generateModel(model);
        return HOME;
    }

    public void measure(double time){
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
