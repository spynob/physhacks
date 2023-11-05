package com.quantumslots.physhacks.service;

import com.quantumslots.physhacks.model.Potentials;

import java.lang.Math;


public class RewardService {

    private float distanceScale(float s, float r){
        return (float) (15*Math.exp(-15*(r/s)) +1);
    }

    private float timeScale(double time, float r){
        return (float) ((float) 1 / (250*Math.pow(r,0.5)) * Math.pow(time, 3.5)) + 1;


        //if (tScale > 5) tScale = (float) (1000 * Math.exp(-0.8*time) + 1);
    }

    /*
    public float timeScale(){
        if slow vScale = 0.5;
        if normal vScale = 1;
        if fast vScale = 1.5;

        return vScale;
    }
     */


    //add speed factor
    public int getReward(Potentials potential, float selector1, float selector2, double time, int bet){
        float reward;
        float range = Math.abs(selector1 - selector2);
        float screen = 10;

        switch (potential){
            case InfiniteSquareWell:
                time = 1;

                reward = bet * timeScale(time, range) * distanceScale(screen, range);
                //reward = bet + (bet * (1+time/5) * rewardFunction(screen, range))/2;

                return Math.round(reward);
            default:
                throw new IllegalArgumentException("Potential not handled");

            case StepPotential:
                reward = bet * timeScale(time, range) * distanceScale(screen, range);

                return Math.round(reward);
        }
    }


}



