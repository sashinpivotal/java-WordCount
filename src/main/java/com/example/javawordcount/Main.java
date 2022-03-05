package com.example.javawordcount;

import java.sql.Array;
import java.util.*;

import static com.example.javawordcount.Constants.unText;
import static com.example.javawordcount.Constants.unTextTest;

public class Main {

    public static final int ONE = 1;
    public static final String REGEX = "[[\\.\\?\\!\\,\\;\\:\\{\\}\\(\\)\\']]";

    public static void main(String[] args) {

        String unTextWithNoPunctuation
                = unText.replaceAll(REGEX, "");
        String[] words = unTextWithNoPunctuation.split(" +");

        Map<String, Integer> map = constructMapFromWords(words);
        ArrayList<Integer> values = createSortedArrayListFromValuesOfHashMap(map);

        printTheWordsInHighestOccurencesFirstOrder(map, values);

    }

    private static void printTheWordsInHighestOccurencesFirstOrder(Map<String, Integer> map, ArrayList<Integer> values) {
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

    private static ArrayList<Integer> createSortedArrayListFromValuesOfHashMap(Map<String, Integer> map) {
        ArrayList<Integer> values = new ArrayList<>(map.values());
        Collections.sort(values, Collections.reverseOrder());
        return values;
    }

    private static Map<String, Integer> constructMapFromWords(String[] words) {
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
        return map;
    }
}

