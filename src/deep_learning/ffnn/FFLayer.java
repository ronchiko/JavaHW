package deep_learning.ffnn;

import deep_learning.Percptron;
import static deep_learning.constants.InputWeightVector.sum;

public class FFLayer {
    private Percptron[] percptrons;
    private float[][] weights;
    private int size;
    private FFLayer next;

    public FFLayer(int size){
        this.size = size;
        percptrons = new Percptron[size];
        weights = new float[size][0];
        for (int i = 0; i < size; i++) {
            percptrons[i] = new Percptron();
        }
    }
    public FFLayer(int size, FFLayer next){
        this.next = next;
        this.size = size;
        percptrons = new Percptron[size];
        weights = new float[size][];
        for (int i = 0; i < size; i++) {
            percptrons[i] = new Percptron();
            weights[i] = new float[next.size];
            for (int j = 0; j < next.size; j++) {
                weights[i][j] = 1f / size;
            }
        }
    }

    public void compute(float[] inputs, float[][] weights){

        for (int i = 0;i < percptrons.length;i++){
            percptrons[i].compute(sum(i, inputs, weights));
        }

        if(!isOutputLayer()) {
            inputs = new float[percptrons.length];
            for (int i = 0; i < inputs.length; i++) {
                inputs[i] = percptrons[i].getValue();
            }

            next.compute(inputs, this.weights);
        }
    }

    public float get(int index) {
        return percptrons[index].getValue();
    }
    public void delta(int index, float delta){
        percptrons[index].setDelta(delta);
    }
    public float derivative(int index){
        return percptrons[index].derivative();
    }
    public int getSize() {
        return size;
    }
    public FFLayer getNext() {
        return next;
    }
    public boolean isOutputLayer(){
        return next == null;
    }
}
