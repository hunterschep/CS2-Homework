/* @author: Hunter Scheppat
   @date: 2/3/2023
   @description: class Time creates time objects that hold their attributes
   and allows math and reduction to be done on them
*/

import java.util.Scanner;

class Time {
    // private variables for different time values
    private int seconds;
    private int minutes;
    private int hours;
    private int days;

    // constructor that sets initial attributes
    public Time(int seconds, int minutes, int hours, int days) {
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;
        this.days = days;
    }

    // subtract one time from another
    public void subtract(Time other) {
        // enlarge both for easier math
        this.enlarge();
        other.enlarge();

        // subtract by seconds
        this.seconds -= other.seconds;

        // reduce
        this.reduce();
    }

    // add one time to another
    public void add(Time other) {

        // enlarge both for easier math
        this.enlarge();
        other.enlarge();

        // add seconds
        this.seconds += other.seconds;

        // reduce
        this.reduce();
    }

    public void reduce() {

        // reduce by dividing and leaving remainder
        this.minutes = this.seconds / 60;
        this.seconds = this.seconds % 60;

        this.hours = this.minutes / 60;
        this.minutes = this.minutes % 60;

        this.days = this.hours / 24;
        this.hours = this.hours % 24;


    }

    public void enlarge() {

        // enlarge times by converting everything to seconds
        this.seconds += this.days * 24 * 60 * 60;
        this.seconds += this.hours * 60 * 60;
        this.seconds += this.minutes * 60;

        this.days = 0; this.hours = 0; this.minutes = 0;

    }

    @Override
    public String toString() {
        return this.seconds + " seconds " + this.minutes + " minutes " + this.hours + " hours " + this.days + " days";
    }

    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);

        // get attributes for time one
        int seconds = myScan.nextInt();
        int minutes = myScan.nextInt();
        int hours = myScan.nextInt();
        int days = myScan.nextInt();

        // create first object
        Time firstTime = new Time(seconds, minutes, hours, days);
        String symbol = myScan.next();

        // get attributes for time two
        seconds = myScan.nextInt();
        minutes = myScan.nextInt();
        hours = myScan.nextInt();
        days = myScan.nextInt();

        // create second object
        Time secondTime = new Time(seconds, minutes, hours, days);

        // do cool stuff
        if (symbol.equals("+")) {
            firstTime.add(secondTime);
        }
        else {
            firstTime.subtract(secondTime);
        }

        System.out.println(firstTime);
    }
}


