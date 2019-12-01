package prototyping.shantingyard;

import hw.unit4.core.Queue;
import hw.unit4.core.Stack;

public class Calculator {

    private char[] operators = {
            '+', '-', '(', ')', '/', '*'
    };

    public Calculator(){

    }

    private int getOV(char c){
        switch (c){
            case '+': case '-':
                return 1;
            case '*': case '/':
                return 2;
        }

        return 0;
    }

    private boolean isOperator(char c){
        for (int i = 0; i < operators.length; i++) {
            if(operators[i] == c) return true;
        }
        return false;
    }

    private Double doOperation(char op, double a, double b){
        if(op == '+') return a + b;
        if(op == '-') return a - b;
        if(op == '*') return a * b;
        if(op == '/') return a / b;

        return null;
    }

    public Double solve(String exp){
        Queue<String> queue = shuntingYard(exp.replace(" ", ""));
        Stack<String> stack = new Stack<>();

        while (!queue.isEmpty()){
            String deq = queue.remove();

            if(isOperator(deq.charAt(0))){
                double n1 = ShuntingYardUtils.toDouble(stack.pop());
                double n2 = !stack.isEmpty() ? ShuntingYardUtils.toDouble(stack.pop()) : 0;

                stack.push(doOperation(deq.charAt(0), n2, n1).toString());
            }else{
                stack.push(deq);
            }
        }

        if(stack.isEmpty())
            return null;

        return ShuntingYardUtils.toDouble(stack.pop());
    }

    private Queue<String> shuntingYard(String exp){
        Queue<String> queue = new Queue<>();
        Stack<String> stack = new Stack<>();

        String word = "";

        for (int i = 0; i < exp.length(); i++) {
            final char current = exp.charAt(i);
            if(isOperator(current)){
                if(!word.isEmpty())
                    queue.insert(word);
                switch (current){
                    case '(':
                        stack.push("(");
                        break;
                    case ')':
                        boolean stopped = true;
                        while (!stack.isEmpty() && !stack.top().equals("(")){
                            queue.insert(stack.pop());
                            if(stack.isEmpty()) stopped = false;
                        }
                        if(stopped)
                            stack.pop();
                        else{
                            // An error happened, we are missing a bracket
                            System.err.println("Syntax ERROR");
                        }
                        break;
                    default:
                        int thisOv = getOV(current);

                        while (!stack.isEmpty() && getOV(stack.top().charAt(0)) != 0 && getOV(stack.top().charAt(0)) < thisOv){
                            queue.insert(stack.pop());
                        }
                        stack.push(current + "");
                        break;
                }
                word = "";
            }else{
                word += current;
            }
        }
        if(!word.isEmpty()) queue.insert(word);

        while (!stack.isEmpty()){
            queue.insert(stack.pop());
        }

        return queue;
    }
}
