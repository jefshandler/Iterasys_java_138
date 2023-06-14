package util;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCsvFile {

    public static Object[][] csvData(String csvFile) {
        String line = "";
        String cvsSplitBy = ",";
        List<Object[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(cvsSplitBy);
                Object[] row = new Object[values.length];
                for (int i = 0; i < values.length; i++) {
                    row[i] = Double.parseDouble(values[i]);
                }
                data.add(row);
            }
        } catch (IOException e) {
            // Adicione tratamento de erro adequado aqui
            e.printStackTrace();
        }

        return data.toArray(new Object[data.size()][]);
    }
        }

