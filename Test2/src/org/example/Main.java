package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String circleFilePath = args[0];
        String pointsFilePath = args[1];

        FileInputStream streamCircle = null;
        FileInputStream streamPoints = null;
        try {
            streamCircle = new FileInputStream(circleFilePath);
            streamPoints = new FileInputStream(pointsFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String strLine;
        List<String> linesCircle = new ArrayList<>();
        List<String> linesPoints = new ArrayList<>();
        try (BufferedReader readerCircle = new BufferedReader(new InputStreamReader(streamCircle));
             BufferedReader readerPoints = new BufferedReader(new InputStreamReader(streamPoints))) {
            while ((strLine = readerCircle.readLine()) != null) {
                String lastWord = strLine;
                linesCircle.add(lastWord);
            }
            while ((strLine = readerPoints.readLine()) != null) {
                String lastWord = strLine;
                linesPoints.add(lastWord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        float x0 = Float.parseFloat(linesCircle.get(0).split(" ")[0]);
        float y0 = Float.parseFloat(linesCircle.get(0).split(" ")[1]);
        float r = Float.parseFloat(linesCircle.get(1));

        for (String point : linesPoints) {
            float xP = Float.parseFloat(point.split(" ")[0]);
            float yP = Float.parseFloat(point.split(" ")[1]);
            double v = Math.pow((x0 - xP), 2) + Math.pow((y0 - yP), 2);
            if (v == Math.pow(r, 2)) {
                System.out.println(0);
            } else if (v < Math.pow(r, 2)) {
                System.out.println(1);
            } else {
                System.out.println(2);
            }
        }

    }
}