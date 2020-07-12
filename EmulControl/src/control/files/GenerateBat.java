package control.files;

import control.commands.Command;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;

public class GenerateBat {
    private static final String startAbdFileName = "startAdb.bat";
    private static final String pushFileName = "pushFile.bat";
    private static final String clearResultsFolderName = "clearResultsFolder.bat";

    private static String urlToAdb;

    private static String deviceName;
    private static String urlFrom;
    private static String urlTo;
    private static String fileName;

    public static String getClearResultsFolderName() {
        return clearResultsFolderName;
    }

    public static String getPushFileName() {
        return pushFileName;
    }

    public static String getStartAbdFileName() {
        return startAbdFileName;
    }

    public static void setUrlToAdb(String urlToAdb) {
        GenerateBat.urlToAdb = urlToAdb;
    }

    public static String getUrlToAdb() {
        return urlToAdb;
    }

    public static String getDeviceName() {
        return deviceName;
    }

    public static void setDeviceName(String deviceName) {
        GenerateBat.deviceName = deviceName;
    }

    public static String getUrlFrom() {
        return urlFrom;
    }

    public static void setUrlFrom(String urlFrom) {
        GenerateBat.urlFrom = urlFrom;
    }

    public static String getUrlTo() {
        return urlTo;
    }

    public static void setUrlTo(String urlTo) {
        GenerateBat.urlTo = urlTo;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        GenerateBat.fileName = fileName;
    }

    public void generateConnectDevicesBat() {
        try (Writer writer = new FileWriter(new File("c:\\" + GenerateFolders.getTemp() + "\\" + GenerateFolders.getBatFilesFolderName() + "\\" + startAbdFileName))) {
            writer.write(Command.getCheckEmulatorConnection(getUrlToAdb()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void generatePushFileBat() {
        Parser parser = new Parser();
        parser.readDeviceListFile();
        try (Writer writer = new FileWriter(new File("c:\\" + GenerateFolders.getTemp() + "\\" + GenerateFolders.getBatFilesFolderName() + "\\" + pushFileName))) {
            String command = Command.getInstallFileInEmulator(getUrlToAdb(), parser.getDeviceNames(), getUrlFrom(), getFileName(), getUrlTo());
            writer.write(command);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void generateClearResultsFolderName() {
        try (Writer writer = new FileWriter(new File("c:\\" + GenerateFolders.getTemp() + "\\" + GenerateFolders.getBatFilesFolderName() + "\\" + clearResultsFolderName))) {
            String url = "c:\\" + GenerateFolders.getTemp() + "\\" + GenerateFolders.getResultsFolderName();
            String command = Command.getClearFolder(url);
            writer.write(command);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
