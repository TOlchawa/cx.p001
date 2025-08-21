package pl.tom.loop.simple;

import pl.tom.loop.core.Battery;

/**
 * Reference battery using the core Battery behavior.
 */
public class SimpleBattery extends Battery {
    public SimpleBattery(double capacity, double currentLevel, double condition) {
        super(capacity, currentLevel, condition);
    }
}

