package com.quantumslots.physhacks.controllers;

import com.quantumslots.physhacks.model.Player;
import com.quantumslots.physhacks.model.Potentials;
import com.quantumslots.physhacks.model.potentials.PotentialFunction;
import com.quantumslots.physhacks.service.RewardService;
import com.quantumslots.physhacks.service.utils.PlayerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.quantumslots.physhacks.service.utils.PlayerUtils;

@Controller
public class HomeController {

    String HOME = "index";
    private Player player;
    @Autowired
    private RewardService rewardService;
    private PotentialFunction potential;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String init(Model model){
        player = PlayerUtils.getDefaultPlayer();
        generateModel(model);
        return HOME;
    }

    public void placeBet(int bet){
        player.setBet(bet);
    }

    public void selectRange(float selector1, float selector2){
    }

    public void tick(){

    }

    public void changePotential(Potentials potential){}

    @RequestMapping(value = "/measure", method = RequestMethod.POST)
    public String measure(Model model, @ModelAttribute Player newPlayerProfile){
        player.setSelector1position(newPlayerProfile.getSelector1position());
        player.setSelector2position(newPlayerProfile.getSelector2position());
        return HOME;
    }

    private void generateModel(Model model){
        model.addAttribute("player", player);
    }
    public void measure(double time){
        double position = potential.makeMeasurement(time);
        float selector1 = player.getSelector1position();
        float selector2 = player.getSelector2position();

        if (selector1 <= position && position <= selector2) {
            // what are gains
            int gains = rewardService.getReward(potential.getPotentialStructure(), selector1, selector2, time, player.getBet());
            player.setCoins(player.getCoins() + gains);
        }
    }
}
