package states;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Ranking {

    public static void ranking(String name, int score) {
        String line;
        TreeMap<Integer, String> rank = new TreeMap<>();

        int first = Integer.parseInt(rank.get(1).split(" ")[0]);
        int last = Integer.parseInt(rank.get(3).split(" ")[0]);
        try (BufferedReader reader = new BufferedReader(new FileReader(
                new File("resources/ranking.txt")))) {
            while ((line = reader.readLine()) != null) {

                int result = Integer.parseInt(line.split(" ")[0]);
                String user = line.split(" ")[1];

                if (result <= last) {
                    break;
                }

                if (result > last && result < first) {
                    int replace = Integer.parseInt(rank.get(2).split(" ")[0]);
                    String replaceName = rank.get(2).split(" ")[1];
                    rank.remove(3);
                    rank.put(2, result + " " + user);
                    rank.put(3, replace + " " + replaceName);
                } else {
                    int replaceFirst = Integer.parseInt(rank.get(1).split(" ")[0]);
                    String replaceFirstName = rank.get(1).split(" ")[1];
                    int replaceSec = Integer.parseInt(rank.get(2).split(" ")[0]);
                    String replaceSecondName = rank.get(2).split(" ")[1];
                    rank.remove(3);
                    rank.put(1, result + " " + user);
                    rank.put(2,replaceFirst + " " + replaceFirstName);
                    rank.put(3, replaceSec + " " + replaceSecondName);
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    "resources/ranking.txt"));

            for (Map.Entry<Integer, String> data : rank.entrySet()) {
                writer.write(data.getKey() + " " + data.getValue());
            }
            writer.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
