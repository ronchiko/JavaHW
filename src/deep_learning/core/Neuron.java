package deep_learning.core;

import deep_learning.constants.NonLinearity;

public class Neuron {

    private float value;
    private float[] weights;
    private float bias;
    private float delta;
    private NonLinearity nl;

    public Neuron(int weightCount, NonLinearity nl){
        this.nl = nl;
        delta = 1; bias = 1; value = 0;
        weights = new float[weightCount];
    }


    public void call(Layer previous){
        float sum = 0;
        for (int i = 0; i < previous.size(); i++) {
            sum += weights[i] * previous.get(i).value;
        }
        value = nl.compute(sum + bias);
    }

    public void set(float value){
        this.value = value;
    }
    public float getValue(){
        return value;
    }

    public void setWeight(int weight, float value){
        weights[weight] = value;
    }
    public float getWeight(int weight) {
        return weights[weight];
    }

    public void setDelta(float delta) {this.delta = delta;}
    public float getDelta() {return delta;}

    public void setBias(float bias){
        this.bias = bias;
    }
    public float getBias() {
        return bias;
    }

    public NonLinearity getNonLinearity() {
        return nl;
    }
    public String serialize(){
        StringBuilder sb = new StringBuilder();
        sb.append("N:");
        sb.append(value);
        sb.append(",");
        sb.append(delta);
        sb.append(",");
        sb.append(bias);
        sb.append(",");
        for (int i = 0; i < weights.length; i++) {
            sb.append(weights[i]);
            if(i != weights.length - 1)
                sb.append(",");
        }
        sb.append("\n");
        return sb.toString();
    }
}
