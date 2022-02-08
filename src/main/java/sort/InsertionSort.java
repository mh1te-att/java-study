package sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author zhaoyc
 * @since 2/8/22
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] array = {1,3,5,4,16,2,13,15};
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort(array);
        System.out.println(Arrays.toString(array));
    }

    public void sort(int[] array) {
        // i 为待插入元素的索引
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i]; // 待插入元素的值
            int j = i - 1; // 已排序区间的元素索引
            while (j >= 0) {
                if (array[j] > tmp) {
                    array[j + 1] = array[j];
                } else {
                    break; // 减少比较次数
                }
                j--;
            }
            array[j + 1] = tmp;
        }
    }
}
