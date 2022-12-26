package Threds;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch1 {
    public static void main(String[] args) {


    CountDownLatch latch = new CountDownLatch(4);
    //4 trhead öncelikli bunlar dan önce bu 4 threadi çalıştır

        WorkerThreads worker1=new WorkerThreads("Worker-1",5000,latch);
        WorkerThreads worker2=new WorkerThreads("Worker-2",7000,latch);
        WorkerThreads worker3=new WorkerThreads("Worker-3",9000,latch);
        WorkerThreads worker4=new WorkerThreads("Worker-4",11000,latch);


        //start worker
        worker1.start();
        worker2.start();
        worker3.start();
        worker4.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName()+" task finish");
    }
}


class WorkerThreads extends Thread {
    private int delay;
    private CountDownLatch latch;
//constructor
    public WorkerThreads(String name, int delay, CountDownLatch latch) {
        super(name);
        this.delay = delay;
        this.latch = latch;

    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
            System.out.println(Thread.currentThread().getName()+"çalışıyor");
            latch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}