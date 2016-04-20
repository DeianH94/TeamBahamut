package states;

import java.io.*;
import java.util.TreeMap;

/**
 * Created by Anton on 20-Apr-16.
 */
public class LoadRanking {

    private int score;
    private String name;
    private String line;
    private TreeMap<Integer, String> rank;

    public LoadRanking() {

        TreeMap<Integer, String> rank = new TreeMap<>();
        int currentPosition = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(
                new File("resources/ranking.txt")));) {
            while ((line = reader.readLine()) != null) {

                int result = Integer.parseInt(line.split(" ")[0]);
                String user = line.split(" ")[1];
                rank.put(currentPosition,result + " " + user);
                currentPosition++;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
