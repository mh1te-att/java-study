package sort;

/**
 *
 * @Title: sort.BubbleSort
 * @Description: 冒泡排序
 * @Author: zhaoyc
 * @Date: 2020/2/18 12:33
 *
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {63, 4, 24, 1, 3, 15};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(array);
    }

    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        showArray(array);
    }

    public void showArray(int[] array) {
        for (int i : array) {
            System.out.print(" > " + i);
        }
    }
}
