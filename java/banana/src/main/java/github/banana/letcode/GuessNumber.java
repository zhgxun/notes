package github.banana.letcode;

/**
 * 374. 猜数字大小
 * <p>
 * -1 : 我的数字比较小
 * 1 : 我的数字比较大
 * 0 : 恭喜！你猜对了！
 */
public class GuessNumber {

    public static void main(String[] args) {

    }

    public int guessNumber(int n) {
        int low = 1, high = n;
        int mid = low;
        while (low <= high) {
            mid = low + (high - low) / 2;
            int g = guess(mid);
            if (g == 0) {
                return mid;
            } else if (g == 1) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return mid;
    }

    public int guessNumber1(int n) {
        int left = 1;
        int right = n;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == 1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return mid;
    }

    int guess(int num) {
        return 0;
    }
}
