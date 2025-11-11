package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Map<String, Set<String>> result = new TreeMap<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))){
            reader.lines()
                    .map(String::toLowerCase)
                    .filter(word -> word.matches("[а-яё]+") && word.length() >= 3)
                    .forEach(word ->{
                        String sorted = word.chars()
                                .sorted()
                                .mapToObj(c -> String.valueOf((char)c))
                                .collect(Collectors.joining());
                        result.computeIfAbsent(sorted, key -> new TreeSet<>()).add(word);
                    });
        }catch (IOException e) {
            throw new RuntimeException("Somethings wrong", e);
        }
        return result.values().stream()
                .filter(group -> group.size() >= 2)
                .collect(Collectors.toList());
        //return null;
    }
}
