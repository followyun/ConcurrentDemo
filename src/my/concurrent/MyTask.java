package my.concurrent;

import java.util.concurrent.Callable;

/**
 */
public abstract class MyTask implements Callable<Long> {
    private long taskId;

    public Long call() throws Exception {
        System.out.println("第" + taskId + "个线程 ID[" + Thread.currentThread().getId() + "]开始执行");
        Thread.sleep(2000L);
        System.out.println("第" + taskId + "个线程 ID[" + Thread.currentThread().getId() + "]结束执行");
        return taskId;
    }

    public MyTask(long taskId) {
        this.taskId = taskId;
    }

    public long getTaskId(){
        return taskId;
    }
}
