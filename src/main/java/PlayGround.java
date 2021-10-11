import linkedList.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PlayGround {
    public static void main(String[] args) throws InterruptedException {
//        Thread.sleep(1000);
//        List<byte[]> list = new ArrayList<>();
//        int index = 1;
//        for (int i = 0; i < 10000; i++) {
//            Thread.sleep(1000);
//            byte[] bytes = new byte[1024 * 1024];
//            list.add(bytes);
//            System.out.println("success " + index);
//            index += 1;
//        }
//        System.out.println(list);
        String a = "a";
        String b = "b";
        String c = "a" + "b"; // 编译时会自动优化中间的+号，让c=ab
        String d = a + b; // 两个String对象相加会自动使用new关键字调构造方法
        String e = "ab";
        String f = "a" + new String("b");
        System.out.println(f == c);
        System.out.println(f == d);
        System.out.println(f == e);
        System.out.println(c == d);          //false
        System.out.println(f == c);          //false
        System.out.println(c == e);            //true
        System.out.println(e == f);            //false
        System.out.println(e == f.intern());    //true
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

