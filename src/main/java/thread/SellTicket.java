package thread;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.AnnotatedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @Title: SellTicket
 * @Description: 买票线程安全
 * @Author: zhaoyc
 * @Date: 7/17/2020 6:30 PM
 */
@Slf4j(topic = "c.SellTicket")
public class SellTicket {

    static Random random = new Random();

    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }

    public static void main(String[] args) throws InterruptedException {
        TicketWindow window = new TicketWindow(1000);

        List<Thread> threadList = new ArrayList<>();
        List<Integer> soldTickets = new Vector<>();

        for (int i = 0; i < 4000; i++) {
            Thread thread = new Thread(() -> {
                int sold = window.sellTicket(randomAmount());
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                soldTickets.add(sold);
            });
            threadList.add(thread);
            thread.start();
        }

        for (Thread thread : threadList) {
            thread.join();
        }

        log.debug("剩余票数: {}", window.getAmount());
        log.debug("总共卖出: {}", soldTickets.stream().mapToInt(i ->i).sum());
    }
}

class TicketWindow {

    private int amount;

    public TicketWindow(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int sellTicket(int ticket) {
        if (this.amount >= ticket) {
            this.amount -= ticket;
            return ticket;
        } else {
            return 0;
        }
    }
}