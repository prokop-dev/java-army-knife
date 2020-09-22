package dev.prokop.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public final class ResourceUtils {

    private ResourceUtils() {}

    /**
     * Reads given resource file as a string.
     *
     * @param fileName path to the resource file
     * @return the file's contents
     * @throws RuntimeException if read fails for any reason
     */
    public static String getResourceFileAsString(String fileName) {
        final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) new RuntimeException("Unable to create InputStream for: " + fileName + ", URL: " + classLoader.getResource(fileName));
            try (InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        } catch (IOException ioe) {
            throw new RuntimeException("Unable to read resource: " + fileName, ioe);
        }
    }

}
