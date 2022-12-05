package com.cybratech;

import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("input.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            int currentElfCalories = 0;
            String line = br.readLine();
            List<Integer> elfCalories = new LinkedList<>();
            while (line != null) {
                if (!line.isBlank() || !line.isEmpty()) {
                    int calories = Integer.parseInt(line);
                    currentElfCalories += calories;
                } else {
                    elfCalories.add(currentElfCalories);
                    currentElfCalories = 0;
                }
                line = br.readLine();
            }
            System.out.println("Max Elf: " + elfCalories.stream().max(Comparator.naturalOrder()).orElse(0));
            elfCalories.sort(Comparator.naturalOrder());
            int maxThree = 0;
            for (int i = elfCalories.size() - 1; i > elfCalories.size() - 4; i--) {
                System.out.println(elfCalories.get(i));
                maxThree += elfCalories.get(i);
            }
            System.out.println("Maximum 3: " + maxThree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}