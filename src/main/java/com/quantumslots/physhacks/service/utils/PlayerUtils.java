package com.quantumslots.physhacks.service.utils;

import com.quantumslots.physhacks.model.Player;

import java.security.PublicKey;

public class PlayerUtils {

    public static Player getDefaultPlayer(){
        Player player = new Player();
        player.setBet(0);
        player.setBudget(100);
        player.setSelector1position(0);
        player.setSelector2position(50f);
        return player;
    }
}
