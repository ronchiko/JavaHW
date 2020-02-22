package deep_learning.core;

import deep_learning.constants.NonLinearity;

public class Layer {
    private int size;
    private Neuron[] neurons;

    public Layer(int size, int previousSize, NonLinearity nonLinearity){
        this.size = size;
        neurons = new Neuron[size];
        for (int i = 0; i < size; i++) {
            neurons[i] = new Neuron(previousSize, nonLinearity);
        }
    }

    public Neuron get(int index) {
        return neurons[index];
    }
    public Neuron[] neurons() {
        return neurons;
    }
    public int size(){
        return size;
    }

    public void call(Layer prev){
        for (int i = 0; i < neurons.length; i++) {
            neurons[i].call(prev);
        }
    }
    public void train(Layer prev, float learningStep){
        if(prev == null)
            prev = this;

        for (Neuron neuron : neurons) {
            int j = 0;
            for (Neuron pNeuron : prev.neurons) {
                neuron.setWeight(j, neuron.getWeight(j) + neuron.getDelta() * pNeuron.getValue() * learningStep);
                j++;
            }
            neuron.setBias(neuron.getBias() + learningStep * neuron.getDelta());
        }
    }

    public String serialize(){
        StringBuilder sb = new StringBuilder();
        sb.append("L:");
        sb.append(size);
        sb.append("\n");
        for (Neuron neuron : neurons) {
            sb.append(neuron.serialize());
        }
        return sb.toString();
    }
}
