# LeetCode刷题记录
本module包含以下所有算法题的答案和解析。

## Medium
[236. Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)
Recursive
[307.Range Sum Query - Mutable](https://leetcode.com/problems/range-sum-query-mutable/) Binary
Indexed Tree (Fenwick Tree)
[1004. Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/) Sliding
Window

## Hard
[629. K Inverse Pairs Array](https://leetcode.com/problems/k-inverse-pairs-array/) dp[i][j] =
dp[i-1][j] + dp[i-1][j-1] +... +dp [i-1][j-i+1]
[778. Swim in Rising Water](https://leetcode.com/problems/swim-in-rising-water/description/) BFS +
Priority Queue
[315. Count of Smaller Numbers After Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self/)
Binary Indexed Tree (Fenwick Tree)
[135. Candy](https://leetcode.com/problems/candy/) Greedy in forward and backward

# Java/Kotlin Code Snippet

### Initialize arrays

**Java**

```java
// initialize with fix length (fill with default value)
int[][]nums=new int[3][3];

// initialize with content
int[][]nums={{1,2},{2,3},{3,4},{1,4},{1,5}};
```

**Kotlin**

```kotlin
// initialize with fix length (fill with default value)
val nums = IntArray(10) { 1 }

// initialize with value, array type will be inferred
val nums = arrayOf(1, 2, 3)

// initialize with primitive value
val nums = intarrayof(1, 2, 3)
val hasValues = booleanArrayOf(true, true, false)
```

### Initialize arrayList

**Java**

```java
// initialize with add() method
List<Integer> nums=new ArrayList<Integer>(){{add(1);}};

// initialize with multiple elements in one line
List<Integer> nums=Arrays.asList(1,2,3);

// initialize with only one element
List<Integer> nums=Collections.singletonList(1);
```

**Kotlin**

```kotlin
// initialize with multiple elements in one line
val nums = arrayListOf(1, 2, 3)

// initialize with no elements
val nums = mutableListOf<Int>()

// initialize with fixed size and default value
val nums = MutableList(nums.size){ 0 }
```


