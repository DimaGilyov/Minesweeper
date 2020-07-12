package control.commands;

import control.files.GenerateFolders;

import java.util.ArrayList;

public class Command {
    private static final String devicesList = "devicesList.txt";
    private static final String resultFileName = "success.txt";

    public static String getDevicesList() {
        return devicesList;
    }

    public static String getResultFileName() {
        return resultFileName;
    }

    private static String getStartServerAdb(String urlToAdb) {
        String disk = urlToAdb.substring(0, 1);
        return disk + ":\n" +
                "CD " + urlToAdb + "\n" +
                "CALL adb.exe\n" +
                "ADB start-server\n";
    }

    public static String getCheckEmulatorConnection(String urlToAdb) {
        String adbConnection = getStartServerAdb(urlToAdb);
        return adbConnection +
                "ADB devices > c:\\" + GenerateFolders.getTemp() + "\\" + GenerateFolders.getResultsFolderName() + "\\" + devicesList + "\n" +
                "ADB kill-server\n" +
                "EXIT";
    }

    public static String getInstallFileInEmulator(String urlToAdb, ArrayList<String> deviceNames, String urlFrom, String fileName, String urlTo) {
        String adbConnection = getStartServerAdb(urlToAdb);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(adbConnection);

        int count = 0;
        for (String deviceName : deviceNames) {
            String rec = " > ";
            if (count != 0) {
                rec = " >> ";
            }
            stringBuilder.append("ADB -s ").append(deviceName).append(" push ").append(urlFrom).append("\\").append(fileName).append(" /").append(urlTo).append("/").append("\n");
            stringBuilder.append("ADB -s ").append(deviceName).append(" shell find ").append(urlTo).append("/").append(fileName).append(rec).append("c:\\")
                    .append(GenerateFolders.getTemp()).append("\\").append(GenerateFolders.getResultsFolderName()).append("\\").append(resultFileName).append("\n");

            count++;
        }

        stringBuilder.append("ADB kill-server\n").append("EXIT\n");
        return stringBuilder.toString();
    }

    public static String getClearFolder(String url) {
        return "set folder="+ url + "\n" +
        "CD \"%folder%\" && rd /s /q \"%folder%\" 2>NUL\n" +
                "EXIT";
    }


}
