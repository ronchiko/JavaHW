package expiramental.inegral.nodes;

import expiramental.inegral.IntegralUnitNode;

public class AdditionIntegralUnitNode extends IntegralUnitNode {

    public AdditionIntegralUnitNode(String value) {
        super(value);
    }

    @Override
    public String integrate() {
        return children[0].integrate() + " + " + children[1].integrate();
    }
}
