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
        System.out.println("提交第" + count + "个线程！");
        executorService.execute(command);
    }

    private MyExecutorService() {

    }

    public <T> Future<T> submit(Callable<T> callable) {
        int count = threadCount.addAndGet(1);
        System.out.println("提交第" + count + "个线程！");
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
            BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(2);
            ThreadFactory threadFactory = Executors.defaultThreadFactory();
            RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
            //AbortPolicy 取消策略，当工作队列满后再加入任务会抛出异常RejectedExecutionException
            //DiscardPolicy 丢弃策略，当工作队列满后再加入任务会丢弃该任务
            //DiscardOldestPolicy 丢弃策略，当工作队列满后再加入任务会丢弃队列中队头的任务，然后将该任务加入工作队列
            //CallerRunsPolicy 运行策略，当工作队列满后再加入任务，该任务会在执行executor()或submit()的线程中被执行

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
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(2);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        myExecutorService.executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                unit, workQueue, threadFactory, handler);
        return myExecutorService;
    }

    public int getCount(){
        return threadCount.get();
    }
}
