package expiramental.integral.nodes;

import expiramental.integral.IntegralUnitNode;
import expiramental.integral.IntegrationException;

public class AdditionIntegralUnitNode extends IntegralUnitNode {

    public AdditionIntegralUnitNode(String value) {
        super(value);
    }

    @Override
    public String integrate() throws IntegrationException {
        return children[0].integrate() + " + " + children[1].integrate();
    }
}
