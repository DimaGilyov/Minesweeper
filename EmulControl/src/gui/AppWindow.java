package gui;

import control.files.GenerateBat;
import control.files.GenerateFolders;
import control.files.Parser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AppWindow {


    public void run() {
        SwingUtilities.invokeLater(() -> {
            WindowDesigner windowDesigner = new WindowDesigner();
            JFrame jFrame = new JFrame("Emulator Settings");
            jFrame.setSize(296, 230);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            jFrame.setLocation(screenSize.width / 3, screenSize.height / 2);

            File file = new File("icon.jpg");
            try {
                jFrame.setIconImage(ImageIO.read(file));
            } catch (IOException e) {
                e.printStackTrace();
            }

            jFrame.setResizable(false);
            jFrame.setVisible(true);
            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JTabbedPane jTabbedPane = new JTabbedPane();
            jFrame.add(jTabbedPane);
            jTabbedPane.add("Push", windowDesigner.$$$getRootComponent$$$());

            JTextField fromField = windowDesigner.getFromField();
            JTextField toField = windowDesigner.getToField();
            JTextField fileField = windowDesigner.getFileField();
            JCheckBox saveBox = windowDesigner.getSaveCheckBox();
            JButton refreshButton = windowDesigner.getRefreshButton();
            JButton pushButton = windowDesigner.getPushButton();
            JTextArea textArea = windowDesigner.getTextArea1();

            //TODO сделать JSON конфиг
            GenerateBat.setUrlToAdb("D:\\XuanZhi\\LDPlayer");
            GenerateBat.setUrlFrom("Z:/Rooms/PokerBros/production");
            GenerateBat.setUrlTo("data/data/com.kpgame.PokerBros/files/game-remote-asset/src");
            GenerateBat.setFileName("project.jsc2");

            Parser parser = new Parser();
            GenerateFolders.createDir();
            GenerateBat generateBat = new GenerateBat();
            generateBat.generateClearResultsFolderName();

            pushButton.setEnabled(false);

            fromField.setText(GenerateBat.getUrlFrom());
            toField.setText(GenerateBat.getUrlTo());
            fileField.setText(GenerateBat.getFileName());

            saveBox.setSelected(true);
            fromField.setEnabled(false);
            toField.setEnabled(false);
            fileField.setEnabled(false);

            fromField.setDisabledTextColor(Color.GRAY);
            toField.setDisabledTextColor(Color.GRAY);
            fileField.setDisabledTextColor(Color.GRAY);

            saveBox.addActionListener(e -> {
                if (saveBox.isSelected()) {
                    GenerateBat.setUrlFrom(fromField.getText());
                    GenerateBat.setUrlTo(toField.getText());
                    GenerateBat.setFileName(fileField.getText());

                    fromField.setEnabled(false);
                    toField.setEnabled(false);
                    fileField.setEnabled(false);
                } else {
                    fromField.setEnabled(true);
                    toField.setEnabled(true);
                    fileField.setEnabled(true);
                }
            });

            refreshButton.addActionListener(e -> {
                generateBat.generateConnectDevicesBat();

                try {
                    System.out.println("cmd /c start c:\\" + GenerateFolders.getTemp() + "\\" + GenerateFolders.getBatFilesFolderName() + "\\" + GenerateBat.getClearResultsFolderName());
                    Thread.sleep(2000);
                    Runtime.getRuntime().exec("cmd /c start c:\\" + GenerateFolders.getTemp() + "\\" + GenerateFolders.getBatFilesFolderName() + "\\" + GenerateBat.getStartAbdFileName());
                    Thread.sleep(7000);
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }

                parser.readDeviceListFile();
                textArea.setText("");
                ArrayList<String> devicesName = parser.getDeviceNames();
                StringBuilder stringBuilder = new StringBuilder();

                if (devicesName.size() > 0) {
                    pushButton.setEnabled(true);
                    for (String name : devicesName) {
                        stringBuilder.append(name).append("\n");
                    }
                } else {
                    pushButton.setEnabled(false);
                    stringBuilder.append("Devices not found");
                }

                textArea.setText(stringBuilder.toString());
            });

            pushButton.addActionListener(e -> {
                generateBat.generatePushFileBat();
                try {
                    Runtime.getRuntime().exec("cmd /c start c:\\" + GenerateFolders.getTemp() + "\\" + GenerateFolders.getBatFilesFolderName() + "\\" + GenerateBat.getPushFileName());
                    Thread.sleep(10000);
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }

                textArea.setText("");
                parser.readSuccess();
                ArrayList<String> successArray = parser.getSuccessArray();
                StringBuilder stringBuilder = new StringBuilder();
                for (String success : successArray) {
                    stringBuilder.append(success).append("\n");
                }

                textArea.setText(stringBuilder.toString());
            });

            AdbSettingsWindow adbSettingsWindow = new AdbSettingsWindow();
            jTabbedPane.add("Adb", adbSettingsWindow.$$$getRootComponent$$$());
            JTextField adbPathInput = adbSettingsWindow.getAdbPath();
            adbPathInput.setEnabled(false);
            adbPathInput.setDisabledTextColor(Color.GRAY);
            adbPathInput.setText(GenerateBat.getUrlToAdb());
            JCheckBox saveAdbPathCheckBox = adbSettingsWindow.getSaveAdbPathCheckBox();
            saveAdbPathCheckBox.setSelected(true);

            saveAdbPathCheckBox.addActionListener(e -> {
                if (saveAdbPathCheckBox.isSelected()){
                    adbPathInput.setEnabled(false);
                    GenerateBat.setUrlToAdb(adbPathInput.getText());
                }else {
                    adbPathInput.setEnabled(true);
                }
            });

        });
    }
}
