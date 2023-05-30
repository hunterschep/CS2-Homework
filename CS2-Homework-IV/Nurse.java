// Hunter Scheppat
public class Nurse {
    protected boolean[] workable;
    protected int totalAvailable;
    protected boolean workingShift;
    protected int shift;
    protected String name;
    protected int order;

    public Nurse(int totalShifts, int totalAvailable, String canWork, String name, int idx)
    {
        // track if assigned
        this.workingShift = false;
        this.totalAvailable = totalAvailable;
        this.workable = new boolean[totalShifts];
        // true @ an index means that shift is being worked
        this.name = name;
        this.shift = -1;
        this.order = idx;

        // true @ an index means that shift can be worked
        for (int i = 0; i < totalShifts; i++) {
            workable[i] = canWork.contains(String.valueOf(i));
        }
    }
    // 1 if more shifts, 0 if same, -1 if less
    public boolean greaterThan(Nurse n) {
        return this.totalAvailable > n.totalAvailable;
    }

    // get a nurses name
    public int getOrder() {
        return this.order ;
    }

    // see if a nurse can work a shift
    public boolean canWork(int shift) {
        return workable[shift];
    }
    public void assignShift(int shift) {
        this.shift = shift;
    }

    @Override
    public String toString() {
        return (this.name + " " + this.shift);
    }

}
