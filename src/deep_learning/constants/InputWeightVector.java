package deep_learning.constants;

public class InputWeightVector {
    private float[] inputs;
    private float[][] weights;

    public InputWeightVector(int size, int nextSize){
        inputs = new float[size];
        weights = new float[size][];
        for (int i = 0; i < size; i++) {
            weights[i] = new float[nextSize];
        }
    }

    public float getWeight(int input, int weight){
        return weights[input][weight];
    }

    public float getValue(int input){
        return inputs[input];
    }

    public void setInput(int inputIndex, float value, float[] weights){
        inputs[inputIndex] = value;
        this.weights[inputIndex] = weights;
    }

    public static float sum(int weightIndex, float[] inputs, float[][] weights){
        float sum = 0;
        for (int i = 0; i < inputs.length; i++){
            sum += inputs[i] * weights[i][weightIndex];
        }
        return sum;
    }
}
