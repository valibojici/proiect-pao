package proiect;

import java.io.*;
import java.util.ArrayList;

public class CsvReader {
    private static CsvReader instance;

    private CsvReader() {
    }

    public static CsvReader getInstance() {
        if (instance == null) {
            instance = new CsvReader();
        }
        return instance;
    }

    public ArrayList<String[]> readCsvFile(String path) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        ArrayList<String[]> rows = new ArrayList<>();
        String row;
        while ((row = reader.readLine()) != null) {
            row = row.strip();
            if (row.equals("")) {
                continue;
            }
            rows.add(row.split(","));
        }
        return rows;
    }
}
