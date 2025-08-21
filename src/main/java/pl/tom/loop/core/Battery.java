package pl.tom.loop.core;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Battery stores energy and degrades over time. Units are abstract but consistent across the model.
 */
@Getter
@Setter
@Accessors(fluent = true)
public class Battery {
    private double capacity;      // max energy
    private double currentLevel;  // current energy
    private double condition;     // 0.0..1.0 (health)

    public Battery(double capacity, double currentLevel, double condition) {
        if (capacity < 0) throw new IllegalArgumentException("capacity < 0");
        if (currentLevel < 0) currentLevel = 0;
        if (currentLevel > capacity) currentLevel = capacity;
        if (condition < 0 || condition > 1) throw new IllegalArgumentException("condition not in [0,1]");
        this.capacity = capacity;
        this.currentLevel = currentLevel;
        this.condition = condition;
    }

    public double getStateOfCharge() {
        return capacity == 0 ? 0 : currentLevel / capacity;
    }

    public boolean hasEnergy(double amount) {
        return currentLevel >= amount && condition > 0;
    }

    public double consume(double amount) {
        if (amount <= 0) return 0;
        double taken = Math.min(currentLevel, amount);
        currentLevel -= taken;
        return taken;
    }

    public void charge(double amount) {
        if (amount <= 0) return;
        currentLevel = Math.min(capacity, currentLevel + amount);
    }

    public void degrade(double delta) {
        if (delta <= 0) return;
        condition = Math.max(0, condition - delta);
    }
}

