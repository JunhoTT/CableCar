package cablecar;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CableCar implements Runnable {
    Semaphore visitorsWaitingInMountainFootForDeparture, visitorsWaitingInMountainSummitForReturning;
    Semaphore seatsAvailableOnDepartureCableCar, seatsAvailableOnReturningCableCar;
    Semaphore visitorsOnSummitCounter;

    public CableCar(Semaphore visitorsWaitingInMountainFootForDeparture, Semaphore visitorsWaitingInMountainSummitForReturning, Semaphore seatsAvailableOnDepartureCableCar, Semaphore seatsAvailableOnReturningCableCar, Semaphore visitorsOnSummitCounter) {
        this.visitorsWaitingInMountainFootForDeparture = visitorsWaitingInMountainFootForDeparture;
        this.visitorsWaitingInMountainSummitForReturning = visitorsWaitingInMountainSummitForReturning;
        this.seatsAvailableOnDepartureCableCar = seatsAvailableOnDepartureCableCar;
        this.seatsAvailableOnReturningCableCar = seatsAvailableOnReturningCableCar;
        this.visitorsOnSummitCounter = visitorsOnSummitCounter;
    }

    @Override
    public void run() {
        while (visitorsWaitingInMountainFootForDeparture.availablePermits() != 0 || visitorsOnSummitCounter.availablePermits() != 0) {
            int i;
            try {
                // Departure travel simulation
                i = 0;
                while (i < 10 && i + visitorsOnSummitCounter.availablePermits() < 50) {     // Confirm that when the cable car arrived at summit, the number of people in summit would not higher than 50
                    if (visitorsWaitingInMountainFootForDeparture.tryAcquire(50, TimeUnit.MICROSECONDS)) {
                        ++i;
                        System.out.println("A visitor boarded the departure cable car. Now we have " + i + " visitors on the cable car.  And there are still " + visitorsWaitingInMountainFootForDeparture.availablePermits() + " visitors waiting for departure boarding.");
                    } else {
                        break;
                    }
                }
                System.out.println("The cable car leaved foot for summit. " + i + " visitors on board.");
                Thread.sleep(1000);
                visitorsOnSummitCounter.release(i);

                System.out.println(i + " visitors arrived at the summit. Totally "+ visitorsOnSummitCounter.availablePermits() + " visitors on the summit now.");

                // Returning travel simulation
                i = 0;
                while (i < 10) {
                    if (visitorsWaitingInMountainSummitForReturning.tryAcquire(50, TimeUnit.MICROSECONDS)) {
                        ++i;
                        System.out.println("A visitor boarded the returning cable car. Now we have " + i + " visitors on the cable car. And there are still " + visitorsWaitingInMountainSummitForReturning.availablePermits() + " visitors waiting for return boarding.");
                    } else {
                        break;
                    }
                }
                System.out.println("The cable car leaved summit for foot. " + i + " visitors on board.");
                visitorsOnSummitCounter.acquire(i);
                Thread.sleep(1000);
                System.out.println(i + " visitors arrived at the foot.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All visitors have back to the foot, CableCar thread terminated.");
    }
}
