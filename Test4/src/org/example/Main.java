package org.example;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePatch = args[0];
        FileInputStream streamFile = null;
        try {
            streamFile = new FileInputStream(filePatch);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String strLine;
        List<Integer> linesNums = new ArrayList<>();
        try (BufferedReader readerFile = new BufferedReader(new InputStreamReader(streamFile))) {
            while ((strLine = readerFile.readLine()) != null) {
                String lastWord = strLine;
                linesNums.add(Integer.parseInt(lastWord));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(linesNums);
        Integer median = linesNums.get(linesNums.size() / 2);
        int sum = 0;
        for (Integer i : linesNums) {
            sum += Math.abs(i - median);
        }
        System.out.println(sum);
    }
}