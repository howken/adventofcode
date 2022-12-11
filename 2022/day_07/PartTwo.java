import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartTwo {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BufferedReader reader;
        Integer sum = 0;
        HashMap<String, Integer> folders = new HashMap<String, Integer>();
        String currentFolder = "";

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            Pattern dirPattern = Pattern.compile("^dir (.*)$");   // the pattern to search for
            Pattern cmdPattern = Pattern.compile("^\\$ (.*)$");   // the pattern to search for
            Pattern filePattern = Pattern.compile("^(\\d+) (.*)$");   // the pattern to search for
            Pattern moveUpPattern = Pattern.compile("^cd (\\w*)$");
            Pattern moveDownPattern = Pattern.compile("cd (.*)");
            
            while (line != null) {
                //System.out.println(line);
                Matcher dirMatch = dirPattern.matcher(line);
                Matcher cmdMatch = cmdPattern.matcher(line);
                Matcher fileMatch = filePattern.matcher(line);
                if (cmdMatch.find()) {
                    String command = cmdMatch.group(1);
                    // Match command
                    //System.out.println("command: "+command);
                    // if command is cd .. update current folder to one below.
                    // if command is cd {folder} update currentFolder to the new subfolder. and add 0 sum folder to folders.
                    Matcher moveUpMatch = moveUpPattern.matcher(command);
                    Matcher moveDownMatch = moveDownPattern.matcher(command);
                    if (moveUpMatch.find()) {
                            // We move into a subdir
                            currentFolder = currentFolder+moveUpMatch.group(1)+"/";
                            //System.out.println("New folder (up): "+currentFolder);
                            folders.put(currentFolder, 0);

                    } else if (moveDownMatch.find()) {
                        if (Objects.equals(moveDownMatch.group(1), new String("/"))) {
                            // We are at first dir :+1:
                            currentFolder = moveDownMatch.group(1);
                            folders.put(currentFolder, 0);
                            //System.out.println("New folder (base): "+currentFolder);
                        } else {
                            // Split line on / and remove last folder from currentFolder.
                            List<String> dirs = new ArrayList(Arrays.asList(currentFolder.split("\\/")));
                            //System.out.println("dir.size = "+dirs.size()+" O:"+dirs.get(0)+" 1:"+dirs.get(1));
                            if (dirs.size() == 1) {
                                // We can't go further down than /
                                currentFolder = "/";
                                //System.out.println("New folder (can't down): "+currentFolder);
                            } else {
                                // As we move down we add the total of the previous dir to the one we are moving to.
                                Integer tmpSize = folders.get(currentFolder);
                                dirs.remove(dirs.size()-1);
                                StringJoiner joiner = new StringJoiner("/");
                                dirs.forEach(item -> joiner.add(item));
                                currentFolder = joiner.toString()+"/";
                                //System.out.println("New folder (down): "+currentFolder);
                                folders.put(currentFolder, folders.get(currentFolder)+tmpSize);
                            }
                        }
                    } else {
                        // we do not care about the ls command
                        //System.out.println("ERROR: command - "+command);
                    }
                } else if (dirMatch.find()) {
                    //String folder = dirMatch.group(1);
                    // Match listing of dir in current dir.
                    //System.out.println("folder: "+folder);

                    // we dont care about these as we handle them when we enter them.
                } else if (fileMatch.find()) {
                    Integer fileSize = Integer.parseInt(fileMatch.group(1));
                    //String fileName = fileMatch.group(2);
                    // Match file with size
                    //System.out.println("file: "+fileName+" with size: "+fileSize);
                    // Add file size to folders sum.
                    folders.put(currentFolder, folders.get(currentFolder)+fileSize);
                }
                //System.out.println("Current dir: "+currentFolder);
                //System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // We need to add the total of the last dir to the every dir benethe it.
        List<String> lastDirs = new ArrayList(Arrays.asList(currentFolder.split("\\/")));
        for(int i = lastDirs.size()-1; i > 0; i--) {
            int tmp = folders.get(currentFolder);
            lastDirs.remove(i);
            StringJoiner j = new StringJoiner("/");
            lastDirs.forEach(item -> j.add(item));
            currentFolder = j.toString()+"/";
            folders.put(currentFolder, folders.get(currentFolder)+tmp);
        }

        Integer atleast = 30000000-(70000000-folders.get("/"));
        Integer currentSmalestSize = folders.get("/");
        //System.out.println("root dir is "+folders.get("/")+" and we need to free: "+atleast);
        for(String key : folders.keySet()) {
            if (folders.get(key) >= atleast && folders.get(key) < currentSmalestSize) {
                //System.out.println("Folder "+key+" has size "+folders.get(key));
                currentSmalestSize=folders.get(key);
            }
                //System.out.println("Folder "+key+" has size "+folders.get(key));
        }
        sum=currentSmalestSize;
        System.out.println("Result:  " + Integer.toString(sum));
    }
}
