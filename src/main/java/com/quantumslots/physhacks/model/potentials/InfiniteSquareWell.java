package com.quantumslots.physhacks.model.potentials;

import com.quantumslots.physhacks.model.Potentials;

import java.util.ArrayList;

public class InfiniteSquareWell extends PotentialFunction {
    double squareWellWidth;
    /**
     * Default constructor for the infinite square well.
     * It creates a wave function with only the first eigenbasis
     */
    public InfiniteSquareWell() {
        this.potentialStructure = Potentials.InfiniteSquareWell;
        this.basisFunctions = new ArrayList<>(1);
        squareWellWidth = 1;
    }
    @Override
    public double evaluate(double position, double time) {
        return 0;
    }

    @Override
    public double eigenBasis(double position, int basisNumber) {
        return 0;
    }
}
