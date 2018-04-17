package my.concurrent.lock;

import java.awt.*;

/**
 */
public class Taxi {
    private final Dispatcher dispatcher;
    private Point location, destation;
    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public synchronized void setDestation(Point location){
        this.location = location;
        if(true){
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("调用dispatcher.notifyAvailable()开始");
            dispatcher.notifyAvailable(this);
            System.out.println("调用dispatcher.notifyAvailable()结束");

        }
    }


}
