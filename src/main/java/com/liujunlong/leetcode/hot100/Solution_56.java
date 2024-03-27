package com.liujunlong.leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 普通数组
 * 56. 合并区间
 * https://leetcode.cn/problems/merge-intervals/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class Solution_56 {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {15, 18}, {8, 10},};
        int[][] merge = merge(intervals);
    }

    /**
     * 题解
     * https://leetcode.cn/problems/merge-intervals/solutions/203562/he-bing-qu-jian-by-leetcode-solution/?envType=study-plan-v2&envId=top-100-liked
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        //排序，按照区间的左值递增
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int L = intervals[i][0], R = intervals[i][1];
            //排序后 L 是递增的，只用比较当前的L和merged中的R
            //当前 L > merged.R，则当前区间和merged中的不重叠，所以往merged中添加，继续遍历数组进行判断
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }


}
