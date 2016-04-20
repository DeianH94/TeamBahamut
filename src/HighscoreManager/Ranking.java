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

                if (newScore >= last && newScore <= first) {
                    int replace = Integer.parseInt(rank.get(2).split(" ")[0]);
                    String replaceName = rank.get(2).split(" ")[1];

                    rank.replace(2, newScore + " " + userName);
                    rank.replace(3, replace + " " + replaceName);
                } else {
                    int replaceFirst = Integer.parseInt(rank.get(1).split(" ")[0]);
                    String replaceFirstName = rank.get(1).split(" ")[1];
                    int replaceSec = Integer.parseInt(rank.get(2).split(" ")[0]);
                    String replaceSecondName = rank.get(2).split(" ")[1];

                    rank.replace(1, newScore + " " + userName);
                    rank.replace(2,replaceFirst + " " + replaceFirstName);
                    rank.replace(3, replaceSec + " " + replaceSecondName);
                }

            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    "resources/ranking.txt.txt"));

            for (Map.Entry<Integer, String> data : rank.entrySet()) {
                writer.write(data.getKey() + " " + data.getValue());
                writer.write(System.lineSeparator());
            }
            writer.flush();

    }
}
