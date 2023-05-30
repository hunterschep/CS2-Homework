/*
@name: Hunter Scheppat
@date: February 27th, 2023
@description: Calculator.java works as a java calculator by taking an infix expression and converting it to postfix
then performing operations on it
 */

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        String infix = myScan.nextLine();
        String postFinal = toPostFix(infix);
        Integer answer = calculate(postFinal);
        if (answer != null) {
            System.out.println(answer);
        }
        else {
            System.out.println("undef");
        }

    }

    public static String toPostFix(String s) {
        GenericLinkedStack<Character> myStack = new GenericLinkedStack<>();
        String postFix = "";

        for (int i = 0; i < s.length(); i++) {

            while (s.charAt(i) == ' ' && i < s.length() - 1) {
                i++;
            }

            char current = s.charAt(i);

            if (Character.isDigit(current)) {
                postFix += current;

                if (i+1 >= s.length() || !Character.isDigit(s.charAt(i+1))) {
                    postFix += ' ';
                }
            }

            else {
                int j = i + 1;
                if (j < s.length()) {
                    if (Character.isDigit(s.charAt(j))) {
                        postFix += current;
                        continue;
                    }

                }
                int thisPrecedence = precedence(current);
                while (!myStack.isEmpty() && precedence(myStack.peek()) >= thisPrecedence) {
                    postFix += myStack.pop();
                    postFix += ' ';
                }

                myStack.push(current);
            }
        }

        while (!myStack.isEmpty()) {
            postFix += myStack.pop();
            postFix += ' ';
        }

        return postFix;
    }

    public static Integer calculate(String post) {
        GenericLinkedStack<Integer> holdingStack = new GenericLinkedStack<>();
        String[] split = post.split(" ");

        for (String s: split) {
            if (isDigit(s)) {
                holdingStack.push(Integer.parseInt(s));
            }
            else {
                Integer bottom = holdingStack.pop();
                Integer top = holdingStack.pop();
                Integer result = operation(bottom, top, s);

                if (result != null) {
                    holdingStack.push(result);
                }
                else {
                    while (!holdingStack.isEmpty()) {
                        holdingStack.pop();
                    }
                    holdingStack.push(null);
                    break;
                }
            }
        }

        return holdingStack.pop();
    }


    public static int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        } else {
            return -1;
        }

    }

    public static Integer operation(Integer bottom, Integer top, String op) {
        switch (op) {
            case "+":
                return top + bottom;
            case "-":
                return top - bottom;
            case "/":
                if (bottom == 0) {
                    return null;
                }
                return top / bottom;
            default:
                return top * bottom;
        }

    }

    public static boolean isDigit(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

}
