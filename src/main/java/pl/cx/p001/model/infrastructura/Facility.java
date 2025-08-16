package pl.cx.p001.model.infrastructura;

import lombok.Data;
import pl.cx.p001.model.robot.Battery;

/**
 * Facility represents infrastructure objects such as factories, mines, etc.
 * Each facility has a condition (0.0 = destroyed, 1.0 = brand new),
 * energy demand, and battery state (stored energy).
 */
@Data
public class Facility {
    /**
     * Condition: 0.0 = destroyed, 1.0 = brand new
     */
    private double condition;
    /**
     * Energy demand per tick/unit
     */
    private double energyDemand;
    /**
     * Current battery state (stored energy)
     */
    private Battery battery;

    public Facility(double condition, double energyDemand, Battery battery) {
        this.condition = condition;
        this.energyDemand = energyDemand;
        this.battery = battery;
    }
}
