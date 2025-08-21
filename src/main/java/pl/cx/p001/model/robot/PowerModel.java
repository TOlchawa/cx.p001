package pl.cx.p001.model.robot;

import lombok.Value;

/**
 * Continuous power draw parameters (idle + self-discharge rates per second).
 */
@Value
public class PowerModel {
    double idleRatePerSecond;
    double selfDischargePerSecond;
    public PowerModel(double idleRatePerSecond, double selfDischargePerSecond) {
        if (idleRatePerSecond < 0 || selfDischargePerSecond < 0) throw new IllegalArgumentException("rates < 0");
        this.idleRatePerSecond = idleRatePerSecond;
        this.selfDischargePerSecond = selfDischargePerSecond;
    }
}

