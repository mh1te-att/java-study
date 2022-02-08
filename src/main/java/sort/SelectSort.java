package sort;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author zhaoyc
 * @since 2/8/22
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] array = {1,3,5,4,16,2,13,15};
        SelectSort selectSort = new SelectSort();
        selectSort.sort(array);
    }

    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // i 代表每轮排序选择最小的元素要交换到的目标索引
            int min = i; // min为每轮最小元素的索引
            for (int j = min + 1; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
            }
            if (min != i) {
                int tmp = array[min];
                array[min] = array[i];
                array[i] = tmp;
            }
            System.out.println("第" + i + "轮排序" + Arrays.toString(array));
        }
    }
}
