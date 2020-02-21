package deep_learning;

import deep_learning.constants.NonLinearity;

public class Percptron {

    private NonLinearity nonLinearity;
    private float value;
    private float delta;

    /**
     * Default constructor, non linearity is set to the sigmoid function
     */
    public Percptron(){
        nonLinearity = NonLinearity.Sigmoid;
    }
    /**
     * Sets a non linearity function using the pre-programmed non linearity functions
     * @param nonLinearity
     */
    public Percptron(NonLinearity nonLinearity){
        this.nonLinearity = nonLinearity;
    }

    public void compute(float summary){
        value = nonLinearity.compute(summary);
    }

    public float getValue() {
        return value;
    }
    public float derivative(){
        return nonLinearity.prime(value);
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }
}
