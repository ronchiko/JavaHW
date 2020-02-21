package deep_learning.constants;

import deep_learning.NNUtils;

import java.util.function.Function;

public enum NonLinearity {
    Sigmoid(NNUtils::sigmoid, NNUtils::sigmoid_prime),
    Tanh(NNUtils::tanh, NNUtils::tanh_prime),
    ReLU(NNUtils::relu, NNUtils::relu_prime);

    private Function<Float, Float> func;
    private Function<Float, Float> prime;

    NonLinearity(Function<Float, Float> func, Function<Float, Float> prime){
        this.func = func;
        this.prime = prime;
    }

    public float compute(float x){
        return func.apply(x);
    }
    public float prime(float x) {return prime.apply(x);}

    public Function<Float, Float> getPrime() {return prime;}
    public Function<Float, Float> getCompute() {
        return func;
    }
}
