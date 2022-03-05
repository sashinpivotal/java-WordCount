package com.example.javawordcount;

import java.sql.Array;
import java.util.*;

import static com.example.javawordcount.Constants.unText;
import static com.example.javawordcount.Constants.unTextTest;

public class Main {

    public static final int ONE = 1;

    public static void main(String[] args) {

        String unTextWithNoPunctuation
                = unText.replaceAll("[[\\.\\?\\!\\,\\;\\:\\{\\}\\(\\)\\']]", "");
        String[] words = unTextWithNoPunctuation.split(" +");

        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {
            word = word.toUpperCase();
            boolean containsKey = map.containsKey(word);
            if (containsKey) {
                map.replace(word, map.get(word) + ONE);
            } else {
                map.put(word, ONE);
            }
        }

        ArrayList<Integer> values = new ArrayList<>(map.values());
        Collections.sort(values, Collections.reverseOrder());

        for (int i = 0; i < 50; i++) {
            int value = values.get(i);
            String key = "";
            boolean containsValue = map.containsValue(value);
            for(Map.Entry<String, Integer> entry: map.entrySet()) {
                if(entry.getValue() == value) {
                    key = entry.getKey();
                    map.remove(key);
                    break;
                }
            }
            System.out.println(
                    "COMMON WORD <" + key + ">" +
                    " occurs " + value + " times");
        }

    }
}

