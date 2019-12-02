package expiramental.integral.nodes;

import expiramental.integral.IntegralUnitNode;
import expiramental.integral.IntegrationException;

public class PowerIntegralUnitNode extends IntegralUnitNode {

    public PowerIntegralUnitNode(String value) {
        super(value);
    }

    @Override
    public String integrate() throws IntegrationException {
        String firstIntegration = children[1].integrate();
        return "(" + children[0].integrate() + "^(" + firstIntegration + "+1))/(" + firstIntegration + "+1)";
    }
}
