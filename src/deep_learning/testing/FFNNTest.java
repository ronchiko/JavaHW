package deep_learning.testing;

import deep_learning.ffnn.FeedForwardNeuralNetwork;

public class FFNNTest {
    public static void main(String[] args) {
        FeedForwardNeuralNetwork ffnn = new FeedForwardNeuralNetwork(6, new int[]{3}, 1);

        float[] inputs = {
            2, 2, 2, 2, 2, 2
        };

        ffnn.call(inputs);

        System.out.println(ffnn.output(0));
    }
}
