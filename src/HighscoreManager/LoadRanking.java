package HighscoreManager;

import java.io.*;
import java.util.TreeMap;

public class LoadRanking {
    public static TreeMap<Integer, String> loadRanking() {
        String line;
        TreeMap<Integer, String> rank = new TreeMap<>();
        int currentPosition = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(
                new File("resources/ranking.txt.txt")))) {
            while ((line = reader.readLine()) != null) {

                int result = Integer.parseInt(line.split(" ")[1]);
                String user = line.split(" ")[2];
                rank.put(currentPosition,result + " " + user);
                currentPosition++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rank;
    }
}
