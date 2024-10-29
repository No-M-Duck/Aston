package output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class CollectionWriter<T> {
    protected String delimiter = ";";
    protected void writeFile(String type, List<String> output) {
        int fileNumber = 1;
        String fileType = ".csv";
        File file;
        String fileName;
        File directory = new File("output");
        if (!directory.exists()) {
            directory.mkdir();
        }
        do {
            fileName = type + "_" + fileNumber + fileType;
            file = new File(directory, fileName);
            fileNumber++;
        }
        while (file.exists());
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (String line : output) {
                fileWriter.write(line + "\n");
            }
            System.out.println("File " + fileName + " written to " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void toFile(List<T> collection);
}
