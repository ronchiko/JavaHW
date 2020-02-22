package deep_learning.core;

/**
 * Interface for implementing neural networks that can be auto-trained
 */
public interface NeuralNetwork {
    void call(float[] inputs);
    void train(float[] expected);

    String serialize();
}
