package com.quantumslots.physhacks.potentials;


import com.quantumslots.physhacks.model.Potentials;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class PotentialFunction {
    public final double HBAR = 1.05457182E-34;
    private Potentials potentialStructure;
    // List holding the eigenstates used
    private ArrayList<Integer> basisFunctions;
    // List of magnitudes for each eigenstates
    private ArrayList<Double> magnitudes;
    //Default constructor. Uses groundstate eigenbasis and magnitude of 1.
    public PotentialFunction(Potentials potentialType) {
        potentialStructure = potentialType;
        basisFunctions = new ArrayList<>(Arrays.asList(1));
        magnitudes = new ArrayList<>(Arrays.asList(1.0));
        normalizeMagnitudes();
    }
    //Constructor
    public PotentialFunction(Potentials potentialType, ArrayList<Integer> basisFunctions, ArrayList<Double> magnitudes) {
        potentialStructure = potentialType;
        this.basisFunctions = basisFunctions;
        this.magnitudes = magnitudes;
        normalizeMagnitudes();

    }
    public Potentials getPotentialStructure() {

        return potentialStructure;
    }
    /**
     * Evaluates the value of the wave function
     * @param position Position along the x axis where the function is to be evaluated
     * @param time Time at which the function is to be evaluated
     * @return The probability density of the wave function
     */
    public abstract double evaluate(double position, double time);

    /**
     * "Observe" the particle and makes the wavefunction collapse
     * @param time Time at which the function is to be observed
     * @return The position of the collapse
     */
    public abstract double makeMeasurement(double position, double time);

    /**
     * Evaluates the value of the nth eigenbasis
     * @param position Position at which to evaluate the basis
     * @param basisNumber Number determining which eigenbasis to evaluate.
     * @return
     */
    public abstract double eigenBasis(double position, double time, int basisNumber);

    public abstract double energy(int basisNumber);

    /**
     * Makes sure the sum of the square of the magnitudes is equal to 1
     * @return True if succeeded, false otherwise
     */
    private void normalizeMagnitudes() {
        double sum = 0;
        int size = magnitudes.size();
        // Gets the sum of the magnitudes square
        for (int i = 0; i < size; i++) {
            sum += magnitudes.get(i)*magnitudes.get(i);
        }
        // Divides each magnitudes by the sum of the magnitudes square to make sure the wavefunction is normalized.
        for (int i = 0; i < size; i++) {
            magnitudes.set(i, magnitudes.get(i)/Math.pow(sum, 0.5));
        }
    }

    public ArrayList<Double> getMagnitudes() {
        return magnitudes;
    }

}
