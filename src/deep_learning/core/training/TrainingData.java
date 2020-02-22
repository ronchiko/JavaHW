package deep_learning.core.training;

import java.util.ArrayList;

public class TrainingData {

    private float[] inputs;
    private float[] outputs;

    public TrainingData(String line){
        ArrayList<Float> storage = new ArrayList<>();
        int continueIndex = readArray(line, 0, storage);
        inputs = new float[storage.size()];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = storage.get(i);
        }
        storage.clear();
        readArray(line, ++continueIndex, storage);
        outputs = new float[storage.size()];
        for (int i = 0; i < outputs.length; i++) {
            outputs[i] = storage.get(i);
        }
    }

    private int readArray(String string, int index, ArrayList<Float> array){
        StringBuilder sb = new StringBuilder();
        for (int i = index; i < string.length(); i++) {
            final char c = string.charAt(i);
            if(c == '|')
                return i;
            if(c == ','){
                array.add(Float.parseFloat(sb.toString()));
                sb.delete(0, sb.length());
                continue;
            }
            sb.append(c);
        }
        return string.length();
    }

    public float[] inputs(){
        return inputs;
    }
    public float[] outputs(){
        return outputs;
    }
}
