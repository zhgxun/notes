package github.banana.letcode;

/**
 * 11. 盛最多水的容器
 * <p>
 * 这种方法背后的思路在于，两线段之间形成的区域总是会受到其中较短那条长度的限制。此外，两线段距离越远，得到的面积就越大。
 * <p>
 * 我们在由线段长度构成的数组中使用两个指针，一个放在开始，一个置于末尾。 此外，我们会使用变量 maxareamaxarea 来持续存储到目前为止所获得的最大面积。
 * 在每一步中，我们会找出指针所指向的两条线段形成的区域，更新 maxareamaxarea，并将指向较短线段的指针向较长线段那端移动一步。
 */
public class MaxArea {

    public int maxArea(int[] height) {
        // 保存当前最大面积
        int haveMaxArea = 0;
        // 左指针
        int left = 0;
        // 右指针
        int right = height.length - 1;
        // 不需要重复, 此时为同一高度无面积
        while (left < right) {
            haveMaxArea = Math.max(haveMaxArea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return haveMaxArea;
    }
}
