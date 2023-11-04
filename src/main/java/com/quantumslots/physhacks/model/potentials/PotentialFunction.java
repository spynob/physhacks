package com.quantumslots.physhacks.model.potentials;

import com.quantumslots.physhacks.model.Potentials;

import java.util.ArrayList;

public abstract class PotentialFunction {
    Potentials potentialStructure;
    // List holding the eigenstates used
    ArrayList<Integer> basisFunctions;
    // List of magnitudes for each eigenstates
    ArrayList<Double> magnitudes;
    public Potentials getPotentialStructure() {
        return potentialStructure;
    }

    /**
     * Evaluates the value of the wave function
     * @param position Position along the x axis where the function is to be evaluated
     * @param time Time at which the function is to be evaluated
     * @return The value of the wave function
     */
    public abstract double evaluate(double position, double time);

    /**
     * Evaluates the value of tthe nth eigenbasis
     * @param position Position at which to evaluate the basis
     * @param basisNumber Number determining which eigenbasis to evaluate.
     * @return
     */
    public abstract double eigenBasis(double position, int basisNumber);

}
