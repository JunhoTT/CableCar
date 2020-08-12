package cablecar;

import java.util.concurrent.Semaphore;

public class DepartingTouristGenerator implements Runnable {
    Semaphore visitorsWaitingInMountainFootForDeparture, visitorsWaitingInMountainSummitForReturning;
    Semaphore seatsAvailableOnDepartureCableCar, seatsAvailableOnReturningCableCar;
    Semaphore visitorsOnSummitCounter;

    public DepartingTouristGenerator(Semaphore visitorsWaitingInMountainFootForDeparture, Semaphore visitorsWaitingInMountainSummitForReturning, Semaphore seatsAvailableOnDepartureCableCar, Semaphore seatsAvailableOnReturningCableCar, Semaphore visitorsOnSummitCounter) {
        this.visitorsWaitingInMountainFootForDeparture = visitorsWaitingInMountainFootForDeparture;
        this.visitorsWaitingInMountainSummitForReturning = visitorsWaitingInMountainSummitForReturning;
        this.seatsAvailableOnDepartureCableCar = seatsAvailableOnDepartureCableCar;
        this.seatsAvailableOnReturningCableCar = seatsAvailableOnReturningCableCar;
        this.visitorsOnSummitCounter = visitorsOnSummitCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i != 500; ++i) {
            try {
                System.out.println("One visitor comes and want to go to the summit. Now we have " + (visitorsWaitingInMountainFootForDeparture.availablePermits() + 1) + " waiting for the departing cable car. This is the " + (i + 1) + "th(st/nd) visitor.");

                visitorsWaitingInMountainFootForDeparture.release();
                Thread.sleep((int) (Math.random() * 60) + 30);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("DepartingTouristGenerator thread terminated.");
    }
}
