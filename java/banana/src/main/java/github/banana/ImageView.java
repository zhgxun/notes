//
// 其实曾经一度向往客户端开发, 觉得是多么了不起的技术进步, 知道网易云音乐就是一个前端
// 框架+H5开发出来的时候, 确实内心还是有一些小激动的。
// 
package github.banana;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * 图片查看工具
 * 
 * 如今H5已经足够完善而且轻量, 可以写出很多优美的软件比如网易云音乐。
 * 
 * @author zhgxun
 *
 */
public class ImageView {

    public static void main(String[] args) {
        // 监听事件队列
        EventQueue.invokeLater(() -> {
            JFrame frame = new ImageViewFrame();
            // 工具标题
            frame.setTitle("image view tool");
            // 默认的关闭操作, 退出后关闭
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // 设置可见性, 为false则将不能持续看到窗口, 间隔几秒钟后自动消失
            frame.setVisible(true);
        });
    }
}

/**
 * 图像窗口查看器
 * 
 * @author zhgxun
 *
 */
class ImageViewFrame extends JFrame {
    // 继承类默认需要实现类的版本号
    private static final long serialVersionUID = 1L;
    // 标签
    private JLabel label;
    // 选择器, 即是鼠标可点击操作的区域
    private JFileChooser chooser;
    // 窗体的默认宽高
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public ImageViewFrame() {
        // 设置宽高
        setSize(WIDTH, HEIGHT);

        // 新建一个标签
        label = new JLabel();
        add(label);

        // 新建一个文件选择器
        chooser = new JFileChooser();
        // 默认选择当前目录, 打开后可自动选择其它目录
        chooser.setCurrentDirectory(new File("."));

        // 菜单
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);

        // 添加一个名称为File的菜单
        JMenu menu = new JMenu("File");
        bar.add(menu);

        // 给名称为File的菜单添加一个子项Open, 打开后可选择文件
        JMenuItem item = new JMenuItem("Open");
        menu.add(item);

        // 该子项监听事件操作
        item.addActionListener(event -> {
            // 显示打开文件对话框
            int result = chooser.showOpenDialog(null);
            // 如果监听到有文件打开操作
            if (result == JFileChooser.APPROVE_OPTION) {
                // 获取文件的路径
                String name = chooser.getSelectedFile().getPath();
                // 标签窗体中设置该图片路径并显示图片
                label.setIcon(new ImageIcon(name));
            }
        });

        // 给File的菜单添加一个退出子项Exit
        JMenuItem exit = new JMenuItem("Exit");
        menu.add(exit);
        // 监听子项退出事件操作, 发生事件操作时即是选择退出程序直接退出虚拟机即可
        exit.addActionListener(event -> System.exit(0));
    }
}
