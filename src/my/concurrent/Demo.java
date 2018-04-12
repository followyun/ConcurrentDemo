package my.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
}
