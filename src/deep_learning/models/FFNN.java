package deep_learning.models;

import deep_learning.constants.NonLinearity;
import deep_learning.core.Layer;
import deep_learning.core.NeuralNetwork;
import deep_learning.core.Neuron;

public class FFNN implements NeuralNetwork {

    private final static float LEARNING_STEP = .1f;

    private Layer[] layers;
    private int inputCount;
    private long generation;

    public FFNN(int inputs, int[] hidden, int outputs){
        layers = new Layer[2 + hidden.length];
        inputCount = inputs;
        generation = 0;

        layers[0] = new Layer(inputs, 0, NonLinearity.Sigmoid);
        for (int i = 0; i < hidden.length; i++) {
            layers[i + 1] = new Layer(hidden[i], layers[i].size(), NonLinearity.Sigmoid);
        }
        layers[hidden.length + 1] = new Layer(outputs, hidden[hidden.length - 1], NonLinearity.Sigmoid);
    }

    public void call(float[] inputs){
        if(inputs.length != inputCount)
            return;
        for (int i = 0; i < inputCount; i++) {
            layers[0].get(i).set(inputs[i]);
        }

        for (int i = 1; i < layers.length; i++) {
            layers[i].call(layers[i - 1]);
        }
    }
    public void train(float[] expected){
        backpropgate(expected);
        for (int i = 1; i < layers.length; i++) {
            layers[i].train(layers[i - 1], LEARNING_STEP);
        }
        generation++;
    }
    public float[] outputs(){
        float[] outputs = new float[layers[layers.length - 1].size()];
        for (int i = 0; i < outputs.length; i++) {
            outputs[i] = layers[layers.length - 1].get(i).getValue();
        }
        return outputs;
    }

    private void backpropgate(float[] expectedValues){
        for (int i = layers.length - 1; i >= 0; i--) {
            float[] errors = new float[layers[i].size()];
            if(i != layers.length - 1){
                for (int j = 0; j < layers[i].size(); j++) {
                    float error = 0f;
                    for (Neuron neuron : layers[i + 1].neurons()) {
                        error += neuron.getWeight(j) * neuron.getDelta();
                    }
                    errors[j] = error;   
                }
            }else{
                int k = 0;
                for (Neuron neuron : layers[i].neurons()) {
                    errors[k] = expectedValues[k] - neuron.getValue();
                    k++;
                }
            }

            for (int j = 0; j < layers[i].size(); j++) {
                var neuron = layers[i].get(j);
                neuron.setDelta(neuron.getNonLinearity().compute(neuron.getValue()) * errors[j]);
            }
        }
    }

    public String serialize(){
        StringBuilder sb = new StringBuilder();
        sb.append("G: ").append(generation).append("\n");
        for (Layer layer : layers) {
            sb.append(layer.serialize());
        }
        return sb.toString();
    }
    public static FFNN load(){
        return null;
    }
}
