package ru.scadarnull;

import ru.scadarnull.entity.Customer;
import ru.scadarnull.entity.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        if(args.length < 1){
            System.out.println("Неверные аргументы программы");
        }else{
            int numberOfCustomer = Integer.parseInt(args[0]);
            Store store = new Store(1000);
            List<Customer> customers = new ArrayList<>();
            List<Thread> threads = new ArrayList<>();

            for(int i = 0; i < numberOfCustomer; ++i){
                customers.add(new Customer("Покупатель " + i, store));
            }

            for(Customer customer : customers){
                threads.add(new Thread(customer));
            }

            for(Thread thread : threads){
                thread.start();
            }

            Thread.sleep(300);

            int sum = 0;
            for(Customer customer : customers){
                sum += customer.getNumberOfPurchasedGoods();
            }
            System.out.println(sum);
            System.out.println(store.getGoods());
        }
    }
}
