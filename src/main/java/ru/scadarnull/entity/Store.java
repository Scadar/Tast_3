package ru.scadarnull.entity;

import ru.scadarnull.App;

import java.util.concurrent.atomic.AtomicInteger;

public class Store {
    public volatile int counter;
    private AtomicInteger goods;

    public Store(int goods) {
        this.goods = new AtomicInteger(goods);
        counter = 0;
    }

    public synchronized void sell(int numberOfGoods, Customer customer){

        if(goods.get() == 0){
            customer.setActive(false);
            notifyAll();
            return;
        }

        if(numberOfGoods > goods.get()){
            if(goods.get() != 0){
                int temp = goods.get();
                goods.addAndGet(-temp);
                customer.addToSumOfGoods(temp);
                customer.incNumberOfPurchased();
            }
        }else{
            goods.addAndGet(-numberOfGoods);
            customer.addToSumOfGoods(numberOfGoods);
            customer.incNumberOfPurchased();
        }

        counter++;
        if(counter < App.NUMBER_OF_CUSTOMERS){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            counter = 0;
            notifyAll();
        }
    }



    public AtomicInteger getGoods() {
        return goods;
    }
}
