package com.ownutils.leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {

    public int[] twoSum1(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++)
            for (int j = i + 1; j < nums.length; j++)
                if (nums[i] + nums[j] == target) {
                    int[] result = new int[]{i, j};
                    return result;
                }

        return null;
    }

    public int[] twoSum2(int[] nums, int target) {


        HashMap map = new HashMap();
        map.get("");
        for (int i = 0; i < nums.length; i++) {
            System.out.println(i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 17;
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.twoSum1(nums, target)));
    }
}
