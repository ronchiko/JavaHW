package deep_learning.testing;

import deep_learning.constants.NonLinearity;
import deep_learning.models.FFNN;

public class FFNNTest {
    public static void main(String[] args) {
        FFNN ffnn = new FFNN(6, new int[]{3}, 1);

        float[] inputs = {
            2, 2, 2, 2, 2, 2
        };
        float[] expected = {
                .8f
        };

        for (int i = 0; i < 50; i++) {
            ffnn.call(inputs);
            var o = ffnn.outputs();
            for (float output :
                    o) {
                System.out.println(output);
            }

            ffnn.train(expected);
        }
        
    }
}
