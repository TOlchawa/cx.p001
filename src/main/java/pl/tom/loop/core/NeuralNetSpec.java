package pl.tom.loop.core;

/**
 * Describes a neural network size (layers and total neurons).
 */
public final class NeuralNetSpec {
    private final int layers;
    private final int neurons;

    public NeuralNetSpec(int layers, int neurons) {
        if (layers < 0) throw new IllegalArgumentException("layers < 0");
        if (neurons < 0) throw new IllegalArgumentException("neurons < 0");
        this.layers = layers;
        this.neurons = neurons;
    }

    public int layers() { return layers; }
    public int neurons() { return neurons; }
}
