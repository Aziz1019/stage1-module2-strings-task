package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */

    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> substrings = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            if (delimiters.contains(String.valueOf(c))) {
                if (sb.length() > 0) {
                    substrings.add(sb.toString());
                    sb = new StringBuilder();
                }
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0) {
            substrings.add(sb.toString());
        }
        return substrings;
    }

    public static void main(String[] args) {
        StringSplitter splitter = new StringSplitter();
        List<String> result = splitter.splitByDelimiters("qw3e1rt4yu2i3opa1sd1fg2hj4kl", List.of("1", "2", "3"));
        result.removeIf(s -> s.equals(" "));
        System.out.println(result);
    }
}
