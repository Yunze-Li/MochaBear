package com.arctos.mochabear.leetcode;

import java.util.HashSet;
import java.util.Set;

public class JavaSolution {
    public int numMatchingSubseq(String s, String[] words) {
        if (words.length == 0) {
            return 0;
        }

        int count = 0;
        Set<String> isSub = new HashSet<>();
        Set<String> notSub = new HashSet<>();
        for (String word : words) {
            if (isSub.contains(word)) {
                count += 1;
                continue;
            } else if (notSub.contains(word)) {
                continue;
            }

            boolean result = isSubseq(s, word);
            if (result) {
                count += 1;
                isSub.add(word);
            } else {
                notSub.add(word);
            }
        }
        return count;
    }

    private boolean isSubseq(String full, String current) {
        int currentIndex = 0;
        for (int i = 0; i < full.length(); i++) {
            if (full.charAt(i) == current.charAt(currentIndex)) {
                currentIndex++;
            }
            if (currentIndex == current.length()) {
                return true;
            }
        }
        return false;
    }
}
