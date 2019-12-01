package expiramental.inegral.nodes;

import expiramental.inegral.IntegralUnitNode;

public class MultiplicationIntegralUnitNode extends IntegralUnitNode {

    public static final Class SIMPLE_NODE = SimpleIntegralNode.class,
            ADDITION_NODE = AdditionIntegralUnitNode.class,
            MULTIPLICATION_NODE = MultiplicationIntegralUnitNode.class,
            POWER_NODE = PowerIntegralUnitNode.class;

    public MultiplicationIntegralUnitNode(String value) {
        super(value);
    }

    @Override
    public String integrate() {
        Class first = children[0].getClass(), second = children[1].getClass();

        return null;
    }
}
