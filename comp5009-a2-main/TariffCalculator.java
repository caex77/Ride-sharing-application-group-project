     public class TariffCalculator {

        public static double calculateCost (String vehicleType, String luggageSize, double distanceMiles, String dayOfWeek, int hourOfDay) {
            
            double base = 10.0;

             // Vehicle type charge
            double vehicleFee = 0;
            if (vehicleType.equals("Estate")) vehicleFee = 5.0;
            if (vehicleType.equals("Minivan")) vehicleFee = 10.0;

            // Luggage charge
            double luggageFee = 0;
            if (luggageSize.equals("Small")) luggageFee = 5.0;
            if (luggageSize.equals("Large")) luggageFee = 10.0;

             // Distance charge
            double distanceFee = distanceMiles * 1.5;

            // Weekend surcharge
            boolean isWeekend = dayOfWeek.equals("Saturday") || dayOfWeek.equals("Sunday");
            double dayMultiplier = isWeekend ? 1.3 : 1.0;

            // Peak hour surcharge (7-9am and 5-7pm)
            boolean isPeak = (hourOfDay >= 7 && hourOfDay <= 9) || (hourOfDay >= 17 && hourOfDay <= 19);
            double timeMultiplier = isPeak ? 1.2 : 1.0;

            return (base + vehicleFee + luggageFee + distanceFee) * dayMultiplier * timeMultiplier;
    }   
}