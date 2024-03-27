package com.liujunlong.leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * https://leetcode.cn/problems/find-all-anagrams-in-a-string/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class Solution_438 {
    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";
        findAnagrams(s, p).forEach(System.out::println);
    }

    /**
     * my solution
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return new ArrayList<Integer>();
        }
        int[] pArr = new int[26];//保存字母出现的次数，数组作为滑动窗口
        int[] sArr = new int[26];

        //初始化
        for (int i = 0; i < pLen; i++) {
            pArr[p.charAt(i) - 'a']++;//p数组
            sArr[s.charAt(i) - 'a']++;//s字符串首个匹配字符串
        }
        if (Arrays.equals(sArr, pArr)) {
            list.add(0);//首个匹配成功
        }

        int i = 0;
        while (i + pLen < sLen) {
            sArr[s.charAt(i) - 'a']--;//滑动窗口右移1位
            sArr[s.charAt(i + pLen) - 'a']++;
            if (Arrays.equals(sArr, pArr)) {
                list.add(i + 1);//右移一位后匹配成功，所以坐标为i+1
            }
            i++;
        }
        return list;
    }


}
