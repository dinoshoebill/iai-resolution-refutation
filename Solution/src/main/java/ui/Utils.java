package ui;

import java.util.*;

public class Utils {
    public static void conclusion(boolean ok, Resolution struct) {
        if(ok)
            System.out.println("[CONCLUSION]: " + struct.conclusionElement + " is true");
        else
            System.out.println("[CONCLUSION]: " + struct.conclusionElement + " is unknown");
    }

    public static String makeConclusionElement(List<String> element) {

        Collections.sort(element);

        StringBuilder sb = new StringBuilder();

        for(int k = 0; k < element.size(); k++) {
            sb.append(element.get(k));

            if(k != element.size() - 1) {
                sb.append(" v ");
            }
        }

        return new String(sb);
    }

    public static void setup(Resolution struct, List<String> input) {

        struct.clauses.clear();
        struct.sos.clear();

        String conclusionElement = "";

        for(int i = 0; i < input.size(); i++) {
            String line = input.get(i);

            String[] lineSplit = line.toLowerCase().split(" v ");

            if(i == input.size() - 1) {

                List<String> last = new ArrayList<>(Arrays.asList(lineSplit));

                makeConclusionElement(last);

                conclusionElement = makeConclusionElement(last);

                for(int j = 0; j < lineSplit.length; j++) {

                    if(lineSplit[j].startsWith("~")) {
                        lineSplit[j] = lineSplit[j].substring(1);
                    } else {
                        lineSplit[j] = "~" + lineSplit[j];
                    }

                    struct.sos.add(new TreeSet<>(Collections.singletonList(lineSplit[j])));
                }
            } else {
                Collections.addAll(struct.clauses, new TreeSet<>(Arrays.asList(lineSplit)));
            }
        }

        for(TreeSet<String> element : struct.clauses) {
            struct.mapper.put(element, new ArrayList<>());
        }

        for(TreeSet<String> element : struct.sos) {
            struct.mapper.put(element, new ArrayList<>());
        }

        struct.conclusionElement = conclusionElement;
    }
}
