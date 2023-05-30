// Hunter Scheppat
import java.util.ArrayList;
import java.util.Scanner;

public class Scheduler {
    public static void main(String[] args) {

        // initialize nurses and shifts arrays
        Scanner myScan = new Scanner(System.in);
        int shifts = myScan.nextInt();
        Nurse[] nurses = new Nurse[shifts];
        int[] nurseWorking = new int[shifts];
        ArrayList<Nurse> order = new ArrayList<>();

        for (int i = 0; i < nurses.length; i++) {
            nurseWorking[i] = -1;
        }
        myScan.nextLine();

        int j = 0;
        for (int i = 0; i < shifts; i++) {
            // idx 1 = total shifts they can work
            String[] firstLine = myScan.nextLine().split(" ");
            int total = Integer.parseInt(firstLine[1]);
            String name = firstLine[0];
            // the individual shifts they can work
            String working = myScan.nextLine();
            Nurse hold = new Nurse(shifts, total, working, name, i);
            nurses[j] = hold;
            order.add(hold);
            j++;
        }
        sort(nurses);
        boolean canSchedule = assignNurses(nurses, 0, nurseWorking);

        if (canSchedule) {
            for (int i = 0; i < nurses.length; i++) {
                Nurse current = nurses[i];
                current.assignShift(nurseWorking[i]);
                order.set(current.getOrder(), current);
            }

            for (Nurse n : order) {
                System.out.println(n);
            }
        }
        else {
            System.out.println("impossible");
        }
    }


    // assign the nurses to a shift
    public static boolean assignNurses(Nurse[] nurses, int shift, int[] isNurseWorking) {
        // if n shifts have been assigned, success
        if (shift == nurses.length) {
            return true;
        }
        // get the first nurse
        for (int i = 0; i < nurses.length; i++) {
            Nurse current = nurses[i];

            // if the nurse can work the shift & they are not already working
            if (current.canWork(shift) && isNurseWorking[i] == -1) {
                // work that shift
                isNurseWorking[i] = shift;
                // try to solve
                if (assignNurses(nurses, shift + 1, isNurseWorking)) {
                    return true;
                }
                // backtrack and remove them from the shift if this does not work
                isNurseWorking[i] = -1;
            }
        }
        // if no one can take the shift it is impossible
        return false;
    }


    // sort nurses by least to most shifts
    public static void sort(Nurse[] input) {
        boolean swap = true;
        int length = input.length - 1;

        // bubble sort by pushing up nurses with most availability
        while(swap) {
            swap = false;
            for(int i = 0; i < length; i++) {
                if(input[i].greaterThan(input[i+1])) {
                    Nurse hold = input[i + 1];
                    input[i + 1] = input[i];
                    input[i] = hold;
                    swap = true;
                }
            }
            length--;

        }
        // increases efficiency by 80%+
    }

}
