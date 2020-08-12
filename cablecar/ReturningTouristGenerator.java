package cablecar;

import java.util.concurrent.Semaphore;

public class ReturningTouristGenerator implements Runnable {
    Semaphore visitorsWaitingInMountainFootForDeparture, visitorsWaitingInMountainSummitForReturning;
    Semaphore seatsAvailableOnDepartureCableCar, seatsAvailableOnReturningCableCar;
    Semaphore visitorsOnSummitCounter;

    public ReturningTouristGenerator(Semaphore visitorsWaitingInMountainFootForDeparture, Semaphore visitorsWaitingInMountainSummitForReturning, Semaphore seatsAvailableOnDepartureCableCar, Semaphore seatsAvailableOnReturningCableCar, Semaphore visitorsOnSummitCounter) {
        this.visitorsWaitingInMountainFootForDeparture = visitorsWaitingInMountainFootForDeparture;
        this.visitorsWaitingInMountainSummitForReturning = visitorsWaitingInMountainSummitForReturning;
        this.seatsAvailableOnDepartureCableCar = seatsAvailableOnDepartureCableCar;
        this.seatsAvailableOnReturningCableCar = seatsAvailableOnReturningCableCar;
        this.visitorsOnSummitCounter = visitorsOnSummitCounter;
    }

    @Override
    public void run() {
        while (visitorsWaitingInMountainFootForDeparture.availablePermits() != 0 // While there is any visitors not yet visited the summit.
                || visitorsOnSummitCounter.availablePermits() != 0) {  // While there is any visitor who is on the summit.
            try {
                if (visitorsOnSummitCounter.availablePermits() > visitorsWaitingInMountainSummitForReturning.availablePermits()) {              // While there are some visitors playing on summit, they would randomly want to go back to foot.
                    System.out.println("One visitor want to go back. Now we have " + (visitorsWaitingInMountainSummitForReturning.availablePermits() + 1) + " waiting for the returning cable car. Totally " + visitorsOnSummitCounter.availablePermits() + " visitors on the summit.");
                    visitorsWaitingInMountainSummitForReturning.release();   // One visitor want to go back.
                }
                Thread.sleep((int) (Math.random() * 200) + 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ReturningTouristGenerator thread terminated.");

    } // If no more visitors, terminate.
}

