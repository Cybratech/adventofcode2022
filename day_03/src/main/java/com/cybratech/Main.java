package com.cybratech;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("input.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            String line = br.readLine();
            int priority = 0;
            int groupPriority = 0;
            int groupCounter = 0;
            List<List<Character>> currentThreeGroups = new ArrayList<>();
            while (line != null) {
                if (!line.isBlank() || !line.isEmpty()) {
                    List<Character> allCharacter = line.chars().mapToObj(c -> (char) c).toList();
                    currentThreeGroups.add(allCharacter);
                    groupCounter+=1;
                    List<Character> firstHalf = allCharacter.subList(0, allCharacter.size() / 2);
                    List<Character> secondHalf = allCharacter.subList(allCharacter.size() / 2, allCharacter.size());
                    List<Character> intersection = firstHalf.stream().filter(secondHalf::contains).toList();
                    priority += getPriority(intersection.get(0));
                }
                if(groupCounter == 3){
                    Optional<Character> intersection = currentThreeGroups.get(0).stream().filter(e->currentThreeGroups.get(1).contains(e) && currentThreeGroups.get(2).contains(e)).findFirst();
                    if(intersection.isPresent()){
                        groupPriority+=getPriority(intersection.get());
                    }
                    else{
                        System.out.println("Too many group matches");
                    }
                    currentThreeGroups.clear();
                    groupCounter = 0;
                }
                line = br.readLine();
            }
            System.out.println("Priority: " + priority);
            System.out.println("Priority Groups: " + groupPriority);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getPriority(char input) {
        char smallA = 'a';
        char largeA = 'A';
        if ((int) input >= 97) {
            return (int) input - (int) smallA + 1;
        } else {
            return (int) input - (int) largeA + 26 + 1;
        }
    }
}