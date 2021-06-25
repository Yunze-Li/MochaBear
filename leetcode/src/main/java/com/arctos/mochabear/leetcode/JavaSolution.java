package com.arctos.mochabear.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class JavaSolution {
    public int[] findRedundantConnection(int[][] edges) {

        HashMap<Integer, Set<Integer>> edgeMap = new HashMap<>();
        for (int[] edge : edges) {
            if (edgeMap.containsKey(edge[0])) {
                edgeMap.get(edge[0]).add(edge[1]);
            } else {
                Set<Integer> newSet = new HashSet<>();
                newSet.add(edge[1]);
                edgeMap.put(edge[0], newSet);
            }
            if (edgeMap.containsKey(edge[1])) {
                edgeMap.get(edge[1]).add(edge[0]);
            } else {
                Set<Integer> newSet = new HashSet<>();
                newSet.add(edge[0]);
                edgeMap.put(edge[1], newSet);
            }
        }

        boolean needCheck = true;
        while (needCheck) {
            for (int key : edgeMap.keySet()) {
                if (edgeMap.get(key).size() == 1) {
                    int neighbor = edgeMap.get(key).iterator().next();
                    edgeMap.get(neighbor).remove(key);
                    edgeMap.remove(key);
                    needCheck = true;
                    break;
                }
                needCheck = false;
            }
        }

        for (int i = edges.length - 1; i >= 0; i--) {
            if (edgeMap.containsKey(edges[i][0]) && edgeMap.containsKey(edges[i][1])) {
                return edges[i];
            }
        }
        return edges[0];
    }

}
