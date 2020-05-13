package StacksAndQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Evaluate {
    public static void main(String[] args) {
        LinkedStack<String> ops = new LinkedStack<String>();
        LinkedStack<Double> value = new LinkedStack<Double>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("(")) ;
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals(")"))
            {
                String op = ops.pop();
                if (op.equals("+")) value.push(value.pop() + value.pop());
                else if (op.equals("*")) value.push(value.pop() * value.pop());
            }
            else value.push(Double.parseDouble(s));
            /*switch (s) {
                case "(":
                    break;
                case "+":
                case "*":
                    ops.push(s);
                    break;
                case ")":
                    String op = ops.pop();
                    switch (op) {
                        case "+":
                            value.push(value.pop() + value.pop());
                            break;
                        case "*":
                            value.push(value.pop() * value.pop());
                            break;
                    }
                    break;
                default:
                    value.push(Double.parseDouble(s));
                    break;
            }*/
        }
        StdOut.println(value.pop());
    }
}
