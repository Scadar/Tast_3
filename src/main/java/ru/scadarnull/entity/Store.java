package ru.scadarnull.entity;


import java.util.concurrent.atomic.AtomicInteger;

public class Store {
    private AtomicInteger goods;

    public Store(int goods) {
        this.goods = new AtomicInteger(goods);
    }

    public synchronized void sell(int numberOfGoods, Customer customer){
        if(goods.get() == 0){
            customer.setActive(false);
            return;
        }

        if(numberOfGoods > goods.get()){
            int temp = goods.get();
            goods.addAndGet(-temp);
            customer.addToSumOfGoods(temp);
        }else{
            goods.addAndGet(-numberOfGoods);
            customer.addToSumOfGoods(numberOfGoods);
        }
        customer.incNumberOfPurchased();
    }

    public AtomicInteger getGoods() {
        return goods;
    }
}
