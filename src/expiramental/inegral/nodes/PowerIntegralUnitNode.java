package expiramental.inegral.nodes;

import expiramental.inegral.IntegralUnitNode;

public class PowerIntegralUnitNode extends IntegralUnitNode {

    public PowerIntegralUnitNode(String value) {
        super(value);
    }

    @Override
    public String integrate() {
        String firstIntegration = children[1].integrate();
        return "(" + children[0].integrate() + "^(" + firstIntegration + "+1))/(" + firstIntegration + "+1)";
    }
}
