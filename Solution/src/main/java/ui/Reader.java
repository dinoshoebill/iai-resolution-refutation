package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

    static void loadResolutionFile(Resolution struct) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(struct.pathToResolutionFile));

        List<String> fileLines = new ArrayList<>();

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.startsWith("#"))
                continue;

            fileLines.add(line.toLowerCase());
        }

        Utils.setup(struct, fileLines);
    }

    static void loadCookingFile(Resolution struct) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(struct.pathToInput));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if(line.startsWith("#"))
                continue;

            struct.commands.add(line.toLowerCase());
        }

        scanner = new Scanner(new File(struct.pathToResolutionFile));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if(line.startsWith("#"))
                continue;

            struct.ogInput.add(line.toLowerCase());
        }
    }
}
