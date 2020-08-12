package cablecar;

import java.util.concurrent.Semaphore;

public class Runner {
    public static void main(String[] args) {
        Semaphore visitorsWaitingInMountainFootForDeparture, visitorsWaitingInMountainSummitForReturning;
        Semaphore seatsAvailableOnDepartureCableCar, seatsAvailableOnReturningCableCar;
        Semaphore visitorsOnSummitCounter;

        visitorsWaitingInMountainFootForDeparture = new Semaphore(0);
        visitorsWaitingInMountainSummitForReturning = new Semaphore(0);
        visitorsOnSummitCounter = new Semaphore(0);
        seatsAvailableOnDepartureCableCar = new Semaphore(0);
        seatsAvailableOnReturningCableCar = new Semaphore(0);

        CableCar cableCar = new CableCar(visitorsWaitingInMountainFootForDeparture,visitorsWaitingInMountainSummitForReturning, seatsAvailableOnDepartureCableCar, seatsAvailableOnReturningCableCar, visitorsOnSummitCounter );
        DepartingTouristGenerator departingTouristGenerator = new DepartingTouristGenerator(visitorsWaitingInMountainFootForDeparture,visitorsWaitingInMountainSummitForReturning, seatsAvailableOnDepartureCableCar, seatsAvailableOnReturningCableCar, visitorsOnSummitCounter );
        ReturningTouristGenerator returningTouristGenerator = new ReturningTouristGenerator(visitorsWaitingInMountainFootForDeparture,visitorsWaitingInMountainSummitForReturning, seatsAvailableOnDepartureCableCar, seatsAvailableOnReturningCableCar, visitorsOnSummitCounter );

        new Thread(departingTouristGenerator).start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(cableCar).start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(returningTouristGenerator).start();
    }
}
