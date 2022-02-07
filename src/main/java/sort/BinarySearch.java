package sort;

/**
 * 二分查找
 *
 * @author zhaoyc
 * @since 2/7/22
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] array = {1,5,6,8,9,10,12,14};
        BinarySearch binarySearch = new BinarySearch();
        int sort = binarySearch.sort(array, 2);
        System.out.println(sort);
    }

    public int sort(int[] array, int target) {
        int left = 0, right = array.length - 1, mid;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

}
