package github.banana;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class UtilQueue {
    public static void main(String[] args) {
        // queue();
        // 实现自定义的排序方法
        PriorityQueue<User> users = new PriorityQueue<>(new Comparator<User>() {

            @Override
            public int compare(User o1, User o2) {
                return -o1.getName().compareTo(o2.getName());
            }
            
        });
        // 往队列添加元素
        // 添加失败抛异常
        users.add(new User(1, "周迅", 18));
        // 添加失败返回false
        users.offer(new User(2, "袁姗姗", 20));
        users.add(new User(3, "张国荣", 30));
        users.offer(new User(4, "罗大佑", 20));
        
        System.out.println("队列的长度：" + users.size());
        System.out.println("获取头部元素并删除：" + users.remove());
        System.out.println("队列的长度：" + users.size());
        System.out.println("获取头部元素并删除：" + users.poll());
        System.out.println("队列的长度：" + users.size());
        System.out.println("获取头部元素不删除：" + users.element());
        System.out.println("队列的长度：" + users.size());
        System.out.println("获取头部元素不删除：" + users.peek());
        System.out.println("队列的长度：" + users.size());
    }

    private static void queue() {
        // 初始化一个队列
        Queue<User> users = new LinkedList<>();
        // 往队列添加元素
        // 添加失败抛异常
        users.add(new User(1, "周迅", 18));
        // 添加失败返回false
        users.offer(new User(2, "袁姗姗", 20));
        users.add(new User(3, "张国荣", 30));
        users.offer(new User(4, "罗大佑", 20));

        System.out.println("队列的长度：" + users.size());
        System.out.println("获取头部元素并删除：" + users.remove());
        System.out.println("队列的长度：" + users.size());
        System.out.println("获取头部元素并删除：" + users.poll());
        System.out.println("队列的长度：" + users.size());
        System.out.println("获取头部元素不删除：" + users.element());
        System.out.println("队列的长度：" + users.size());
        System.out.println("获取头部元素不删除：" + users.peek());
        System.out.println("队列的长度：" + users.size());
    }
}
