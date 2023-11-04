package com.quantumslots.physhacks.model;

public class Player {
    private int coins;
    private float selector1position;
    private float selector2position;
    private int bet;

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        if (bet < 0 || bet > coins){
            throw new IllegalArgumentException("Not a valid bet");
        }
        this.bet = bet;
    }

    public Player(int coins, float selector1position, float selector2) {
        this.coins = coins;
        this.selector1position = selector1position;
        this.selector2position = selector2;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
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
