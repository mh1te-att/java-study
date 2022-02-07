import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PlayGround {

    public static int longestSubsequence(int[] arr, int difference) {
        Set<Integer> res = new HashSet<>();
        int ans = 1;
        for (int i = 0; i < arr.length; i++) {
            int left = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                int right = arr[j];
                if (left + difference == right) {
                    res.add(left);
                    res.add(right);
                    left = right;
                }
            }
            if (res.size() > ans) {
                ans = res.size();
            }
            res.clear();
        }
        return ans;
    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = {1,5,7,8,5,3,4,2,1};
        int difference = -2;
        int i = longestSubsequence(arr, difference);
        System.out.println(i);
        ArrayList<Object> objects = new ArrayList<>();
    }

    static class Solution {
        public static int[] twoSum(int[] nums, int target) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) {
                    return new int[] {i, map.get(target - nums[i])};
                }
                map.put(nums[i], i);
            }
            throw new IllegalArgumentException("no solution");
        }

        public static void main(String[] args) {
            int[] nums = new int[] {2,7,9,15};
            int target = 9;
            int[] ints = twoSum(nums, target);
            System.out.println(Arrays.toString(ints));
        }
    }
}

