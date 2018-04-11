package my.concurrent;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 */
public class MyExecutorService implements Executor {

    private ExecutorService executorService;
    private static MyExecutorService myExecutorService;
    private static final int POOL_SIZE = 1;
    private AtomicInteger threadCount = new AtomicInteger(0);

    public void execute(Runnable command) {
        int count = threadCount.addAndGet(1);
        System.out.println("执行第" + count + "个线程！");
        executorService.execute(command);
    }

    private MyExecutorService() {

    }

    public <T> Future<T> submit(Callable<T> callable) {
        int count = threadCount.addAndGet(1);
        System.out.println("执行第" + count + "个线程！");
        return executorService.submit(callable);
    }

    /**
     * 获取线程池服务
     *
     * @return
     */
    public synchronized static MyExecutorService getMyExecutorService() {

        if (myExecutorService == null) {
            myExecutorService = new MyExecutorService();
            int corePoolSize = POOL_SIZE;
            int maximumPoolSize = POOL_SIZE;
            long keepAliveTime = 0L;
            TimeUnit unit = TimeUnit.NANOSECONDS;
            //初始化工作队列，设定队列大小为3
            BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(3);
            ThreadFactory threadFactory = Executors.defaultThreadFactory();
            RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();//取消策略，当加入工作任务超过工作队列大小后抛出异常RejectedExecutionException
            myExecutorService.executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                    unit, workQueue, threadFactory, handler);
        }

        return myExecutorService;
    }

    /**
     * 创建线程池服务
     *
     * @param poolSize 线程池大小
     * @return
     */
    public static MyExecutorService newMyExecutorService(int poolSize) {

        MyExecutorService myExecutorService = new MyExecutorService();
        int corePoolSize = poolSize;
        int maximumPoolSize = poolSize;
        long keepAliveTime = 0L;
        TimeUnit unit = TimeUnit.NANOSECONDS;
        //初始化工作队列，设定队列大小为3
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(3);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        myExecutorService.executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                unit, workQueue, threadFactory, handler);
        return myExecutorService;
    }
}
