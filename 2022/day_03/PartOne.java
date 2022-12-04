import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PartOne {

    private static char CommonChar(String first, String second) {
        int end = first.length();

        for (int i = 0; i < end ; i++) {
            for (int j = 0; j < end ; j++) {
                if (first.charAt(i) == second.charAt(j)) {
                    return first.charAt(i);
                }
            }
        }
        return '*';
    }
    private static int CharValue(char prio) {
        int value=prio;
        if (value < 97) { // ASCII value of a
           return (value - 38);
        } else {
            return (value - 96);
        }
    }
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BufferedReader reader;
        Integer prioritySum = 0;

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            
            while (line != null) {
                // Split line in half (array of char's to two arrays)
                Integer mid = line.length() / 2;
                String[] compartment = {line.substring(0,mid),line.substring(mid)};
                // find common letter in arrays
                char priorityChar = CommonChar(compartment[0],compartment[1]);
                if (priorityChar == '*') {
                    System.out.println("ERR: got no matching char from: "+compartment[0]+" and "+compartment[1]);
                }
                // find char value and addit to total.
                prioritySum = prioritySum + CharValue(priorityChar);

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Result:  " + Integer.toString(prioritySum));
    }
}
