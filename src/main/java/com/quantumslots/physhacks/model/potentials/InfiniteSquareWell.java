package com.quantumslots.physhacks.model.potentials;

import com.quantumslots.physhacks.model.Potentials;
import java.util.ArrayList;


/**
 * For an infinite potential well starting from -a to a has the general solution
 * psi_n(x,y) = sqrt(1/a) * sin(n*pi*(x + a) / 2a) * exp(-i * n^2 * pi^2 * hbar * t / 2 * m * (2a))
 * In the case of a = 1, we simply this to:
 * psi_n(x,t) = sin(n*pi*(x + a) / 2a) * exp(-i * n^2 * pi^2 * hbar * t / 4 * m * 2a)
 */
public class InfiniteSquareWell extends PotentialFunction {
    /**
     * Default constructor for the infinite square well.
     * It creates a wave function with only the first eigenbasis
     */

    /**
     * Constructor
     */
    public InfiniteSquareWell() {
        super(Potentials.InfiniteSquareWell);
    }

    @Override
    /**
     * Closed-form for integral of square of wavefunction for infinite square well.
     * @param x1 Lower bound of integral
     * @param x2 Upper bound of integral
     * @return probability of particle being found between x1 and x2
     */
    public double evaluate_probability(double x1, double x2, ArrayList<Integer> basisFunctions, ArrayList<Double> magnitudes) {
        double total = 0;
        for (int n : basisFunctions) {
            total += Math.pow(magnitudes.get(n), 2) * (x1 / 2 - Math.sin(n * Math.PI * (x1 + a)) / 2 * Math.PI * n)
                    -Math.pow(magnitudes.get(n), 2) * (x2 / 2 - Math.sin(n * Math.PI * (x2 + a)) / 2 * Math.PI * n);
        }
        return total;
    }

    /**
     * Evaluate value of the eigenbasis
     * @param x Position at which to evaluate the basis
     * @param n Number determining which eigenbasis to evaluate.
     * @return
     */
    @Override
    public double eigenBasis(double x, int n) {
        return super.getMagnitudes().get(n) * Math.sin(n * Math.PI * (x + a) / 2);
    }

    @Override
    public double energy(int n) {
        return Math.pow(n,2) * Math.pow(Math.PI, 2) * Math.pow(super.HBAR,2) / (8 * super.getMass());
    }
}
