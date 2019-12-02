package expiramental.integral.nodes;

import expiramental.integral.IntegralUnitNode;
import expiramental.integral.IntegrationException;

public class SimpleIntegralNode extends IntegralUnitNode {

    public SimpleIntegralNode(String value) {
        super(value);
    }

    public boolean isX() {
        return value.equals("x");
    }

    public float asNumber(){
        return Float.parseFloat(value);
    }

    @Override
    public String integrate() {

        if(isX())
            return "x^2/2";
        return value + "*x";
    }
}
