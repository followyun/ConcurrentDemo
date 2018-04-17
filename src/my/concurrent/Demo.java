package my.concurrent;

import my.concurrent.lock.Dispatcher;
import my.concurrent.lock.Taxi;

import java.awt.*;
import java.util.concurrent.*;

/**
 * 使用示例
 */
public class Demo {
    /**
     * 测试超出工作者队列，不同策略产生的结果
     */
    public static void overFlowWorkQueueSizeTest() {

        MyExecutorService myExecutorService = MyExecutorService.getMyExecutorService();

        UserInfo userInfo = new UserInfo();
        Future<Long> result1 = myExecutorService.submit(new UserInfoTask(myExecutorService.getCount() + 1, userInfo));
        Future<Long> result2 = myExecutorService.submit(new UserInfoTask(myExecutorService.getCount() + 1, userInfo));
        Future<Long> result3 = myExecutorService.submit(new UserInfoTask(myExecutorService.getCount() + 1, userInfo));
        Future<Long> result4 = myExecutorService.submit(new UserInfoTask(myExecutorService.getCount() + 1, userInfo));
        Future<Long> result5 = myExecutorService.submit(new UserInfoTask(myExecutorService.getCount() + 1, userInfo));

        try {
            System.out.println(result1.get());
            System.out.println(userInfo.getNowUserIndex());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            long value2 = result2.get();
            System.out.println(userInfo.getNowUserIndex());
            System.out.println(value2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(result3.get());
            System.out.println(userInfo.getNowUserIndex());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(result4.get());
            System.out.println(userInfo.getNowUserIndex());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(result5.get());
            System.out.println(userInfo.getNowUserIndex());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 左右死锁测试
     */
    public static void rightLeftDeadLockTest(){
        final Dispatcher dispatcher = new Dispatcher();
        final Taxi taxi = new Taxi(dispatcher);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(new Runnable() {
            public void run() {
                for(;;){
                    System.out.println("开始调用dispatcher.getImage()");
                    dispatcher.getImage();
                    System.out.println("结束调用dispatcher.getImage()");

                }
            }
        });

        executorService.submit(new Runnable() {
            public void run() {
                for(;;){
                    System.out.println("开始调用taxi.setDestation()");
                    taxi.setDestation(new Point(1,3));
                    System.out.println("结束调用taxi.setDestation()");

                }
            }
        });

        executorService.shutdown();
        try {
            executorService.awaitTermination(1000, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
