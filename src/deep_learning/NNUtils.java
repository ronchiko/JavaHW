package deep_learning;

import deep_learning.core.NeuralNetwork;
import deep_learning.core.training.TrainingData;
import deep_learning.core.training.trainers.NNTDTrainer;

import java.io.IOException;
import java.util.function.Function;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.max;
import static java.lang.Math.E;

public final class NNUtils {

    private static final float ADA = 0.01f;
    private static final float GD_PRECISION = 0.00001f;

    /**
     * Compute gradient descent on a function
     * @param gradient
     * @return
     */
    public static float gradientDescent(Function<Float, Float> gradient){
        float curX = 6.0f;
        float previousStepSize = 1f;

        while (previousStepSize > GD_PRECISION){
            float prevX = curX;
            curX = - ADA * gradient.apply(prevX);
            previousStepSize = Math.abs(curX - prevX);
        }

        return curX;
    }

    public static float binaryCrossEntropyLoss(float prediction, float actual){
        return (float)(actual * log(prediction) + (1 - actual) * log(1 - prediction));
    }
    public static float meanSquaredLoss(float prediction, float actual){
        return (float)pow(actual - prediction, 2);
    }
    public static float hingeLoss(float prediction, float actual){
        return max(0, 1 - prediction * actual);
    }

    public static float sigmoid(float z){
        return (float)(1 / (1 + pow(E, -z)));
    }
    public static float sigmoid_prime(float z){
        return sigmoid(z)*(1 - sigmoid(z));
    }

    public static float tanh(float z){
        return (float)((pow(E, z) - pow(E, -z)) / (pow(E, z) + pow(E, -z)));
    }
    public static float tanh_prime(float z){
        return 1 - (float)pow(tanh(z), 2);
    }

    public static float relu(float z){
        return max(0, z);
    }
    public static float relu_prime(float z){
        return z > 0 ? 1 : 0;
    }


    public static void trainEpoch(NeuralNetwork network, String dataPath) throws IOException {
        NNTDTrainer trainer = new NNTDTrainer(dataPath);
        trainEpoch(network, trainer.epochs(), trainer);
    }
    public static void trainEpoch(NeuralNetwork network, int epoch, Function<Integer, TrainingData> collector){
        for (int i = 0; i < epoch; i++) {
            TrainingData data = collector.apply(epoch);
            network.call(data.inputs());
            network.train(data.outputs());
        }
    }
}
