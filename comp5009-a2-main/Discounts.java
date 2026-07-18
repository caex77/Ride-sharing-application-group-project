public class Discounts {
    //calculates how much of a discount your getting based on the day and time.
    public static double calculateDiscountPercent(String day, int hour) {
        double discount = 0;
// add discount on weedays and if is not busy
        if (day.equalsIgnoreCase("Monday") ||
                day.equalsIgnoreCase("Tuesday") ||
                day.equalsIgnoreCase("Wednesday") ||
                day.equalsIgnoreCase("Thursday")) {
            discount = discount + 10;
        }
// add a discount during this time
        if (hour >= 5 && hour <= 10) {
            discount = discount + 10;
        }
// make sure the discount doesn't go higher than here
        if (discount > 20) {
            discount = 20;
        }

        return discount;
    }
    // adding discount to the orignal price
    public static double applyDiscount(double originalPrice, String day, int hour) {
        double discountPercent = calculateDiscountPercent(day, hour);
        double discountAmount = originalPrice * (discountPercent / 100);
        return originalPrice - discountAmount;
    }
    // message saying discount has been added
    public static String getDiscountMessage(String day, int hour) {
        double discountPercent = calculateDiscountPercent(day, hour);

        if (discountPercent > 0) {
            return "Discount applied: " + discountPercent + "%";
        } else {
            return "No discounts available for this booking.";
        }
    }
}
