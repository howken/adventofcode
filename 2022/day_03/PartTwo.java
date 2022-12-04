import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PartTwo {

    private static char CommonChar(List<String> group) {
        String first = group.get(0);
        String second = group.get(1);
        String third = group.get(2);
        for (int i = 0; i < first.length() ; i++) {
            for (int j = 0; j < second.length() ; j++) {
                for (int k = 0; k < third.length(); k++) {
                    if (first.charAt(i) == second.charAt(j) && first.charAt(i) == third.charAt(k)) {
                        return first.charAt(i);
                    }
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
            //String[] group = {};
            List<String> group = new ArrayList<>();
            
            while (line != null) {
                // add line to list
                group.add(line);
                // Do we have a group of three lines?
                if (group.size() > 2) {
                    // find common letter in 3 strings
                    char priorityChar = CommonChar(group);
                    if (priorityChar == '*') {
                        System.out.println("ERR: got no matching char from: "+group.get(0)+" and "+group.get(1)+" and "+group.get(2));
                    }
                    // find char value and addit to total.
                    prioritySum = prioritySum + CharValue(priorityChar);
                    // remove all elements from list
                    group.removeAll(group);
                }
                
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Result:  " + Integer.toString(prioritySum));
    }
}
