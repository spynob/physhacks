package com.quantumslots.physhacks.model;

public class Player {
    private int budget;
    private float selector1position;
    private float selector2position;
    private int bet=0;

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        if (bet < 0 || bet > budget){
            throw new IllegalArgumentException("Not a valid bet");
        }
        this.bet = bet;
    }

    public Player(int budget, float selector1position, float selector2position) {
        this.budget = budget;
        this.selector1position = selector1position;
        this.selector2position = selector2position;
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
