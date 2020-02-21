package deep_learning.ffnn;

public class FeedForwardNeuralNetwork {
    private FFLayer[] layers;
    private int inputs;
    private float[][] input_weights;

    public FeedForwardNeuralNetwork(int inputs, int[] layers_info, int outputs){
        layers = new FFLayer[layers_info.length + 1];

        // Create layer chain
        layers[layers_info.length] = new FFLayer(outputs);
        for(int i = layers_info.length; i > 0; i--){
            layers[i - 1] = new FFLayer(layers_info[i - 1], layers[i]);
        }

        this.inputs = inputs;
        input_weights = new float[inputs][];
        for (int i = 0; i < inputs; i++) {
            input_weights[i] = new float[layers[0].getSize()];
            for (int j = 0; j < layers[0].getSize(); j++) {
                input_weights[i][j] = 1f / inputs;
            }
        }

    }

    public void call(float[] inputs){
        if(inputs.length != this.inputs) {
            System.out.println("The size of the input vector must be " + this.inputs + ". current vector " + inputs.length);
            return;
        }

        layers[0].compute(inputs, input_weights);
    }

    public float output(int index){
        return layers[layers.length - 1].get(index);
    }

    public float[] outputs(){
        float[] outputs = new float[layers[layers.length - 1].getSize()];
        for (int i = 0; i < outputs.length; i++) {
            outputs[i] = layers[layers.length - 1].get(i);
        }
        return outputs;
    }

    public void backpropgate(float[] expectedValues){
        for (int i = layers.length - 1; i >= 0; i++) {
            final FFLayer layer = layers[i];
            float[] error = new float[layer.getSize()];
            if(i == layers.length - 1){
                for (int j = 0; j < layer.getSize(); j++) {
                    float error_v = 0;
                    for (int k = 0; k < layer.getSize(); k++) {

                    }
                    error[j] = error_v;
                }
            }else {
                for (int j = 0; j < layer.getSize(); j++) {
                    error[j] = expectedValues[j] - layer.get(j);
                }
            }
            for (int j = 0; j < layer.getSize(); j++) {
                layer.delta(j, error[j] * layer.derivative(j));
            }
        }
    }
}
