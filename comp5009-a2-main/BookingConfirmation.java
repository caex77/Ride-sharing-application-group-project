import javax.swing.*;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

public class BookingConfirmation {

    public static String newBookingMessage(
            String customerName,
            String email,
            String phoneNumber,
            boolean wantsSms,
            String pickup,
            String dropoff,
            LocalDate bookingDate,
            String time,
            String vehicleType,
            double cost) {

        String bookingRef = BookingConfirmation.generateBookingReference();
        String plateNumber = generatePlate();

        String message =
                "Booking confirmed\n\n" +
                        "Booking reference: " + bookingRef + "\n" +
                        "Customer name: " + customerName + "\n" +
                        "Pickup location: " + pickup + "\n" +
                        "Drop-off location: " + dropoff + "\n" +
                        "Date: " + bookingDate + "\n" +
                        "Time: " + time + "\n" +
                        "Vehicle information: This coach's number plate is " + plateNumber + "\n" +
                        "Total price: £" + String.format("%.2f", cost) + "\n" +
                        "Payment status: Confirmed\n\n" +
                        "Confirmation email sent to: " + email + "\n" +
                        getSmsMessage(phoneNumber, wantsSms);

        JOptionPane.showMessageDialog(
                null,
                message,
                "New Booking Confirmation",
                JOptionPane.INFORMATION_MESSAGE
        );

        return bookingRef;
    }

    public static void amendmentMessage(
            String customerName,
            String email,
            String phoneNumber,
            boolean wantsSms,
            String pickup,
            String dropoff,
            LocalDate newDate,
            String newTime,
            String vehicleType,
            double newCost) {

        String plateNumber = generatePlate();

        String message =
                "Booking amendment confirmed\n\n" +
                        "Customer name: " + customerName + "\n" +
                        "Updated pickup location: " + pickup + "\n" +
                        "Updated drop-off location: " + dropoff + "\n" +
                        "New date: " + newDate + "\n" +
                        "New time: " + newTime + "\n" +
                        "Vehicle information: This coach's number plate is " + plateNumber + "\n" +
                        "Updated total price: £" + String.format("%.2f", newCost) + "\n" +
                        "Payment status: Confirmed\n\n" +
                        "Amendment confirmation email sent to: " + email + "\n" +
                        getSmsMessage(phoneNumber, wantsSms);

        JOptionPane.showMessageDialog(
                null,
                message,
                "Amendment Confirmation",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void cancellationMessage(
            String customerName,
            String email,
            String phoneNumber,
            boolean wantsSms,
            String pickup,
            String dropoff,
            LocalDate bookingDate,
            String time) {

        String message =
                "Booking cancellation confirmed\n\n" +
                        "Customer name: " + customerName + "\n" +
                        "Pickup location: " + pickup + "\n" +
                        "Drop-off location: " + dropoff + "\n" +
                        "Date: " + bookingDate + "\n" +
                        "Time: " + time + "\n" +
                        "Booking status: Cancelled\n\n" +
                        "Cancellation confirmation email sent to: " + email + "\n" +
                        getSmsMessage(phoneNumber, wantsSms);

        JOptionPane.showMessageDialog(
                null,
                message,
                "Cancellation Confirmation",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void affectedUserMessage(
            String customerName,
            String email,
            String pickup,
            String dropoff,
            LocalDate bookingDate,
            String time) {

        String message =
                "Trip update\n\n" +
                        "Hello " + customerName + ",\n\n" +
                        "Another user on your trip has changed or cancelled their booking.\n" +
                        "Your journey may be affected.\n\n" +
                        "Pickup location: " + pickup + "\n" +
                        "Drop-off location: " + dropoff + "\n" +
                        "Date: " + bookingDate + "\n" +
                        "Time: " + time + "\n\n" +
                        "An update email has been sent to: " + email;

        JOptionPane.showMessageDialog(
                null,
                message,
                "Affected User Message",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static String getSmsMessage(String phoneNumber, boolean wantsSms) {
        if (wantsSms && !phoneNumber.equals("")) {
            return "SMS confirmation sent to: " + phoneNumber;
        }

        return "SMS confirmation was not requested.";
    }

    public static String generateBookingReference() {
        Random random = new Random();
        return "BK" + (100000 + random.nextInt(900000));
    }

    public static String generatePlate() {
        Random random = new Random();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        char first = letters.charAt(random.nextInt(letters.length()));
        char second = letters.charAt(random.nextInt(letters.length()));
        int year = 20 + random.nextInt(10);
        char third = letters.charAt(random.nextInt(letters.length()));
        char fourth = letters.charAt(random.nextInt(letters.length()));
        char fifth = letters.charAt(random.nextInt(letters.length()));

        return "" + first + second + year + " " + third + fourth + fifth;
    }
}