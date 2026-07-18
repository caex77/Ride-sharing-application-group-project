public class TripDurationCalculator {

    public static double calculateDuration(double distanceMiles, int hourOfDay,
                                           int numberOfPickups, String luggageSize) {

        // Base speed in miles per hour
        double baseSpeed = 30.0;

        // Traffic factor - slower during peak hours
        boolean isPeak = (hourOfDay >= 7 && hourOfDay <= 9) ||
                         (hourOfDay >= 17 && hourOfDay <= 19);
        double trafficMultiplier = isPeak ? 0.6 : 1.0;

        // Calculate base travel time in minutes
        double travelTime = (distanceMiles / (baseSpeed * trafficMultiplier)) * 60;

        // Add time for each passenger pickup (10 mins each)
        double pickupTime = numberOfPickups * 10.0;

        // Add time for luggage loading
        double luggageTime = 0;
        if (luggageSize.equals("Small")) luggageTime = 5.0;
        if (luggageSize.equals("Large")) luggageTime = 10.0;

        // Return total duration in minutes
        return travelTime + pickupTime + luggageTime;
    }
}