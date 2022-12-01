import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BufferedReader reader;
        String empty = "";
        Integer elf = 0;
        List<Elf> elves = new ArrayList<>();
        elves.add(elf, new Elf());

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            
            while (line != null) {
                if (line.equals(empty)) {
                    elf = elf + 1;
                    elves.add(elf, new Elf());
                } else {
                    elves.get(elf).addCalories(Integer.parseInt(line));
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(elves);
        Collections.reverse(elves);
        Integer elfOneCalories = elves.get(0).getTotalCalories();

        System.out.println("First Elf calories: "+Integer.toString(elfOneCalories)+" Winner!");
        
        Integer topThreeTotalCalories = 0;
        for(int i=0;i<3;i++) {
            topThreeTotalCalories = topThreeTotalCalories + elves.get(i).getTotalCalories();
        }

        System.out.println("Total calories of top 3: "+Integer.toString(topThreeTotalCalories));
    }
}
