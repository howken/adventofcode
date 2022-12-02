import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BufferedReader reader;
        Integer sum = 0;
        Integer sumNew = 0;
        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            
            while (line != null) {
                switch (line) {
                    case "A X": sum = sum + 4; break; // sten - sten 3 + 1
                    case "A Y": sum = sum + 8; break; // sten - påse 6 + 2
                    case "A Z": sum = sum + 3; break; // sten - sax 0 + 3
                    case "B X": sum = sum + 1; break; // påse - sten 0 + 1
                    case "B Y": sum = sum + 5; break; // påse - påse 3 + 2
                    case "B Z": sum = sum + 9; break; // påse - sax 6 + 3
                    case "C X": sum = sum + 7; break; // sax - sten 6 + 1
                    case "C Y": sum = sum + 2; break; // sax - påse 0 + 2
                    case "C Z": sum = sum + 6; break; // sax - sax 3 + 3
                }
                switch (line) {
                    case "A X": sumNew = sumNew + 3; break; // sten (loose) sax 0 + 3
                    case "A Y": sumNew = sumNew + 4; break; // sten (draw) sten 3 + 1
                    case "A Z": sumNew = sumNew + 8; break; // sten (win) påse 6 + 2
                    case "B X": sumNew = sumNew + 1; break; // påse (loose) sten 0 + 1
                    case "B Y": sumNew = sumNew + 5; break; // påse (draw) påse 3 + 2
                    case "B Z": sumNew = sumNew + 9; break; // påse (win) sax 6 + 3
                    case "C X": sumNew = sumNew + 2; break; // sax (loose) påse 0 + 2
                    case "C Y": sumNew = sumNew + 6; break; // sax (draw) sax 3 + 3
                    case "C Z": sumNew = sumNew + 7; break; // sax (win) sten 6 + 1
                }


                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Result:  " + Integer.toString(sum));
        System.out.println("New Result:  " + Integer.toString(sumNew));
    }
}
