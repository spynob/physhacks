package com.quantumslots.physhacks.potentials;



import com.quantumslots.physhacks.model.Potentials;

import java.util.ArrayList;

public class InfiniteSquareWell extends PotentialFunction {
    int[] boundaries = {-1, 1};
    /**
     * Default constructor for the infinite square well.
     * It creates a wave function with only the first eigenbasis
     */
    public InfiniteSquareWell() {
        super(Potentials.InfiniteSquareWell);
    }

    /**
     * Constructor
     * @param basisFunctions Holds the values for which eigenbasis to use
     * @param magnitudes Holds the value of the magnitudes of the eigenbasis
     */
    public InfiniteSquareWell(ArrayList<Integer> basisFunctions, ArrayList<Double> magnitudes) {
        super(Potentials.InfiniteSquareWell, basisFunctions, magnitudes);
    }
    @Override
    public double evaluate(double position, double time) {
        return 0;
    }

    @Override
    public double makeMeasurement(double position, double time) {
        return 0;
    }

    @Override
    public double eigenBasis(double x, double t, int n) {
        return super.getMagnitudes().get(n) * Math.sin(n * Math.PI * x / 2) * Math.cos(1 * t/super.HBAR);
    }

    @Override
    public double energy(int basisNumber) {
        return 0;
    }
}
