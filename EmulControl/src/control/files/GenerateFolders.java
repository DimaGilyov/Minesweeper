package control.files;

import java.io.File;

public class GenerateFolders {
    private static final String temp = "AdbTemp";
    private static final String batFilesFolderName = "BatFiles";
    private static final String resultsFolderName = "Results";
    private static File resultsFolder;

    public static File getResultsFolder() {
        return resultsFolder;
    }

    public static String getTemp() {
        return temp;
    }

    public static String getBatFilesFolderName() {
        return batFilesFolderName;
    }

    public static String getResultsFolderName() {
        return resultsFolderName;
    }

    public static void createDir() {
        resultsFolder = new File("c:\\" + temp + "\\" + resultsFolderName);
        boolean isTempFolderCreated = new File("c:\\" + temp).mkdir();
        boolean isBatFilesFolderCreated = new File("c:\\" + temp + "\\" + batFilesFolderName).mkdir();
        boolean isResultsFolderCreate = resultsFolder.mkdir();
    }
}
