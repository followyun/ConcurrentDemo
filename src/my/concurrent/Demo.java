package my.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 使用示例
 */
public class Demo {
    /**
     * 测试超出工作者队列
     */
    public static void overFlowWorkQueueSizeTest(){

        MyExecutorService myExecutorService = MyExecutorService.getMyExecutorService();
        Future<Long> result1 = myExecutorService.submit(new Callable<Long>() {
            public Long call() throws Exception {
                System.out.println("线程ID["+Thread.currentThread().getId()+"]开始执行");
                Thread.sleep(2000L);
                System.out.println("线程ID["+Thread.currentThread().getId()+"]结束执行");
                return Thread.currentThread().getId();
            }
        });
        Future<Long> result2 = myExecutorService.submit(new Callable<Long>() {
            public Long call() throws Exception {
                System.out.println("线程ID["+Thread.currentThread().getId()+"]开始执行");
                Thread.sleep(2000L);
                System.out.println("线程ID["+Thread.currentThread().getId()+"]结束执行");
                return Thread.currentThread().getId();
            }
        });

        Future<Long> result3 = myExecutorService.submit(new Callable<Long>() {
            public Long call() throws Exception {
                System.out.println("线程ID["+Thread.currentThread().getId()+"]开始执行");
                Thread.sleep(2000L);
                System.out.println("线程ID["+Thread.currentThread().getId()+"]结束执行");
                return Thread.currentThread().getId();
            }
        });

        try {
            result1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            result2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            result3.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
