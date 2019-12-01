package hw.unit4;

import hw.unit4.core.Stack;

import java.util.Scanner;

public class StackHW2 {

    private static Scanner in = new Scanner(System.in);

    //O(n) => single iteration loop
    static boolean isAnBn(String word){
        int a = 0, b = 0;
        boolean addingB = false;
        for (int i = 0;i < word.length();i++){
            final char c = word.charAt(i);
            if(addingB){
                if(c != 'B') return false;
                b++;
            }else{
                if(c == 'B') {
                    b++;
                    addingB = true;
                }else{
                    if(c != 'A') return false;
                    a++;
                }
            }
        }

        return a == b;
    }
    //O(n) => single iteration loop
    static boolean AeqB(String word){
        int a = 0, b = 0;
        for (int i = 0;i < word.length();i++){
            if(word.charAt(i) == 'A') a++;
            else b++;
        }

        return a == b;
    }
    //O(n) => single iteration loop
    static boolean ex3(String word){
        Stack<Character> stk = new Stack<>();

        boolean reachedMidPoint = false;

        for (int i = 0; i < word.length(); i++) {
            if(!reachedMidPoint){
                if(word.charAt(i) == '*')
                    reachedMidPoint = true;
                else
                    stk.push(word.charAt(i));
            }else{
                if(stk.isEmpty()) return false;
                char c1 = stk.pop(), c2 = stk.pop();
                if(c1 != c2 ){
                    return false;
                }
                if(c1 != word.charAt(i)) return false;
            }
        }
        if(!stk.isEmpty()) return false;
        return reachedMidPoint;
    }
    // Grades must not be empty
    static void printBest(int[] grades){
        Stack<Integer> best = new Stack<>();

        int nGrade = grades[0];
        best.push(0);
        for (int i = 1;i < grades.length;i++) {
            if(grades[i] > nGrade)
                nGrade = grades[i];
            best.push(i);
        }

        while (!best.isEmpty()){
            int index = best.pop();
            if(grades[index] == nGrade)
                System.out.print(++index + ",");
        }

        System.out.println("\b");
    }
    //O(n^2) => a nested while loop
    static Stack<Integer> deleteDuplicates(Stack<Integer> stk){
        Stack<Integer> out = new Stack<>(), temp = new Stack<>(), temp2 = new Stack<>();

        while (!stk.isEmpty())
            temp.push(stk.pop());

        while (!temp.isEmpty()){
            int pop = temp.pop();
            boolean found = false;
            while (!out.isEmpty() && !found) {
                if(out.top() == pop){
                    found = true;
                }else
                    temp2.push(out.pop());
            }
            if(!found) out.push(pop);
            while (!temp2.isEmpty())
                out.push(temp2.pop());

            stk.push(pop);
        }

        return out;
    }
    //O(n) => a single while loop + extraction
    static Stack<Integer> addBigNumbers(Stack<Integer> a, Stack<Integer> b){
        Stack<Integer> ta = new Stack<>(), tb = new Stack<>(), result = new Stack<>();

        while (!a.isEmpty()){
            ta.push(a.pop());
        }
        while (!b.isEmpty()){
            tb.push(b.pop());
        }

        boolean carry = false;

        while (!ta.isEmpty() || !tb.isEmpty()){

            int digit1 = 0;
            if(!ta.isEmpty()){
                digit1 = ta.pop();
                a.push(digit1);
            }
            int digit2 = 0;
            if(!tb.isEmpty()){
                digit2 = tb.pop();
                b.push(digit2);
            }

            result.push((digit1 + digit2 + (carry ? 1 : 0)) % 10);

            carry = digit1 + digit2 + (carry ? 1 : 0) >= 10;

        }

        if(carry) result.push(1);

        return result;
    }
    /*
        The stack must be sorted, and have at least 3 items
        O(n) => single while loop
     */
    static boolean isInvoiceSeries(Stack<Integer> a){
        Stack<Integer> temp = new Stack<>();

        while (!a.isEmpty())
            temp.push(a.pop());

        boolean isFirst = true;
        int d = 0;
        while (!temp.isEmpty()){
            int pop = temp.pop();
            a.push(pop);
            if(!temp.isEmpty()) {
                int diff = temp.top() - pop;

                if (!isFirst && diff != d) return false;
                if (isFirst) {
                    isFirst = false;
                    d = diff;
                }
            }
        }

        return true;
    }
    //O(n^2) => a single nested for loop
    static boolean noDuplicates(Stack<Integer> stk){
        Stack<Integer> temp = new Stack<>();

        while (!stk.isEmpty()){
            temp.push(stk.pop());
        }

        boolean isOk = true;
        while (!temp.isEmpty()){
            int pop = temp.pop();

            int c = 0;
            while (!stk.isEmpty() && isOk){
                if(pop == stk.top()) isOk = false;
                temp.push(stk.pop());
                c++;
            }
            for (int i = 0; i < c; i++) {
                stk.push(temp.pop());
            }

            stk.push(pop);
        }

        return isOk;
    }
    //O(n^2) => a single nested while loop
    static Stack<Integer> mergeStacks(Stack<Integer> a, Stack<Integer> b){
        Stack<Integer> result = new Stack<>(), ta = new Stack<>(), tb = new Stack<>();

        while (!a.isEmpty())
            ta.push(a.pop());

        while (!ta.isEmpty()){
            boolean found = false;
            int c = 0;

            while (!b.isEmpty() && !found){
                if(b.top().equals(ta.top()))
                    found = true;
                tb.push(b.pop());
                c++;
            }
            for (int i = 0; i < c; i++) {
                b.push(tb.pop());
            }

            if(found) result.push(ta.top());
            a.push(ta.pop());
        }

        return result;
    }
    //O(n^2) => a single nested while loop
    static Stack<Integer> fillStacks(Stack<Integer> a, Stack<Integer> b){
        Stack<Integer> result = new Stack<>(), ta = new Stack<>(), tb = new Stack<>();

        while (!a.isEmpty())
            ta.push(a.pop());

        while (!ta.isEmpty()){
            boolean found = false;
            int c = 0;

            while (!b.isEmpty() && !found){
                if(b.top().equals(ta.top()))
                    found = true;
                tb.push(b.pop());
                c++;
            }
            for (int i = 0; i < c; i++) {
                b.push(tb.pop());
            }

            if(!found) result.push(ta.top());
            a.push(ta.pop());
        }

        return result;
    }

    /*
        NOTE: returns an array where index 0 is the odd numbers and index 1 is the even ones
        O(n) => single iteration loop
     */
    static Stack[] splitInto2Stacks(Stack<Integer> a){
        Stack[] stacks = new Stack[]{
                new Stack<Integer>(), new Stack<Integer>()
        };

        Stack<Integer> temp = new Stack<>();
        while (!a.isEmpty())
            temp.push(a.pop());

        while (!temp.isEmpty()){
            if(temp.top() % 2 == 0) stacks[0].push(temp.top());
            else stacks[1].push(temp.top());

            a.push(temp.pop());
        }

        return stacks;
    }

    //O(n^2)
    static void interact(){
        Stack<Character> chars = new Stack<>();
        Stack<Character> temp = new Stack<>();

        StringBuilder word = new StringBuilder();
        while (!word.toString().equals("exit")){
            char c = in.nextLine().charAt(0);

            if (c == '.') {
                while (!chars.isEmpty() && chars.top() != '.') {
                    temp.push(chars.pop());
                }
                while (!temp.isEmpty()) {
                    word.append(temp.top());
                    System.out.print(temp.pop());
                }
                System.out.println();
            } else if (c == '~') {
                chars.pop();
            } else if (c == '*') {
                while (!chars.isEmpty() && chars.top() != '.') {
                    chars.pop();
                }
            } else {
                chars.push(c);
            }
        }
    }



    public static void main(String[] args) {
        Stack<Integer> n1 = new Stack<>(), n2 = new Stack<>();

        n1.push(3);
        n1.push(13);
        n1.push(12);
        n1.push(1);

        n2.push(9);
        n2.push(10);
        n2.push(11);
        n2.push(12);

        System.out.println(mergeStacks(n1, n2));
    }
}
