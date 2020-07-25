package ru.scadarnull.entity;

public class Customer implements Runnable{

    private String name;
    private Store store;
    private int sumOfGoods;
    private int numberOfPurchased;
    private boolean isActive;

    public Customer(String name, Store store) {
        this.name = name;
        this.store = store;
        this.isActive = true;
        this.sumOfGoods = 0;
        this.numberOfPurchased = 0;
    }

    public void buy(){
        int random = 1 + (int)(Math.random() * 10);
        store.sell(random, this);
    }

    @Override
    public void run() {
        while (isActive){
            buy();
        }
        info();
    }

    private void info(){
        System.out.println(name + " купил " + sumOfGoods + " кол-во " + numberOfPurchased);
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void addToSumOfGoods(int goods) {
        sumOfGoods += goods;
    }

    public int getSumOfGoods() {
        return sumOfGoods;
    }

    public void incNumberOfPurchased() {
        numberOfPurchased++;
    }
}
