package com.spookybox;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> receivedFiles = ExtractLogs.pullFromPhone();
        if(!receivedFiles.isEmpty()){
            System.out.println("Deleting pulled files");
            ExtractLogs.deleteFiles();
        }
        if(true){
            return;
        }
        //PieChart demo = new PieChart("Comparison", "Which operating system are you using?");
        //demo.pack();
        //demo.setVisible(true);

        XYChart chart = new XYChart();
        chart.pack();
        chart.setVisible(true);
    }
}
