/* @author: Hunter Scheppat
   @date: 1/18/2023
   @description: class Rational takes a numerator and denominator of a rational
   and contains methods to do different math to them

*/

public class Rational
{
    private int top;
    private int bottom;
    public Rational(int top, int bottom)
    {
        this.top = top;
        this.bottom = bottom;

    }

    // add two rationals using cross multiplication
    public void increaseBy(Rational other)
    {
        this.top = (this.top * other.bottom) + (this.bottom * other.top);
        this.bottom *= other.bottom;
        this.reduce();
    }

    // multiply two rationals
    public void multiplyBy(Rational other)
    {
        this.top *= other.top;
        this.bottom *= other.bottom;
        this.reduce();
    }

    public void negate()
    {
        this.top *= -1;
    }

    public void invert()
    {
        int hold = this.top;
        this.top = this.bottom;
        this.bottom = hold;

    }

    // reduce a rational by finding the greatest common factor and dividing the top and bottom by it
    public void reduce()
    {
        int gcf = greatestDivisor(this.top, this.bottom);

        this.top = this.top / gcf;
        this.bottom = this.bottom / gcf;

    }

    // find the greatest common divisor using the eucledian algorithm
    public int greatestDivisor(int a, int b)
    {
        if (b == 0)
        {
            if (a < 0)
            {
                return a * -1;
            }
            else
            {
                return a;
            }
        }
        else {
            return greatestDivisor(b, a%b);
        }
    }

    // toString override for printing
    @Override
    public String toString()
    {

        return top + "/" + bottom;
    }
}

