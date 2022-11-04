package classes.utilities;

import java.nio.file.Paths;

public final class PathHandler {

    private static final String file = String.valueOf(Paths.get("src", "nhlstenden", "solarsystem"));

    /**
     * Gets the file path.
     * @param path
     * @return
     */
    public static String getFile(String... path) {
        return String.valueOf(Paths.get(file, path));
    }

}