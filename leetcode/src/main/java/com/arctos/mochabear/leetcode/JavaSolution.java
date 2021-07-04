package com.arctos.mochabear.leetcode;

public class JavaSolution {
    public int maxSumSubmatrix(int[][] matrix, int k) {

        int row = matrix.length;
        int column = matrix[0].length;
        int[][] sums = new int[row + 1][column + 1];

        for (int i = 0; i < row; i++) {
            int current = 0;
            for (int j = 0; j < column; j++) {
                current += matrix[i][j];
                sums[i + 1][j + 1] = sums[i][j + 1] + current;
                if (current == k || matrix[i][j] == k || sums[i + 1][j + 1] == k) {
                    return k;
                }
            }
        }

        int diff = Integer.MAX_VALUE;
        int result = 0;
        for (int startRow = 0; startRow < sums.length; startRow++) {
            for (int startColumn = 0; startColumn < sums[0].length; startColumn++) {
                for (int endRow = startRow + 1; endRow < sums.length; endRow++) {
                    for (int endColumn = startColumn + 1; endColumn < sums[0].length; endColumn++) {
                        int rectangleSum = sums[endRow][endColumn] + sums[startRow][startColumn] - sums[startRow][endColumn] - sums[endRow][startColumn];
                        if (rectangleSum == k) return k;
                        if (rectangleSum < k && k - rectangleSum < diff) {
                            result = rectangleSum;
                            diff = k - rectangleSum;
                        }
                    }
                }
            }
        }
        return result;
    }
}
