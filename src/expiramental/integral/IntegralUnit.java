package expiramental.integral;

import expiramental.integral.nodes.AdditionIntegralUnitNode;
import expiramental.integral.nodes.MultiplicationIntegralUnitNode;
import expiramental.integral.nodes.PowerIntegralUnitNode;
import expiramental.integral.nodes.SimpleIntegralNode;

import java.util.HashMap;

/**
 * The main handler for a integral unit:
 *      - parsing
 *      - solving
 */
public class IntegralUnit {

    private static HashMap<Character, Integer> operators = new HashMap<Character, Integer>(){{
       put('+', 1); put('-', 1);
       put('/', 2); put('*', 2);
       put('^', 3);
    }};

    /**
     * The expression to be integrated
     */
    private String source;

    private IntegralUnitNode head;
    private boolean hasIntegrationTree;

    public IntegralUnit(String source){
        this.source = source.replace(" ","");
    }

    public double integrate(float start, float end) throws IntegrationException{
        if (!hasIntegrationTree){
            head = getUnitNode(0, source.length());
            hasIntegrationTree = true;
        }
        System.out.println(head.integrate());
        return 0;
    }

    private int scanForNearestOperator(int start, int end){
        if(source.charAt(start) == '(' && source.charAt(end) == ')')
        {
            start++;
            end--;
        }
        int index = -1;
        int priority = 25000;
        int level = 0;
        for (int i = start; i < end; i++) {
            if(source.charAt(i) == '(') level++;
            if(source.charAt(i) == ')') level--;
            if(level == 0 && operators.containsKey(source.charAt(i)) && operators.get(source.charAt(i)) < priority){
                priority = operators.get(source.charAt(i));
                index = i;
            }
        }
        return index;
    }

    private IntegralUnitNode getUnitNode(int start, int end){
        IntegralUnitNode node = null;
        if(start - end == 0) {
            System.out.println("Start was end " + start + "," + end);
            return null;
        }

        int operatorIndex = scanForNearestOperator(start, end);

        if(operatorIndex == -1) {
            System.out.println("Found string " + source.substring(start, end));
            return new SimpleIntegralNode(source.substring(start, end));
        }

        switch (source.charAt(operatorIndex)){
            case '+':
                node = new AdditionIntegralUnitNode("+");
                break;
            case '^':
                node = new PowerIntegralUnitNode("^");
                break;
            case '*':
                node = new MultiplicationIntegralUnitNode("*");
                break;
        }

        node.setChild(0, getUnitNode(start, operatorIndex));
        node.setChild(1, getUnitNode(operatorIndex + 1, end));
        return node;
    }
}
