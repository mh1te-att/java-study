package sort;

/**
 * @Title: SelectSort
 * @Description: 直接选择排序
 * @Author: zhaoyc
 * @Date: 2020/2/18 14:06
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] array = {63, 4, 24, 1, 3, 15};
        SelectSort selectSort = new SelectSort();
        selectSort.sort(array);
    }

    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int index = 0;
            for (int j = 1; j <= array.length - i; j++) {
                if (array[j] > array[index]) {
                    index = j;
                }
            }
            int tmp = array[array.length - i];
            array[array.length - i] = array[index];
            array[index] = tmp;
        }
        showArray(array);
    }

    public void showArray(int[] array) {
        for (int i : array) {
            System.out.print(" > " + i);
        }
    }
}
