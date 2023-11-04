package com.quantumslots.physhacks.service.utils;

import com.quantumslots.physhacks.model.Player;

public class PlayerUtils {
    private static int startingMoney = 100;
    public static Player getDefaultPlayer(){
        Player player = new Player(startingMoney, 0, 50);
        player.setBet(0);
        return player;
    }
}
