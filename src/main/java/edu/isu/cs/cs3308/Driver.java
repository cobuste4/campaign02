package edu.isu.cs.cs3308;

/**
 * Class containing main which is used to test the Simulation manually
 *
 * @author Steve Coburn
 */
public class Driver {
    public static void main(String[] args) {
        Simulation sim = new Simulation(18, 10, 50, 1024);
        sim.runSimulation();
    }
}