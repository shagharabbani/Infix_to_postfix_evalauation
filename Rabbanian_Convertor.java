/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project3CSC4101;

/**
 *
 * @author srabba2
 */

import java.util.Stack;

public class Convertor {

static int Prec(char ch)
    {
        switch (ch)
        {
        case '+':
        case '-':
            return 1;
     
        case '*':
        case '/':
            return 2;
        }
        return -1;
    }
   
    public static String convertToPostfix(String infix){
       
        String postfix = new String("");
        Stack<Character> stack = new Stack<>();
        boolean isPrevDigit = false;    
        for (int i = 0; i<infix.length(); ++i)
        {
            char c = infix.charAt(i);
            if (Character.isDigit(c)){
                if (isPrevDigit)
                {
                    postfix += c;
                }
                else
                {
                    postfix += ' ';
                    postfix += c;
                }
               
                isPrevDigit=true;
            }
            else if (c == ' '){
                isPrevDigit=false;
            }
            else if (c == '('){
                stack.push(c);
                isPrevDigit=false;
            }
            else if (c == ')')
            {
                
                while (!stack.isEmpty() &&
                        stack.peek() != '('){
                    postfix += ' ';
                    postfix += stack.pop();
                }                  
                stack.pop();    // Pops character '('
                
                    
                isPrevDigit=false;
            }
            else // c is an operator (+-*/)
            {
                while (!stack.isEmpty() && Prec(c)
                         <= Prec(stack.peek())){
                    postfix += ' ';
                    postfix += stack.pop();
            }
                stack.push(c);
                isPrevDigit=false;
            }
        }
        while (!stack.isEmpty()){
            if(stack.peek() == '(')
                return "Invalid Expression";
            postfix += ' ';
            postfix += stack.pop();
         }
        return postfix;
    }
   
    public static float evaluatePostfix (String postfix){
        boolean isPrevDigit = false;
        String num = "";
        Stack<Float> numStk = new Stack<>();
        for (int i = 0; i<postfix.length(); ++i)
        {
            char c = postfix.charAt(i);
            if (Character.isDigit(c))
            {
                num += c;              
                isPrevDigit=true;
            }
            else if (c == ' ')
            {
                if (isPrevDigit)
                {
                    numStk.push(Float.parseFloat(num));
                    num = "";
                    isPrevDigit=false;
                }
            }
            else    // c is an operator (+-*/)
            {
                if (numStk.size()>=2)
                {
                    float y = numStk.pop();
                    float x = numStk.pop();
                    float d = SingleOperation(x,y,c);
                    numStk.push(d);
                }
            }
        }
        return numStk.pop();
    }

public static float SingleOperation(float x, float y, Character operator)
{
    switch (operator)
    {
        case '+':
            return x+y;
        case '-':
            return x-y;
        case '*':
            return x*y;
        case '/':
            return x/y;
        default:
            System.out.println("Invalid Expression");
            return -1;
    }
}
   
}