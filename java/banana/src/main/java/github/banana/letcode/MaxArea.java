package github.banana.letcode;

/**
 * 11. 盛最多水的容器
 * <p>
 * 这种方法背后的思路在于，两线段之间形成的区域总是会受到其中较短那条长度的限制。此外，两线段距离越远，得到的面积就越大。
 * <p>
 * 我们在由线段长度构成的数组中使用两个指针，一个放在开始，一个置于末尾。 此外，我们会使用变量 maxareamaxarea 来持续存储到目前为止所获得的最大面积。
 * 在每一步中，我们会找出指针所指向的两条线段形成的区域，更新 maxareamaxarea，并将指向较短线段的指针向较长线段那端移动一步。
 * <p>
 * 指向较短线段的指针向较长线段那端移动一步
 * <p>
 * 如果我们试图将指向较长线段的指针向内侧移动，矩形区域的面积将受限于较短的线段而不会获得任何增加。
 * 但是，在同样的条件下，移动指向较短线段的指针尽管造成了矩形宽度的减小，但却可能会有助于面积的增大。
 * 因为移动较短线段的指针会得到一条相对较长的线段，这可以克服由宽度减小而引起的面积减小。
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
            // 高度较小的值为高, 下标的差值为宽度
            haveMaxArea = Math.max(haveMaxArea, Math.min(height[left], height[right]) * (right - left));
            // 如果左边高度较小, 则往右边移动
            if (height[left] < height[right]) {
                left++;
            } else {
                // 否则右边往左边移动
                right--;
            }
        }
        return haveMaxArea;
    }
}
