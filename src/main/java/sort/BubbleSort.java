package sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author zhaoyc
 * @since 2/7/22
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {1,3,5,4,16,2,13,15};
        BubbleSort bubbleSort = new BubbleSort();
//        bubbleSort.sort(array);
//        bubbleSort.sort_plus(array);
        bubbleSort.sort_final(array);
    }

    /**
     * 未经任何优化的冒泡排序
     *
     * @param array
     *
     * @author zhaoyc
     * @since 2/7/22
     */
    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0 ; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
                System.out.println("第" + j + "次比较");
            }
            System.out.println("第" + i + "轮排序" + Arrays.toString(array));
        }
    }

    /**
     * 优化过的冒泡排序，相同以后不会重复比较
     *
     * @param array
     *
     * @author zhaoyc
     * @since 2/7/22
     */
    public void sort_plus(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    flag = true;
                }
                System.out.println("第" + j + "次比较");
            }
            System.out.println("第" + i + "轮排序" + Arrays.toString(array));
            if (!flag) {
                break;
            }
        }
    }

    /**
     * 冒泡排序最终版，减少比较次数
     *
     * @param array
     *
     * @author zhaoyc
     * @since 2/7/22
     */
    public void sort_final(int[] array) {
        int n = array.length - 1;
        int index = 0;
        while (true) {
            int last = 0;
            for (int i = 0; i < n; i++) {
                if (array[i] > array[i + 1]) {
                    int tmp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tmp;
                    last = i;
                }
                System.out.println("第" + i + "次比较");
            }
            n = last;
            if (n == 0) {
                break;
            }
            System.out.println("第" + ++index + "轮排序" + Arrays.toString(array));
        }
    }

}
