package com.arctos.mochabear.leetcode;

import org.junit.Test;

public class JavaSolutionTest {

    @Test
    public void test() {
        JavaSolution javaSolution = new JavaSolution();
        // initialized array with content
        int[][] graph = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        javaSolution.findRedundantConnection(graph);

    }
}
