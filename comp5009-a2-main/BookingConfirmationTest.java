import java.time.LocalDate;

public class BookingConfirmationTest {

    public static void main(String[] args) {

        LocalDate date1 = LocalDate.of(2026, 5, 29);
        LocalDate date2 = LocalDate.of(2026, 5, 30);

        BookingConfirmation.newBookingMessage(
                "Bishr Aminu",
                "bishr@example.com",
                "07123456789",
                true,
                "Canterbury",
                "Victoria",
                date1,
                "14:30",
                "Coach",
                25.00
        );

        BookingConfirmation.amendmentMessage(
                "Bishr Aminu",
                "bishr@example.com",
                "07123456789",
                true,
                "Canterbury",
                "New Cross",
                date2,
                "16:00",
                "Coach",
                30.00
        );

        BookingConfirmation.cancellationMessage(
                "Bishr Aminu",
                "bishr@example.com",
                "07123456789",
                true,
                "Canterbury",
                "Victoria",
                date1,
                "14:30"
        );

        BookingConfirmation.affectedUserMessage(
                "Dharam Singh",
                "dharam@example.com",
                "Canterbury",
                "Victoria",
                date2,
                "14:30"
        );
    }
}