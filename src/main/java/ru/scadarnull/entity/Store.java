package ru.scadarnull.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Store {
    private AtomicInteger goods;

    public Store(int goods) {
        this.goods = new AtomicInteger(goods);
    }

    public synchronized int sell(int numberOfGoods){
        if(goods.get() == 0){
            return -1;
        }
        if(numberOfGoods > goods.get()){
            int temp = goods.get();
            goods.addAndGet(-temp);
            return temp;
        }else{
            goods.addAndGet(-numberOfGoods);
            return numberOfGoods;
        }
    }

    public AtomicInteger getGoods() {
        return goods;
    }
}
