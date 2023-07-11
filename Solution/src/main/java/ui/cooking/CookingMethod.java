package ui.cooking;

import ui.Resolution;
import ui.Utils;
import ui.resolution.ResolutionMethod;

import java.util.*;

public class CookingMethod {

    public static void cooking(Resolution struct) {
        for(String command : struct.commands) {

            String substring = command.substring(0, command.length() - 2);

            switch (command.substring(command.length() - 1)) {
                case "?" -> {
                    List<String> input = new ArrayList<>(struct.ogInput);
                    String sosString = command.split(" \s?")[0].toLowerCase();
                    input.add(sosString);
                    Utils.setup(struct, input);
                    Utils.conclusion(ResolutionMethod.resolution(struct), struct);
                }
                case "+" -> {
                    struct.ogInput.add(substring);
                }
                case "-" -> {
                    struct.ogInput.remove(substring);
                }
            }
        }
    }
}
