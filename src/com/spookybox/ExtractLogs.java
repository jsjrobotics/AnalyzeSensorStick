package com.spookybox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExtractLogs {
    private static final String ADB_PATH = "/sdcard/Android/data/com.spookyrobotics.sensorstick/files/";
    private static final String ADB_LIST_FILES = "adb shell cd "+ADB_PATH+"; ls; exit;";
    private static final String ADB_DELETE_FILES = "adb shell cd "+ADB_PATH+"; rm output.sensors.*; exit;";
    private static final String ADB_PULL = "adb pull ";

    public static List<String> pullFromPhone(){
        ArrayList<String> receivedFiles = new ArrayList<String>();
        try {
            Process listFilesProcess = Runtime.getRuntime().exec(ADB_LIST_FILES);
            listFilesProcess.waitFor();
            BufferedReader result = new BufferedReader(new InputStreamReader(listFilesProcess.getInputStream()));

            StringBuilder output = new StringBuilder();
            String line = "";
            while ((line = result.readLine())!= null) {
                Process pullFiles = Runtime.getRuntime().exec(ADB_PULL + ADB_PATH+ line );
                pullFiles.waitFor();
                System.out.println("Pulled " + line);
                output.append(line + "\n");
                receivedFiles.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
        return receivedFiles;
    }

    public static void deleteFiles(){
        try {
            Process deleteFiles = Runtime.getRuntime().exec(ADB_DELETE_FILES);
            deleteFiles.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
