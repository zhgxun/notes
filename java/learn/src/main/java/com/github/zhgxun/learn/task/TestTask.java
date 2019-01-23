package com.github.zhgxun.learn.task;

import com.github.zhgxun.learn.common.task.annotation.ScheduleTask;
import com.github.zhgxun.learn.service.first.LaunchInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class TestTask {

    @Autowired
    private LaunchInfoService launchInfoService;

    @ScheduleTask
    public void test() {
        log.info("Start task ...");
        log.info("LaunchInfoList: {}", launchInfoService.findAll());

        log.info("模拟启动线程操作");
        for (int i = 0; i < 5; i++) {
            new MyTask(i).start();
        }

        String builder = "";
        for (int i = 0; i < 10; i++) {
            builder = builder + "a";
        }
        System.out.println(builder);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyTask extends Thread {
    private int i;
    private int j;
    private String s;

    public MyTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("第 " + i + " 个线程启动..." + Thread.currentThread().getName());
        if (i == 2) {
            throw new RuntimeException("模拟运行时异常");
        }
        if (i == 3) {
            // 除数不为0
            int a = i / j;
        }
        // 未对字符串对象赋值, 获取长度报空指针错误
        if (i == 4) {
            System.out.println(s.length());
        }
    }
}
