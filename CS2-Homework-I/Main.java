/* @author: Hunter Scheppat
   @date: 1/18/2023
   @description: Main contains the main method for the gathering of
   two rationals from the users input and the execution of whatever
   math function they specify

*/

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        // get the numerator and denominator of the first rational
        Scanner myScan = new Scanner(System.in);
        int firstInt = myScan.nextInt();
        int secondInt = myScan.nextInt();

        // create the first rational as an instance of the rational class
        Rational firstRational = new Rational(firstInt, secondInt);

        // get the math operation
        String mathOperation = myScan.next();

        // get the numerator and denominator of the second rational
        int thirdInt = myScan.nextInt();
        int fourthInt = myScan.nextInt();

        // create the second rational as an instance of the rational class
        Rational secondRational = new Rational(thirdInt, fourthInt);

        // call the addition method on the firstRational object
        if (mathOperation.compareTo("+") == 0)
        {
            firstRational.increaseBy(secondRational);
        }
        // negate the rational and then call the addition method (add a negative)
        else if (mathOperation.compareTo("-") == 0)
        {
            secondRational.negate();
            firstRational.increaseBy(secondRational);
        }
        // call the multiply method on the firstRational object
        else if (mathOperation.compareTo("*") == 0)
        {
            firstRational.multiplyBy(secondRational);
        }
        // for division invert the secondRational and then call the multiply method
        else
        {
            secondRational.invert();
            firstRational.multiplyBy(secondRational);
        }

        System.out.println(firstRational);



    }
}

