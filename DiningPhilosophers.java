import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import models.Fork;
import models.Philosopher;

public class DiningPhilosophers {

    public static void main(String[] args) throws Exception {

        final Philosopher[] philosophers = new Philosopher[5];
        Fork[] forks = new Fork[5];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork(i + 1);
        }

        ExecutorService executor = Executors.newFixedThreadPool(5);

        IntStream
            .range(0, 5)
            .forEach(i -> executor.submit(() -> {
                Fork leftFork = forks[i];
                Fork rightFork = forks[(i + 1) % 5];

                philosophers[i] = i == 4 ? 
                    new Philosopher(i + 1, rightFork, leftFork):
                    new Philosopher(i + 1, leftFork, rightFork);  

                philosophers[i].start();
            }));

    }
}

