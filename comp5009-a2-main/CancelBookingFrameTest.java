import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CancelBookingFrameTest {
    @Test
    void removeBooking() {
        Main.bookings = new ArrayList<>();

        Booking booking = new Booking(
                "XX123",
                "Aibhlinn Deeney",
                "ad930@kent.ac.uk",
                "07777777777",
                true,
                "Saloon",
                "Small",
                10,
                "Wednesday",
                LocalDate.of(2026, 6, 10),
                5,
                "Canterbury",
                "Gatwick Airport",
                1.5,
                25.00
        );

        Main.bookings.add(booking);

        Main.bookings.remove(booking);

        assertFalse(Main.bookings.contains(booking));
        assertEquals(0, Main.bookings.size());
    }

}