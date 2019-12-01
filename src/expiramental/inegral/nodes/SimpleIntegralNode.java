package expiramental.inegral.nodes;

import expiramental.inegral.IntegralUnitNode;

public class SimpleIntegralNode extends IntegralUnitNode {

    public SimpleIntegralNode(String value) {
        super(value);
    }

    @Override
    public String integrate() {

        if(value.equals("x"))
            return "x^2/2";
        return value + "*x";
    }
}
