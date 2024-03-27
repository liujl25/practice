package com.liujunlong.leetcode.hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串 [困难]
 * https://leetcode.cn/problems/minimum-window-substring/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class Solution_76 {

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaabbbbbcdd", t = "abcdd";
        System.out.println(minWindow(s, t));
    }

    /**
     * 怎么判断 t 中所有元素都在滑动窗口里面？
     *
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        //使用hashMap保存字符出现的次数
        Map<Character, Integer> tMap = new HashMap<>();
        Map<Character, Integer> currMap = new HashMap<>();
        int sLen = s.length(), tLen = t.length();
        for (int i = 0; i < tLen; i++) {//初始化 tMap
            char ch = t.charAt(i);
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
        }
        int left = 0, right = -1;
        int len = sLen, strLeft = -1, strRight = -1;//结果字符串的长度，在s中的索引
        while (right < sLen) {
            right++;
            if (right < sLen && tMap.containsKey(s.charAt(right))) {//该字符在t字符串中，放入当前滑动窗口map中
                currMap.put(s.charAt(right), currMap.getOrDefault(s.charAt(right), 0) + 1);//累加字符出现字数
            }
            while (check(currMap, tMap) && left <= right) {
                if (right - left + 1 < len) {
                    len = right - left + 1;
                    strLeft = left;
                    strRight = right;
                }
                if (tMap.containsKey(s.charAt(left))) {//check通过，重复元素-1
                    currMap.put(s.charAt(left), currMap.getOrDefault(s.charAt(left), 0) - 1);
                }
                ++left;
            }

        }
        return strLeft == -1 ? "" : s.substring(strLeft, strRight);
    }

    public static boolean check(Map<Character, Integer> currMap, Map<Character, Integer> tMap) {
        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            Character key = entry.getKey();
            Integer val = entry.getValue();
            if (currMap.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

    /**
     * 题解
     * https://leetcode.cn/problems/minimum-window-substring/solutions/1503454/by-flix-1kac/?envType=study-plan-v2&envId=top-100-liked
     * https://leetcode.cn/problems/minimum-window-substring/solutions/258513/tong-su-qie-xiang-xi-de-miao-shu-hua-dong-chuang-k/?envType=study-plan-v2&envId=top-100-liked
     */
    class LeetCode_76 {
        public String minWindow(String s, String t) {
            //首先创建的是need数组表示每个字符在t中需要的数量，用ASCII码来保存
            //加入need[76] = 2，表明ASCII码为76的这个字符在目标字符串t中需要两个，如果是负数表明当前字符串在窗口中是多余的，需要过滤掉
            int[] need = new int[128];
            //按照字符串t的内容向need中添加元素
            for (int i = 0; i < t.length(); i++) {
                need[t.charAt(i)]++;
            }
        /*
        l: 滑动窗口左边界
        r: 滑动窗口右边界
        size: 窗口的长度
        count: 当次遍历中还需要几个字符才能够满足包含t中所有字符的条件，最大也就是t的长度
        start: 如果有效更新滑动窗口，记录这个窗口的起始位置，方便后续找子串用
         */
            int l = 0, r = 0, size = Integer.MAX_VALUE, count = t.length(), start = 0;
            //循环条件右边界不超过s的长度
            while (r < s.length()) {
                char c = s.charAt(r);
                //表示t中包含当前遍历到的这个c字符，更新目前所需要的count数大小，应该减少一个
                if (need[c] > 0) {
                    count--;
                }
                //无论这个字符是否包含在t中，need[]数组中对应那个字符的计数都减少1，利用正负区分这个字符是多余的还是有用的
                need[c]--;
                //count==0说明当前的窗口已经满足了包含t所需所有字符的条件
                if (count == 0) {
                    //如果左边界这个字符对应的值在need[]数组中小于0，说明他是一个多余元素，不包含在t内
                    while (l < r && need[s.charAt(l)] < 0) {
                        //在need[]数组中维护更新这个值，增加1
                        need[s.charAt(l)]++;
                        //左边界向右移，过滤掉这个元素
                        l++;
                    }
                    //如果当前的这个窗口值比之前维护的窗口值更小，需要进行更新
                    if (r - l + 1 < size) {
                        //更新窗口值
                        size = r - l + 1;
                        //更新窗口起始位置，方便之后找到这个位置返回结果
                        start = l;
                    }
                    //先将l位置的字符计数重新加1
                    need[s.charAt(l)]++;
                    //重新维护左边界值和当前所需字符的值count
                    l++;
                    count++;
                }
                //右移边界，开始下一次循环
                r++;
            }
            return size == Integer.MAX_VALUE ? "" : s.substring(start, start + size);
        }
    }
}
