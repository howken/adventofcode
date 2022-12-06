import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

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
                Boolean result = true;
                // check substring of line until check is true
                for(int i=0;i<line.length()-3;i++) {
                    String subString = line.substring(i, (i+4));
                    //System.out.println("Substring: "+subString);
                    HashSet <Character> uniqueCharSet = new HashSet();
                    Boolean foundNoMatch = true;
                    for(int j=0; j < subString.length() ; j++) {
                        result=uniqueCharSet.add(subString.charAt(j));
                        if (result==false) {
                            //System.out.println("String: "+subString);
                            foundNoMatch = false;
                            break;
                        }
                    }
                    if (foundNoMatch) {
                        System.out.println("right String: "+subString);
                        System.out.println("Start char at: "+(i+4));
                        break;
                    }
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
