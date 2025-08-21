package pl.cx.p001.model.robot;

import lombok.Value;

/**
 * Linear compute-time and compute-energy coefficients for the robot brain model.
 */
@Value
public class NeuralCostCoeffs {
    double tBase;
    double tPerLayer;
    double eBase;
    double ePerNeuron;
    double tickBudget;
    public NeuralCostCoeffs(double tBase, double tPerLayer, double eBase, double ePerNeuron, double tickBudget) {
        if (tickBudget <= 0) throw new IllegalArgumentException("tickBudget <= 0");
        if (tBase < 0 || tPerLayer < 0 || eBase < 0 || ePerNeuron < 0) throw new IllegalArgumentException("negative coeff");
        this.tBase = tBase; this.tPerLayer = tPerLayer; this.eBase = eBase; this.ePerNeuron = ePerNeuron; this.tickBudget = tickBudget;
    }
}

