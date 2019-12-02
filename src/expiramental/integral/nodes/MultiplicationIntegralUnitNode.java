package expiramental.integral.nodes;

import expiramental.integral.IntegralUnitNode;
import expiramental.integral.IntegrationException;
import expiramental.integral.UnsupportedIntegrationException;

public class MultiplicationIntegralUnitNode extends IntegralUnitNode {

    public static final Class SIMPLE_NODE = SimpleIntegralNode.class,
            ADDITION_NODE = AdditionIntegralUnitNode.class,
            MULTIPLICATION_NODE = MultiplicationIntegralUnitNode.class,
            POWER_NODE = PowerIntegralUnitNode.class;

    public MultiplicationIntegralUnitNode(String value) {
        super(value);
    }

    @Override
    public String integrate() throws IntegrationException {
        Class first = children[0].getClass(), second = children[1].getClass();

        if (first == SIMPLE_NODE && second == SIMPLE_NODE){
            SimpleIntegralNode n1 = (SimpleIntegralNode) children[0],
                               n2 = (SimpleIntegralNode) children[1];

            if(n1.isX() && n2.isX()) throw new UnsupportedIntegrationException();
            else if(n1.isX()) return n1.integrate() + "*" + n2.asNumber();
            else if(n2.isX()) return n2.integrate() + "*" + n1.asNumber();
            else return (n1.asNumber() * n2.asNumber()) + "*x";
        }

        return null;
    }
}
