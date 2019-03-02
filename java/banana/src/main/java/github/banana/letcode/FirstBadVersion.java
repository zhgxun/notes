package github.banana.letcode;

/**
 * 278. 第一个错误的版本
 */
public class FirstBadVersion {

    public static void main(String[] args) {

    }

    public int firstBadVersion(int n) {
        int low = 1, high = n;
        int mid = 1;
        while (low < high) {
            mid = (low + high) / 2;
            // 找到第一个错误的版本
            if (!isBadVersion(mid)) {
                // 看它的下一个版本是不是true
                if (mid == 0 || isBadVersion(mid + 1)) {
                    // 题目需要返回的是错误的版本号, 当前mid是最后一个正确的版本号
                    return mid;
                }
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return mid;
    }

    boolean isBadVersion(int version) {
        return true;
    }
}
