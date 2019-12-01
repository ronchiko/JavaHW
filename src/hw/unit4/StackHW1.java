package hw.unit4;

import hw.unit4.core.Stack;
import server.reflection.ServerMethod;
import server.reflection.ServerMethodHost;

@ServerMethodHost
public class StackHW1 {

    @ServerMethod(callName = "Insert", paramCount = 2)
    public static void insert(Stack<Integer> stk, int num){
        Stack<Integer> temp = new Stack<>();

        boolean inserted = false;

        while(!stk.isEmpty() && !inserted) {

            if(num > stk.top()){
                stk.push(num);
                inserted = true;
            }else {
                temp.push(stk.pop());
            }
        }

        if(!inserted) stk.push(num);

        while (!temp.isEmpty())
            stk.push(temp.pop());
    }

    @ServerMethod(callName = "Merge", paramCount = 2)
    public static Stack<Integer> merge(Stack<Integer> a, Stack<Integer> b){
        Stack<Integer> ta = new Stack<>(), tb = new Stack<>();

        while (!a.isEmpty()) ta.push(a.pop());
        while (!b.isEmpty()) tb.push(b.pop());

        Stack<Integer> stk = new Stack<>();

        while (!ta.isEmpty() || !tb.isEmpty()){
            if(ta.isEmpty()){
                stk.push(tb.top());
                b.push(tb.pop());
            }else if(tb.isEmpty()){
                stk.push(ta.top());
                a.push(ta.pop());
            }else{
                if(tb.top() < ta.top()){
                    stk.push(tb.top());
                    b.push(tb.pop());
                }else{
                    stk.push(ta.top());
                    a.push(ta.pop());
                }
            }
        }

        return stk;
    }

    public static void main(String[] args) {
        Stack<Integer> c = new Stack<>(), c2 = new Stack<>();

        c.push(5);
        c.push(10);
        c.push(15);
        c.push(20);

        insert(c, 3);
        insert(c, 7);

        c2.push(7);
        c2.push(8);
        c2.push(12);
        c2.push(21);


        System.out.println(c);
        System.out.println(c2);
        System.out.println(merge(c2,c).toString());
    }
}
