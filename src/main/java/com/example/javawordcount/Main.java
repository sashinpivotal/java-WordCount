package com.example.javawordcount;

import java.util.*;

import static com.example.javawordcount.Constants.unText;

public class Main {

    public static final int ONE = 1;
    public static final String REGEX = "[[\\.\\?\\!\\,\\;\\:\\{\\}\\(\\)\\']]";

    public static void main(String[] args) {

        String[] words = extractWordsFromText();
        Map<String, Integer> map = constructMapFromWords(words);
        ArrayList<Integer> values = createSortedArrayListFromValuesOfHashMap(map);
        printWordsInHighestOccurencesFirstOrder(map, values);

    }

    private static String[] extractWordsFromText() {
        String unTextWithNoPunctuation
                = unText.replaceAll(REGEX, "");
        String[] words = unTextWithNoPunctuation.split(" +");
        return words;
    }

    private static void printWordsInHighestOccurencesFirstOrder(Map<String, Integer> map, ArrayList<Integer> values) {
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

