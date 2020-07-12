package control.files;

import control.commands.Command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.Scanner;

public class Parser {
    private ArrayList<String> deviceNames;
    private ArrayList<String> successArray;

    public ArrayList<String> getSuccessArray() {
        return successArray;
    }

    public ArrayList<String> getDeviceNames() {
        return deviceNames;
    }

    public void readDeviceListFile() {
        deviceNames = new ArrayList<>();
        String path = "c:\\"  + GenerateFolders.getTemp() + "\\" + GenerateFolders.getResultsFolderName() + "\\" + Command.getDevicesList();
        System.out.println(path);
        try (Scanner scanner = new Scanner(new FileReader(path))) {
            int count = 0;
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (count != 0 && !str.isEmpty()) {
                    int index = str.indexOf("\t");
                    String name = str.substring(0, index);
                    deviceNames.add(name);
                }

                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readSuccess() {
        successArray = new ArrayList<>();
        String path = "c:\\" +  GenerateFolders.getTemp() + "\\" + GenerateFolders.getResultsFolderName() + "\\" + Command.getResultFileName();
        try (Scanner scanner = new Scanner(new FileReader(path))) {
            int count = 0;
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                int index = str.indexOf("No such file or directory");
                if (index == -1) {
                    successArray.add("File add to " + deviceNames.get(count));
                } else {
                    successArray.add("No such file or directory");
                }

                count++;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
