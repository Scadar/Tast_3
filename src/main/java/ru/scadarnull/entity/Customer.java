package ru.scadarnull.entity;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Customer implements Runnable{

    private String name;
    private Store store;
    private int sumOfGoods;
    private int numberOfPurchased;
    private boolean isActive;
    private CyclicBarrier cyclicBarrier;

    public Customer(String name, Store store, CyclicBarrier cyclicBarrier) {
        this.name = name;
        this.store = store;
        this.isActive = true;
        this.sumOfGoods = 0;
        this.numberOfPurchased = 0;
        this.cyclicBarrier = cyclicBarrier;
    }

    public void buy(){
        int random = 1 + (int)(Math.random() * 10);
        int goods = store.sell(random);
        if(goods == -1){
            setActive(false);
        }else{
            sumOfGoods += goods;
            numberOfPurchased++;
        }
    }

    @Override
    public void run() {
        while (isActive){
            buy();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException ignored) {
            }
        }
        info();
        cyclicBarrier.reset();
    }

    private void info(){
        System.out.println(name + " купил " + sumOfGoods + " кол-во " + numberOfPurchased);
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getSumOfGoods() {
        return sumOfGoods;
    }

}
