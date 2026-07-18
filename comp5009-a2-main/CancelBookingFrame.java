import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class CancelBookingFrame extends JFrame {
    //setting up the UI frame for cancelling a booking
    public CancelBookingFrame() {
        setTitle("Cancel Booking");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 10, 10));


        JLabel bookingLabel = new JLabel("Select Booking:");
        JComboBox<String> bookingBox = new JComboBox<>();

        //Add all current bookings to a dropdown
        for (Booking booking : Main.bookings){
            bookingBox.addItem(booking.getCustomerName());
        }

        JButton cancelButton = new JButton("Cancel Booking");
        add(bookingLabel);
        add(bookingBox);
        add(cancelButton);
        add(new JLabel());

        cancelButton.addActionListener(e -> {

            int selectedIndex = bookingBox.getSelectedIndex();

            Booking booking = Main.bookings.get(selectedIndex);

            // Convert booking date and time into a LocalDateTime


            LocalDate travelDate = booking.getBookingDate();

            LocalDateTime travelDateTime = LocalDateTime.of(travelDate, LocalTime.of(booking.getTimeOfDay(), 0));

            LocalDateTime now = LocalDateTime.now();
            //calculates the time from the trip until now
            Duration timeUntilTrip =
                    Duration.between(now, travelDateTime);

            // Prevent cancellation within 24 hours
            if (timeUntilTrip.toHours() < 24) {

                JOptionPane.showMessageDialog(
                        this,
                        "Bookings cannot be cancelled within 24 hours of travel."
                );

                return;
            }

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to cancel this booking?",
                    "Confirm Cancellation",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                //removes booking from the array list
                Main.bookings.remove(selectedIndex);

                JOptionPane.showMessageDialog(
                        this,
                        "Booking cancelled successfully!"
                );

                dispose();
            }
        });

        setVisible(true);


}}

