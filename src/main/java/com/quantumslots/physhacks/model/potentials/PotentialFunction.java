package com.quantumslots.physhacks.model.potentials;


import com.quantumslots.physhacks.model.Potentials;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public abstract class PotentialFunction {
    public final double HBAR = 1.05457182E-34;
    int[] boundaries = {-1, 1};
    int a = boundaries[1];

    private double mass = 9.11E-31;
    private Potentials potentialStructure;
    // List holding the eigenstates used
    private ArrayList<Integer> basisFunctions;
    // List of magnitudes for each eigenstates
    private ArrayList<Double> magnitudes;
    //Default constructor. Uses groundstate eigenbasis and magnitude of 1.
    //Constructor
    public PotentialFunction(Potentials potentialType) {
        potentialStructure = potentialType;
        this.basisFunctions = initializeBasisFunctions();
        this.magnitudes = initializeMagnitudes(); // make function to randomize magnitudes
        normalizeMagnitudes();
    }

    public ArrayList<Integer> initializeBasisFunctions() {
        ArrayList<Integer> basisFunctions = new ArrayList<>();
        Random random = new Random();
        int count = random.nextInt(6) + 5; // Generate a random count between 5 and 10

        while (basisFunctions.size() < count) {
            int randomNumber = random.nextInt(11); // Generate a random number between 5 and 10
            if (!basisFunctions.contains(randomNumber)) {
                basisFunctions.add(randomNumber);
            }
        }

        return basisFunctions;
    }

    public ArrayList<Double> initializeMagnitudes() {
        ArrayList<Integer> basis = this.basisFunctions;
        ArrayList<Double> magnitudes = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < basis.size(); i++) {
            double randomMagnitude = 0.0001 + (10 - 0.0001) * random.nextDouble();
            magnitudes.add(randomMagnitude);
        }

        return magnitudes;
    }


    public abstract double evaluate_probability(double x1, double x2, ArrayList<Integer> basisFunctions, ArrayList<Double> magnitudes);

    public Potentials getPotentialStructure() {
        return potentialStructure;
    }
    /**
     * Evaluates the value of the real part of the wave function
     * @param x Position along the x-axis where the function is to be evaluated
     * @param t Time at which the function is to be evaluated
     * @return The value of Re{psi(x,t)}
     */
    public double psi_real(double x, double t){
        double total = 0;
        for (int i=0; i<basisFunctions.size();i++) {
            int n = basisFunctions.get(i);
            total += magnitudes.get(i) * eigenBasis(x, n, i) * Math.cos(energy(n) * t);
        }
        return total;
    }

    /**
     * Evaluates the value of the imaginary part of the wave function
     * @param x Position along the x-axis where the function is to be evaluated
     * @param t Time at which the function is to be evaluated
     * @return The value of Im{psi(x,t)}
     */
    public double psi_imaginary(double x, double t){
        double total = 0;
        for (int i=0;i<basisFunctions.size();i++) {
            int n = basisFunctions.get(i);
            total += magnitudes.get(i) * eigenBasis(x, n, i) * Math.sin(energy(n) * t);
        }
        return total;
    }

    /**
     * "Observe" the particle and makes the wavefunction collapse
     * @param time Time at which the function is to be observed
     * @return The position of the collapse
     */
    public double makeMeasurement(double time) {
        Random random = new Random();
        float randomFloat = random.nextFloat(); // Random float between 0 (inclusive) and 1 (exclusive)

        // Scale and shift the random float to be between -1 and 1
        randomFloat = randomFloat * 2 - 1;
        // idea: To generate a random number according to a probability distribution, we find
        // an x such that P(-1, x) = randomFloat, where P(x0,x1) is the PDF.

        // initialize integration bounds from left-end to halfway
        double lowerBound = boundaries[0];
        double rightEnd = boundaries[1];
        double x= lowerBound + rightEnd / 2; // initially integrating from -1 to 0
        double probability;

        for (int i = 0; i < 50; i ++) { // do binary search 50 times assuming that's enough to find special x
            probability = evaluate_probability(lowerBound, x, basisFunctions, magnitudes);
            if (probability < randomFloat) x = x + rightEnd / 2;
            else x = x + lowerBound / 2;
        }
        return x;
    }

    /**
     * Evaluates the value of the nth eigenbasis
     * @param position Position at which to evaluate the basis
     * @param basisNumber Number determining which eigenbasis to evaluate.
     * @return
     */
    public abstract double eigenBasis(double position, int basisNumber, int magnitudeIndex);

    public abstract double energy(int basisNumber);

    /**
     * Makes sure the sum of the square of the magnitudes is equal to 1
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
    // Getters
    public ArrayList<Double> getMagnitudes() {
        return magnitudes;
    }

    public double getMass() { return mass; }

}
