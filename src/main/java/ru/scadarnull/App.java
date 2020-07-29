package ru.scadarnull;

import ru.scadarnull.entity.Customer;
import ru.scadarnull.entity.Store;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;

/**
 * Hello world!
 *
 */
public class App 
{
    public static int NUMBER_OF_CUSTOMERS;

    public static void main( String[] args ) throws InterruptedException {
        if(args.length < 1){
            System.out.println("Неверные аргументы программы");
        }else{
            try {
                NUMBER_OF_CUSTOMERS = Integer.parseInt(args[0]);
            }catch (NumberFormatException ex){
                System.out.println("Неверные аргументы программы, введите число");
                return;
            }
            CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER_OF_CUSTOMERS);
            Store store = new Store(1000);
            List<Customer> customers = new ArrayList<>();
            List<Thread> threads = new ArrayList<>();
            for(int i = 0; i < NUMBER_OF_CUSTOMERS; ++i){
                customers.add(new Customer("Покупатель " + i, store, cyclicBarrier));
                threads.add(new Thread(customers.get(i)));
                threads.get(i).start();
            }

            for(Thread thread : threads){
                thread.join();
            }

            int sum = 0;
            for(Customer customer : customers){
                sum += customer.getSumOfGoods();
            }
            System.out.println("Всего куплено товаров " + sum);
            System.out.println("Товаров осталось в магазине " + store.getGoods());

        }
    }
}
