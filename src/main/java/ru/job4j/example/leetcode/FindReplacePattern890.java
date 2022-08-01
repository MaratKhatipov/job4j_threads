package ru.job4j.example.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindReplacePattern890 {
    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for (String s : words) {
            if (match(s, pattern)) {
                result.add(s);
            }
        }
        return result;
    }

    public static boolean match(String word, String pattern) {
        Map<Character, Character> wordMap = new HashMap<>();
        Map<Character, Character> patternMap = new HashMap<>();

        for (int i = 0; i < word.length(); ++i) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);
            if (!wordMap.containsKey(w)) {
                wordMap.put(w, p);
            }
            if (!patternMap.containsKey(p)) {
                patternMap.put(p, w);
            }
            if (wordMap.get(w) != p || patternMap.get(p) != w) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] word = {"abc", "deq", "mee", "aqq", "dkd", "ccc"};
        String pattern = "mee";
        String testWord = "abc";

        for (int i = 0; i < testWord.length(); i++) {
                System.out.println("WORD - " + testWord.indexOf(testWord.charAt(i)) + " PATTERN - " + pattern.indexOf(pattern.charAt(i)));
        }
    }
}
