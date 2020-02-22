package deep_learning.core.training.trainers;

import deep_learning.core.training.TrainingData;
import expiramental.utils.FileUtilities;

import java.io.IOException;
import java.util.function.Function;

/**
 * Class for loading .nntd files (neural network training data)
 */
public class NNTDTrainer implements Function<Integer, TrainingData> {

    private TrainingData[] epochs;

    public NNTDTrainer(String path) throws IOException {
        String[] lines = FileUtilities.readLines(path);
        epochs = new TrainingData[lines.length];
        int i = 0;
        for (String line :
                lines) {
            epochs[i] = new TrainingData(line);
            i++;
        }
    }

    @Override
    public TrainingData apply(Integer epoch) {
        return epochs[epoch];
    }

    public int epochs(){
        return epochs.length;
    }
}
