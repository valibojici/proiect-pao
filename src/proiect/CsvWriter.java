package proiect;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class CsvWriter {
    private static CsvWriter instance;

    private CsvWriter() {
    }

    public static CsvWriter getInstance() {
        if (instance == null) {
            instance = new CsvWriter();
        }
        return instance;
    }

    public void writeCsvFile(ArrayList<String[]> data, String path) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (String[] row : data) {
            String s = String.join(",", row) + "\n";
            writer.write(s);
        }
        writer.close();
    }

    public void appendCsvFile(String[] data, String path) throws Exception{
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
        String d = String.join(",", data);
        writer.write(d + "\n");
        writer.close();
    }
}
