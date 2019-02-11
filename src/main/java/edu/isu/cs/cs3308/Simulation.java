package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.impl.LinkedQueue;

import java.util.Random;

/**
 * Class representing a wait time simulation program.
 *
 * @author Isaac Griffith
 * @author Steve Coburn
 */
public class Simulation {

    private int arrivalRate;
    private int maxNumQueues;
    private Random r;
    private int numIterations = 50;
    private int timeLimit = 720;
    private LinkedQueue<Integer>[] arr;

    /**
     * Constructs a new simulation with the given arrival rate and maximum number of queues. The Random
     * number generator is seeded with the current time. This defaults to using 50 iterations.
     *
     * @param arrivalRate  the integer rate representing the maximum number of new people to arrive each minute
     * @param maxNumQueues the maximum number of lines that are open
     */
    public Simulation(int arrivalRate, int maxNumQueues) {
        this.arrivalRate = arrivalRate;

        this.maxNumQueues = maxNumQueues;
        r = new Random();
    }

    /**
     * Constructs a new simulation with the given arrival rate and maximum number of queues. The Random
     * number generator is seeded with the provided seed value, and the number of iterations is set to
     * the provided value.
     *
     * @param arrivalRate   the integer rate representing the maximum number of new people to arrive each minute
     * @param maxNumQueues  the maximum number of lines that are open
     * @param numIterations the number of iterations used to improve data
     * @param seed          the initial seed value for the random number generator
     */
    public Simulation(int arrivalRate, int maxNumQueues, int numIterations, int seed) {
        this(arrivalRate, maxNumQueues);
        r = new Random(seed);
        this.numIterations = numIterations;
    }

    /**
     * Executes the Simulation
     *
     * First loop runs for each number of queues there are, starting at 1
     * Second loop runs for each iteration specified by the user
     *      Loop inside the iteration loop creates space in arr for each queue
     * Third loop runs for each minute up to the max specified by timeLimit
     *      First loop inside the minute loop is for adding people to shortest queue. Includes its own nested loop
     *      Second loop inside the minute loop is for taking people out of each queue
     */
    public void runSimulation() {
        System.out.println("Arrival rate: " + arrivalRate);

        for (int currentQueues = 1; currentQueues <= maxNumQueues; currentQueues++) {
            int avgWaitTime = 0;
            int totalWaitTime = 0;
            int peopleThrough = 0;

            for (int currentIteration = 0; currentIteration < numIterations; currentIteration++) {
                arr = new LinkedQueue[currentQueues];
                for (int temp = 0; temp < currentQueues; temp++) {
                    arr[temp] = new LinkedQueue<>();
                }

                for (int timeCurrent = 0; timeCurrent < timeLimit; timeCurrent++) {

                    int peops = getRandomNumPeople(arrivalRate);
                    for (int temp = 0; temp < peops; temp++) {
                        int shortestLine = 0;
                        for (int queueToCheck = 1; queueToCheck < currentQueues; queueToCheck++) {
                            if (arr[queueToCheck].size() < arr[shortestLine].size()) {
                                shortestLine = queueToCheck;
                            }
                        }
                        arr[shortestLine].offer(timeCurrent - 1);
                    }

                    for (int queueToCheck = 0; queueToCheck < currentQueues; queueToCheck++) {
                        if (arr[queueToCheck].size() > 1) {
                            totalWaitTime += timeCurrent - arr[queueToCheck].poll();
                            totalWaitTime += timeCurrent - arr[queueToCheck].poll();
                            peopleThrough += 2;
                        } else if (arr[queueToCheck].size() == 1) {
                            totalWaitTime += timeCurrent - arr[queueToCheck].poll();
                            peopleThrough += 1;
                        }
                    }
                }
                avgWaitTime += totalWaitTime / peopleThrough;
            }
            avgWaitTime = avgWaitTime / numIterations;
            System.out.println("Average wait time using " + currentQueues + " queue(s): " + avgWaitTime);
        }
    }

    /**
     * returns a number of people based on the provided average
     *
     * @param avg The average number of people to generate
     * @return An integer representing the number of people generated this minute
     */
    //Don't change this method.
    private static int getRandomNumPeople(double avg) {
        Random r = new Random();
        double L = Math.exp(-avg);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }
}
