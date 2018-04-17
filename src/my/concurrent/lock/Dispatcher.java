package my.concurrent.lock;

import java.util.HashSet;
import java.util.Set;


public class Dispatcher {
    private final Set<Taxi> taxis;
    private final Set<Taxi> availabletaixes;

    public Dispatcher() {
        taxis = new HashSet<Taxi>();
        availabletaixes = new HashSet<Taxi>();
    }

    public synchronized void notifyAvailable(Taxi taxi) {
        availabletaixes.add(taxi);
    }

    public synchronized String getImage() {
        String imgStr = "这是一张图片";
        System.out.println("调用        for(Taxi taxi : taxis){开始");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Taxi taxi : taxis) {
            System.out.println("显示汽车位置");
        }
        System.out.println("调用        for(Taxi taxi : taxis){结束");
        return imgStr;
    }
}
