package HighscoreManager;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Ranking {
    public static void ranking(TreeMap<Integer,String> rank, int newScore, String userName) throws IOException {

        int first = Integer.parseInt(rank.get(1).split(" ")[0]);
        int last = Integer.parseInt(rank.get(3).split(" ")[0]);

                if (newScore <= last) {
                    return;
                }

                if (newScore > last && newScore < first) {
                    int replace = Integer.parseInt(rank.get(2).split(" ")[0]);
                    String replaceName = rank.get(2).split(" ")[1];
                    rank.remove(3);
                    rank.put(1, rank.get(1).split(" ")[0] + " " + rank.get(1).split(" ")[1] + "\n");
                    rank.put(2, newScore + " " + userName + "\n");
                    rank.put(3, replace + " " + replaceName + "\n");
                } else {
                    int replaceFirst = Integer.parseInt(rank.get(1).split(" ")[0]);
                    String replaceFirstName = rank.get(1).split(" ")[1];
                    int replaceSec = Integer.parseInt(rank.get(2).split(" ")[0]);
                    String replaceSecondName = rank.get(2).split(" ")[1];
                    rank.remove(3);
                    rank.put(1, newScore + " " + userName + "\n");
                    rank.put(2,replaceFirst + " " + replaceFirstName + "\n");
                    rank.put(3, replaceSec + " " + replaceSecondName + "\n");
                }

            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    "resources/ranking.txt.txt"));

            for (Map.Entry<Integer, String> data : rank.entrySet()) {
                writer.write(data.getKey() + " " + data.getValue());
            }
            writer.flush();

    }
}
