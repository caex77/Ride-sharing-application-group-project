import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class AmendBookingFrameTest {
    @Test
    void shouldUpdateCustomerName() {
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

        booking.setCustomerName("Ling");

        assertEquals("Ling", booking.getCustomerName());
    }


}