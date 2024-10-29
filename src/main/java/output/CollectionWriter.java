package output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class CollectionWriter<T> {
    protected String delimiter = ";";
    String fileType = ".csv";

    protected void writeCollection(String type, List<String> output) throws IOException {
        File directory = getDirectory();
        File file = getFile(directory, type, true);
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (String line : output) {
                fileWriter.write(line + "\n");
            }
            System.out.println("Collection saved to " + file.getAbsolutePath());
        }
    }

    protected void writeElement(String type, String data) throws IOException {
        File directory = getDirectory();
        File file = getFile(directory, type, false);
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(data + "\n");
            System.out.println("Element saved to " + file.getAbsolutePath());
        }
    }

    protected File getFile(File directory, String type, boolean newFile) {
        File file;
        String fileName;
        if (newFile) {
            int fileNumber = 1;
            do {
                fileName = type + "_" + fileNumber + fileType;
                fileNumber++;
                file = new File(directory, fileName);
            }
            while (file.exists());
        } else {
            fileName = type + fileType;
            file = new File(directory, fileName);
        }
        return file;
    }

    protected File getDirectory() {
        File directory = new File("output");
        if (!directory.exists()) {
            directory.mkdir();
        }
        return directory;
    }

    public abstract void toFile(List<T> collection) throws IOException;
}
