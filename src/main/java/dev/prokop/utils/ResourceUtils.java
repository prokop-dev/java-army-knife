package dev.prokop.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
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
        if (fileName == null) new IllegalArgumentException("Argument cannot be null");
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

    /**
     * Reads given resource file as a string.
     *
     * @param fileName path to the resource file
     * @return the file's contents as List of Strings
     * @throws RuntimeException if read fails for any reason
     */
    public static List<String> getResourceFileAsListOfStrings(String fileName) {
        if (fileName == null) new IllegalArgumentException("Argument cannot be null");
        final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) new RuntimeException("Unable to create InputStream for: " + fileName + ", URL: " + classLoader.getResource(fileName));
            try (InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(isr)) {
                final List<String> list = reader.lines().collect(Collectors.toList());
                return Collections.unmodifiableList(list);
            }
        } catch (IOException ioe) {
            throw new RuntimeException("Unable to read resource: " + fileName, ioe);
        }
    }

}
