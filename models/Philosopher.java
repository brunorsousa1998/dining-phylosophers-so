package models;

public class Philosopher extends Thread {

    private int name;
    private Fork firstFork;
    private Fork secondFork;

    public Philosopher(int name, Fork firstFork, Fork secondFork) {
        this.name = name;
        this.firstFork = firstFork;
        this.secondFork = secondFork;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(
          "Filosofo " + name + " " + action);
        Thread.sleep(((int) (Math.random() * 2000)));
    }

    @Override
    public void run() {
        try {
            while (true) {
                doAction(": Pensando");
                synchronized (firstFork) {
                    doAction(": Pegou o garfo " + firstFork.position);
                    synchronized (secondFork) {
                        doAction(": Pegou o garfo " + secondFork.position + ". Comendo..."); 
                        
                        doAction(": Colocou de volta o garfo " + secondFork.position);
                    }
                    
                    doAction(": Colocou de volta o garfo " + firstFork.position + ". Voltando a filosofar");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}