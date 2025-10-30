class Printer {
    // synchronized method ensures only one thread prints at a time
    public synchronized void printDocument(String userName, int pages) {
        System.out.println(userName + " started printing " + pages + " pages...");
        for (int i = 1; i <= pages; i++) {
            System.out.println("Printing page " + i + " of " + pages + " for " + userName);
            try {
                Thread.sleep(500); // simulate printing time
            } catch (InterruptedException e) {
                System.out.println("Printing interrupted for " + userName);
            }
        }
        System.out.println(userName + " finished printing.\n");
    }
}

class User implements Runnable {
    private String userName;
    private Printer printer;
    private int pages;

    public User(String userName, Printer printer, int pages) {
        this.userName = userName;
        this.printer = printer;
        this.pages = pages;
    }

    @Override
    public void run() {
        printer.printDocument(userName, pages);
    }
}

public class PrinterSimulation {
    public static void main(String[] args) {
        Printer sharedPrinter = new Printer();

        Thread user1 = new Thread(new User("Alice", sharedPrinter, 3));
        Thread user2 = new Thread(new User("Bob", sharedPrinter, 4));
        Thread user3 = new Thread(new User("Charlie", sharedPrinter, 2));

        user1.start();
        user2.start();
        user3.start();
    }
}