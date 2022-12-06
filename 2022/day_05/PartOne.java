import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PartOne {

    private static class Pile {
        private List<String> pile = new ArrayList<>();

        public void addPileToPile(List<String> newCrates) {
            pile = Stream.concat(pile.stream(), newCrates.stream()).toList();
        }
        public void addCrateToPile(String crate) {
            pile.add(crate);
        }
        public List<String> getCrates(Integer n) {
            List<String> crates = pile.subList(pile.size()-n, pile.size());
            List<String> reverseCrates = new ArrayList<>();
            for(int c = (crates.size()-1) ; c >= 0 ; c--) {
                reverseCrates.add((crates.size()-c-1), crates.get(c));
            }
            pile = pile.subList(0, pile.size()-n);
            return reverseCrates;
        }
        public String topCrate() {
            return pile.get(pile.size()-1);
        }
        /*public List<String> getPile() {
            return pile;
        }*/
        public String getPileString() {
            return String.join(",",pile);
        }
    }
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BufferedReader reader;
        Integer sum = 0;
        List<String> pileLines = new ArrayList<String>();
        // List<Elf> elves = new ArrayList<>();
        //Pile[] piles;
        List<Pile> piles = new ArrayList<>();
        piles.add(0, new Pile()); // empty pile for padding

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            //ArrayList<List<String>> listOfPiles = new ArrayList<>();
            //List<Pile> piles = new ArrayList<>();

            while (line != null) {
                
                if (line.matches("^$")) {
                    System.out.println("Empty line!");
                    // Process existing stacks, start with determaning how many stacks there are.
                    // get last line in pileLines as it contains the number of piles.
                    String numberOfPiles = pileLines.get(pileLines.size()-1).replace("\\s+", " ");
                    String[] pileList = numberOfPiles.split(" ");
                    System.out.println("Number of Piles: "+pileList[pileList.length-1]+" Am I stupid: "+pileList.length);
                    System.out.println("Height of highest pile: "+(pileLines.size()-1));
                    // create array of empty piles
                    Integer nPiles = Integer.parseInt(pileList[pileList.length-1]);
                    //piles = new Pile[nPiles];
                    
                    // Put crates in their respective piles.
                    //Integer depth = 1;
                    for (int i=(pileLines.size()-2) ; i >= 0 ; i--) {
                        System.out.println(pileLines.get(i));
                        // for each pile starting from the bottom.
                        for (int j=0 ; j < Integer.parseInt(pileList[pileList.length-1]) ; j++) {
                            if (pileLines.get(i).substring((1+j+(j*3)),(2+j+(j*3))).matches("^\s$")) {
                                // there is no create to add to the pile
                                System.out.println("Crate: _");
                            } else {
                                // add crate to pile.
                                String crate = pileLines.get(i).substring((1+j+(j*3)),(2+j+(j*3)));
                                System.out.println("Crate: "+crate);
                                // Check if we are on the bottom line and then add a Pile for each crate on the line.
                                if (i==(pileLines.size()-2)) {
                                    piles.add(j+1, new Pile());
                                }
                                piles.get(j+1).addCrateToPile(crate);
                                //piles[j].addCrateToPile(pileLines.get(i).substring((1+j+(j*3)),(2+j+(j*3))));
                            }
                        }
                        //depth++;
                    }
                } else if (line.matches("^move.*$")) {
                    // Move creates as described on the line.
                    // E.G. move 6 from 9 to 3
                    // split line to get values to use.
                    String[] lineParts = line.split(" ");
                    Integer sourcePile = Integer.parseInt(lineParts[3]);
                    Integer destinationPile = Integer.parseInt(lineParts[5]);
                    Integer nCrates = Integer.parseInt(lineParts[1]);
                    System.out.println("We will move "+nCrates+" crates from pile "+sourcePile+" to "+destinationPile);
                    System.out.println("Piles before move:");
                    System.out.println("pile "+sourcePile+": "+piles.get(sourcePile).getPileString());
                    System.out.println("pile "+destinationPile+": "+piles.get(destinationPile).getPileString());
                    piles.get(destinationPile).addPileToPile(
                        piles.get(sourcePile).getCrates(nCrates)
                    );
                    System.out.println("Piles after move:");
                    System.out.println("pile "+sourcePile+": "+piles.get(sourcePile).getPileString());
                    System.out.println("pile "+destinationPile+": "+piles.get(destinationPile).getPileString());

                } else {
                    // line is not empty and has no move so it should be a record of the stacks.
                    // Add the line to a temp list for later processing.
                    pileLines.add(line);
                }
                System.out.println(line);
                System.out.println(" ");
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Test data in piles
        //System.out.println("First pile: "+piles.get(1).getPileString());
        for(int p=1;p<piles.size();p++) {
            System.out.print(piles.get(p).topCrate());
        }
        System.out.println(" Result:  " + Integer.toString(sum));
    }
}
