package by.it.akhmelev.jd02_02.jd02_02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class th2_04_with_lock_ok {

    //переменная баланса
    private final static Lock lockBalance =new ReentrantLock();
    static Integer balance=new Integer(0);

    //это касса. Просто добавляет в баланс единицу
    static class Cashier extends Thread {
        //создадим видимость расчета
        int calc(int in) {
            int j=0; for (int i = 0; i < 666; i++) {j=j+(int)((Math.sqrt(i)));}
            return in;
        }
        @Override
        public  void run() {
            try {
                lockBalance.lock();
                balance += (calc(1));
            }
            finally {
                lockBalance.unlock();
            };
        }
    }
    //создадим 6666 касс. Каждая добавит по 1. Сколько всего будет?
    public static void main(String[ ] args) throws InterruptedException {
        //Считаем сколько было потоков
        int thCount=Thread.activeCount();

        for (int i = 0; i < 6666; i++) {
            new Cashier().start();
        }
        //пока потоков больше чем было в начале просто ждем
        while (Thread.activeCount()>thCount) {Thread.sleep(100);}
        System.out.print("Итого:"+balance);
    }
}
