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
    public InfiniteSquareWell(ArrayList<Integer> basisFunctions, ArrayList<Double> magnitudes, double mass) {
        super(Potentials.InfiniteSquareWell, basisFunctions, magnitudes, mass);
    }
    @Override
    public double evaluate(double position, double time) {
        return 0;
    }

    /**
     * Evaluate value of the eigenbasis
     * @param x Position at which to evaluate the basis
     * @param n Number determining which eigenbasis to evaluate.
     * @return
     */
    @Override
    public double eigenBasis(double x, int n) {
        return super.getMagnitudes().get(n) * Math.sin(n * Math.PI * x / 2);
    }

    @Override
    public double energy(int n) {
        return Math.pow(n,2) * Math.pow(Math.PI, 2) * Math.pow(super.HBAR,2) / (2 * boundaries[1] - boundaries[0] * super.getMass());
    }
}
