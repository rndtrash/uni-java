import java.util.HashMap;

public class Main {
    public static final String sequence = "test test тест     тест ыыыы фдор ылаыфлфрафл дав л";

    public static void main(String[] args) {
        HashMap<String, Integer> occurances = new HashMap<>();

        int i = 0;
        do {
            while (sequence.charAt(i) == ' ' && i < sequence.length())
                i++;

            if (i < sequence.length()) {
                int j = i + 1;
                while (j < sequence.length() && sequence.charAt(j) != ' ')
                    j++;

                String substring = sequence.substring(i, j);
                occurances.put(substring, occurances.getOrDefault(substring, 0) + 1);

                i = j;
            }
        } while (i < sequence.length());

        for (var entry : occurances.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}