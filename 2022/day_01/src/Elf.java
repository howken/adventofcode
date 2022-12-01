
// Elves class describes an elf.
public class Elf implements Comparable<Elf> {
    private int totalCalories = 0;

    public int getTotalCalories() {
        return totalCalories;
    }

    public int addCalories(int calories) {
        totalCalories = totalCalories + calories;
        return totalCalories;
    }

    @Override
    public int compareTo(Elf e) {
        return Integer.compare(totalCalories, e.getTotalCalories());
    }
}