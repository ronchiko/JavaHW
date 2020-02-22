package expiramental.utils;

import java.io.*;
import java.util.ArrayList;

public class FileUtilities {
    /**
     * Read a file
     * @param path
     * @return
     * @throws IOException
     */
    public static String read(String path) throws IOException {
        File file = new File(path);
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String string;
            while ((string = reader.readLine()) != null) {
                sb.append(string);
            }
            return sb.toString();
        }
    }

    public static String[] readLines(String path) throws IOException {
        File file = new File(path);
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            ArrayList<String> strings = new ArrayList<>();
            String string;
            while ((string = reader.readLine()) != null) {
                strings.add(string);
            }
            String[] lines = new String[strings.size()];
            for (int i = 0; i < lines.length; i++) {
                lines[i] = strings.get(i);
            }
            return lines;
        }
    }
}
