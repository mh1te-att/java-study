class Algs {

    private static int [] num = {1,3,6,8,9,11,13,15,32};

    public static void main(String[] args) {
        System.out.println(searchFirst(num, 10));
        System.out.println(searchLast(num, 10));
    }

    public static int searchFirst(int[] num, int key) {
        int lo = 0;
        int hi = num.length - 1;
        int index = 0;
        int mid = 0;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            if (key > num[mid]) {
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
                index = mid;
            }
        }
        return index;
    }

    public static int searchLast(int[] num, int key) {
        int lo = 0;
        int hi = num.length - 1;
        int index = 0;
        int mid = 0;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            if (key >= num[mid]) {
                lo = mid + 1;
                index = mid;
            }
            else {
                hi = mid - 1;
            }
        }
        return index;
    }
}