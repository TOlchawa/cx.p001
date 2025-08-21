package pl.tom.loop.core;

/**
 * Describes continuous power draw parameters (idle and self-discharge).
 */
public final class PowerModel {
    private final double idleRatePerSecond;
    private final double selfDischargePerSecond;

    public PowerModel(double idleRatePerSecond, double selfDischargePerSecond) {
        if (idleRatePerSecond < 0 || selfDischargePerSecond < 0)
            throw new IllegalArgumentException("power rates must be >= 0");
        this.idleRatePerSecond = idleRatePerSecond;
        this.selfDischargePerSecond = selfDischargePerSecond;
    }

    public double idleRatePerSecond() { return idleRatePerSecond; }
    public double selfDischargePerSecond() { return selfDischargePerSecond; }
}
