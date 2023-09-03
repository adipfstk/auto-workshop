package management.auto.employee;

import management.auto.car.Car;
import management.auto.car.StandardCar;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Task implements Runnable {
    private final Queue<Car> carQueue = new LinkedList<>();
    private int standardCarCount = 0;
    private int otherCarCount = 0;
    private int estimatedTime;
    public Task() {

    }

    @Override
    public void run() {
        Random random = new Random();
        this.estimatedTime = random.nextInt(1, 120) * 60 * 1000;
        synchronized (carQueue) {
            while (!carQueue.isEmpty()) {
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                var car = this.carQueue.poll();
                if (car instanceof StandardCar) {
                    standardCarCount--;
                } else {
                    otherCarCount--;
                    System.out.println(otherCarCount);
                }
            }
        }
    }


    public int getEstimatedTime() {
        return estimatedTime;
    }

    public int getStandardCarCount() {
        return standardCarCount;
    }

    public int getOtherCarCount() {
        return otherCarCount;
    }

    public Queue<Car> getCarQueue() {
        return carQueue;
    }

    public void setStandardCarCount(int standardCarCount) {
        this.standardCarCount = standardCarCount;
    }

    public void setOtherCarCount(int otherCarCount) {
        this.otherCarCount = otherCarCount;
    }
}
