import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PartOne {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BufferedReader reader;
        Integer sum = 0;

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            
            while (line != null) {
                // split line to different Elves and their start and end.
                String[] ElvesSection = line.split(",");
                String[] firstElf = ElvesSection[0].split("-");
                String[] secondElf = ElvesSection[1].split("-");
                Integer[] firstElfSection = {Integer.parseInt(firstElf[0]), Integer.parseInt(firstElf[1])};
                Integer[] secondElfSection = {Integer.parseInt(secondElf[0]), Integer.parseInt(secondElf[1])};

                // Check of Elves sections overlap.
                // if first elf start between second elf start and stop and
                // first elf end between second elf start and stop.
                if (((firstElfSection[0] >= secondElfSection[0] && firstElfSection[0] <= secondElfSection[1])  
                    && (firstElfSection[1] >= secondElfSection[0] && firstElfSection[1] <= secondElfSection[1]))
                    || ((secondElfSection[0] >= firstElfSection[0] && secondElfSection[0] <= firstElfSection[1]) 
                    && (secondElfSection[1] >= firstElfSection[0] && secondElfSection[1] <= firstElfSection[1]))) {
                    // add found overlap
                    sum++;
                }

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Result:  " + Integer.toString(sum));
    }
}
