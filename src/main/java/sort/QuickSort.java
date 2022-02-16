package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 快速排序
 *
 * @author zhaoyc
 * @since 2/9/22
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = {1,3,5,4,16,2,13,15};
        QuickSort quickSort = new QuickSort();
        quickSort.sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    public void sort(int[] array, int l, int h) {
        if (l >= h) {
            return;
        }
        int p = partition(array, l, h);
        sort(array, l, p - 1);
        sort(array, p + 1, h);
    }

    public int partition(int[] array, int l, int h) {
        int pv = array[h];
        int i = l;
        for (int j = l; j < h; j++) {
            if (array[j] < pv) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
            }
        }
        int tmp = array[i];
        array[i] = array[h];
        array[h] = tmp;
        return i;
    }
}
