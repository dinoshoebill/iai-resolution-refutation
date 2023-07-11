package ui;

import ui.cooking.CookingMethod;
import ui.resolution.ResolutionMethod;

public class Solution {

    public static void main(String[] args) {
        try {

            Resolution struct = new Resolution();

            int i = 0;
            do {
                switch (args[i]) {
                    case "--m" -> struct.method = args[++i];
                    case "--f" -> struct.pathToResolutionFile = args[++i];
                    case "--i" -> struct.pathToInput = args[++i];
                }

                i++;
            } while (i < args.length);

            switch (struct.method.toLowerCase()) {
                case "resolution" -> {
                    Reader.loadResolutionFile(struct);
                    Utils.conclusion(ResolutionMethod.resolution(struct), struct);
                }
                case "cooking" -> {
                    Reader.loadCookingFile(struct);
                    CookingMethod.cooking(struct);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
