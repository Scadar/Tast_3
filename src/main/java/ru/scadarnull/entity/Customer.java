package ru.scadarnull.entity;

public class Customer implements Runnable{

    private String name;
    private Store store;
    private int numberOfPurchasedGoods;
    private boolean isActive;

    public Customer(String name, Store store) {
        this.name = name;
        this.store = store;
        this.isActive = true;
        this.numberOfPurchasedGoods = 0;
    }

    public void buy(){
        int random = 1 + (int)(Math.random() * 10);
        int result = store.sell(random);
        if(result == -1){
            isActive = false;
        }else{
            numberOfPurchasedGoods += result;
        }
    }

    @Override
    public void run() {
        while (isActive){
            buy();
        }
        info();
    }

    private void info(){
        System.out.println(name + " купил " + numberOfPurchasedGoods);
    }

    public int getNumberOfPurchasedGoods() {
        return numberOfPurchasedGoods;
    }
}
