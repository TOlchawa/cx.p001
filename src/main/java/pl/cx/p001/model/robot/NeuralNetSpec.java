package pl.cx.p001.model.robot;

import lombok.Value;

/**
 * Describes size of a neural network (layers & total neurons) used for compute cost modeling.
 */
@Value
public class NeuralNetSpec {
    int layers;
    int neurons;
    public NeuralNetSpec(int layers, int neurons) {
        if (layers < 0) throw new IllegalArgumentException("layers < 0");
        if (neurons < 0) throw new IllegalArgumentException("neurons < 0");
        this.layers = layers;
        this.neurons = neurons;
    }
}

