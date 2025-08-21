package pl.tom.loop.core;

/**
 * Linear compute-time and compute-energy coefficients for the robot's "brain".
 */
public final class NeuralCostCoeffs {
    private final double tBase;
    private final double tPerLayer;
    private final double eBase;
    private final double ePerNeuron;
    private final double tickBudget;

    public NeuralCostCoeffs(double tBase, double tPerLayer, double eBase, double ePerNeuron, double tickBudget) {
        if (tickBudget <= 0) throw new IllegalArgumentException("tickBudget <= 0");
        if (tBase < 0 || tPerLayer < 0 || eBase < 0 || ePerNeuron < 0)
            throw new IllegalArgumentException("negative cost coefficients");
        this.tBase = tBase;
        this.tPerLayer = tPerLayer;
        this.eBase = eBase;
        this.ePerNeuron = ePerNeuron;
        this.tickBudget = tickBudget;
    }

    public double tBase() { return tBase; }
    public double tPerLayer() { return tPerLayer; }
    public double eBase() { return eBase; }
    public double ePerNeuron() { return ePerNeuron; }
    public double tickBudget() { return tickBudget; }
}
