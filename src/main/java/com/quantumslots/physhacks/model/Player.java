package com.quantumslots.physhacks.model;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class Player {
    private int budget=0;
    private float selector1position=0;
    private float selector2position=0;
    private int bet=0;

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public float getSelector1position() {
        return selector1position;
    }

    public void setSelector1position(float selector1position) {
        this.selector1position = selector1position;
    }

    public float getSelector2position() {
        return selector2position;
    }

    public void setSelector2position(float selector2position) {
        this.selector2position = selector2position;
    }
}
