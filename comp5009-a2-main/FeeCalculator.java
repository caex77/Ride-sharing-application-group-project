import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FeeCalculator {

    /**  This method calculates the amendment fee based on how close the amendment is to the booking date.
     *
     *   @param bookingDate  The date of the original booking, used to determine if an amendment fee applies
     *                       based on how close the amendment is to the booking date.
     *   @return             The amendment fee.
     */
    public static double calculateAmendmentFee(LocalDate bookingDate) {
        LocalDate currentDate = LocalDate.now();
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(currentDate, bookingDate);

        if (daysBetween <= 3) {
            return 5.0; // flat fee of £5 for amendments made within 3 days of booking
        } else {
            return 0.0; // No fee for amendments made more than 3 days before booking
        }
    }

    /** This method calculates the refund amount based on how close the cancellation is to the booking date.
     *
     *   @param bookingDate  The date of the original booking, used to determine the refund amount based on how
     *                       close the booking date is.
     *   @param hour         The hour of the original booking.
     *   @param originalCost  The original cost of the booking, used to calculate the final cost of refund.
     *   @return              The final cost of the refund.
     */
    public static double calculateCancellationRefund(LocalDate bookingDate, int hour, double originalCost) {
        LocalDate currentDate = LocalDate.now();
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(currentDate, bookingDate);

        LocalDateTime bookingDateTime = bookingDate.atTime(hour, 0);
        LocalDateTime currentDateTime = LocalDateTime.now();
        long hoursBetween = java.time.temporal.ChronoUnit.HOURS.between(currentDateTime, bookingDateTime);

        if (daysBetween > 3) {
            return originalCost;
        } else if (daysBetween == 3) {
            return originalCost * 0.75;
        } else if (daysBetween == 2) {
            return originalCost * 0.5;
        } else if (daysBetween == 1) {
            return originalCost * 0.25;
        } else if (hoursBetween >=1) {
            return 0.0;
        } else {
            return 0.0;
        }

    }
}
