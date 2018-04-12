package my.concurrent;

/**
 */
public class UserInfoTask extends MyTask{
    private UserInfo userInfo;
    public UserInfoTask(long taskId,UserInfo userInfo) {
        super(taskId);
        this.userInfo = userInfo;
    }

    @Override
    public Long call() throws Exception {
        userInfo.setNowUserIndex((int)super.getTaskId());
        return super.call();
    }
}
