package util;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class JSONReader {
    public static Object[][] readData(String jsonFile) {
        Gson gson = new Gson();
        Object[][] data = null;

        try {
            String jsonData = new String(Files.readAllBytes(Paths.get(jsonFile)));
            data = gson.fromJson(jsonData, Object[][].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
