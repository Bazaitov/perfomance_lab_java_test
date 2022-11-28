package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.example.dto.Test;
import org.example.dto.Tests;
import org.example.dto.Value;
import org.example.dto.Values;

import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class  Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Аргументов должно быть 2");
            return;
        }
        try (FileReader fileReader = new FileReader(args[0]);
             FileReader fileReader1 = new FileReader(args[1]);
             Writer writer = Files.newBufferedWriter(Paths.get("report.json"))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Tests tests = gson.fromJson(fileReader, Tests.class);
            Values values = gson.fromJson(fileReader1, Values.class);
            Map<Integer, String> map = new HashMap<>();
            for (int i = 0; i < values.getValues().size(); i++) {
                map.put(values.getValues().get(i).getId(), values.getValues().get(i).getValue());
            }
            putIn(map, tests.getTests());
            gson.toJson(tests, writer);
        } catch (JsonIOException | JsonSyntaxException | NullPointerException | IOException e) {
            System.out.println("Некорректные входные данные?\n" + e);
        }
    }

    static void putIn(Map<Integer, String> map, List<Test> testList) {
        for (Test test : testList) {
            if (map.containsKey(test.getId()))
                test.setValue(map.get(test.getId()));
            if (test.getValues() != null)
                putIn(map, test.getValues());
        }
    }
}
