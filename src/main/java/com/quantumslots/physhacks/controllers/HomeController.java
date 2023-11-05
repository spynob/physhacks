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

import java.util.Objects;

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
    public String home(Model model){
        if (Objects.isNull(player)){init();}
        generateModel(model);
        return HOME;
    }

    private void init() {
        player = PlayerUtils.getDefaultPlayer();
        potential = new InfiniteSquareWell();
        // Initialize timer;
    }

    private void generateModel(Model model) {
        model.addAttribute("player", player);
    }

    public String placeBet(Model model, int bet){
        player.setBet(bet);
        return "redirect:/";
    }

    @RequestMapping()
    public void tick(){
    }

    public void changePotential(Potentials potential){}

    @RequestMapping(value = "/measure", method = RequestMethod.POST)
    public String ROLL(Model model, @ModelAttribute("player") Player player){
        this.player.setBet(player.getBet());
        this.player.setSelector1position(player.getSelector1position());
        this.player.setSelector2position(player.getSelector2position());
        //measure(timer);
        return "redirect:/";
    }

    private void measure(double time){
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
