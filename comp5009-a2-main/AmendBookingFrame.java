import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AmendBookingFrame extends JFrame{
    //setting up the UI frame for amending a booking
    public AmendBookingFrame() {
        setTitle("Amend Booking");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 10, 10));


        JLabel bookingLabel = new JLabel("  Select Booking:");
        JComboBox<String> bookingBox = new JComboBox<>();

        //Add all current bookings to a dropdown
        for (Booking booking : Main.bookings){
            bookingBox.addItem(booking.getBookingRef());
        }


        //buttons and boxes for all things that can be amending about the booking
        JLabel vehicleLabel = new JLabel("  Vehicle type:");
        String[] vehicles = {"Saloon", "Estate", "Minivan"};
        JComboBox<String> vehicleBox = new JComboBox<>(vehicles);

        JLabel luggageLabel = new JLabel("  Amount of luggage:");
        String[] luggage = {"None", "Small", "Large"};
        JComboBox<String> luggageBox = new JComboBox<>(luggage);

        JLabel nameLabel = new JLabel ("  Customer Name:");
        JTextField nameField = new JTextField();

        JLabel distanceLabel = new JLabel("  Distance to airport (miles):");
        JTextField distanceField = new JTextField();

        JLabel dateLabel = new JLabel("  New date:");
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        JLabel timeLabel = new JLabel("  New hour (0-23): ");
        JTextField timeField = new JTextField();

        JButton amendButton = new JButton("Amend Booking");
        JButton cancelButton = new JButton("Cancel Booking");

        //adds the buttons/boxes to the UI
        add(bookingLabel); add(bookingBox);
        add(vehicleLabel); add(vehicleBox);
        add(luggageLabel); add(luggageBox);
        add(nameLabel); add(nameField);
        add(distanceLabel); add(distanceField);
        add(dateLabel); add(dateSpinner);
        add(timeLabel); add(timeField);
        add(amendButton); add(cancelButton);

        // Amend booking
        amendButton.addActionListener(e -> {
            try {
                int selectedIndex = bookingBox.getSelectedIndex();
                Booking booking = Main.bookings.get(selectedIndex);

                // Check whether booking has passed
                if (LocalDate.now().isAfter(booking.getBookingDate())) {
                    JOptionPane.showMessageDialog(this,
                            "Cannot amend a booking that has already passed!");
                    return;
                }

                String vehicleType = (String) vehicleBox.getSelectedItem();
                String luggageAmount = (String) luggageBox.getSelectedItem();
                String customerName = nameField.getText();
                int distanceToAirport = Integer.parseInt(distanceField.getText());
                int hour =  Integer.parseInt(timeField.getText());

                // get new date and day from spinner
                java.util.Date selectedDate = (java.util.Date) dateSpinner.getValue();
                LocalDate newDate = new java.sql.Date(selectedDate.getTime()).toLocalDate();
                String newDay = newDate.getDayOfWeek().getDisplayName(java.time.format.TextStyle.FULL,
                        java.util.Locale.ENGLISH);

                // calculate the amendment fee and recalculate the cost of the booking based on the new details
                double amendmentFee = FeeCalculator.calculateAmendmentFee(booking.getBookingDate());
                double newCost = TariffCalculator.calculateCost(vehicleType, luggageAmount, distanceToAirport,
                        newDay, hour);

                double difference = newCost - booking.getCost();

                booking.setCustomerName(customerName);
                booking.setVehicleType(vehicleType);
                booking.setLuggageAmount(luggageAmount);
                booking.setDistanceToAirport(distanceToAirport);
                booking.setBookingDate(newDate);
                booking.setDayOfWeek(newDay);
                booking.setTimeOfDay(hour);
                booking.setCost(newCost);


                if(difference < 0) {
                    // Old cost is more than new cost
                    double netRefund = Math.abs(difference) - amendmentFee;

                    if (netRefund > 0) {
                        // Refund is more than amendment fee
                        JOptionPane.showMessageDialog(this, String.format("New cost is lower .\n" +
                                        "Refund: £%.2f\nAmendment fee: £%.2f\nNet refund: £%.2f", Math.abs(difference),
                                amendmentFee, Math.abs(difference) - amendmentFee));
                        dispose();
                    } else if (amendmentFee > 0) {
                        // Amendment fee is more than refund, so no refund but amendment fee applies
                        double amountToPay = amendmentFee - Math.abs(difference);
                        JOptionPane.showMessageDialog(this, String.format("Requires amendment fee.\n" +
                                "Amendment fee: £%.2f\nNo refund available.", amendmentFee));
                        new PaymentFrame(amountToPay, customerName, vehicleType, luggageAmount, distanceToAirport,
                                newDay, hour, booking.getCustomerEmail(), booking.getCustomerNumber(), false,
                                booking.getPickupLocation(), booking.getDropOffLocation(), newDate,
                                newCost,true);
                        dispose();
                    } else {
                        // No amendment fee and new cost is lower, so just refund the difference
                        JOptionPane.showMessageDialog(this, String.format("New cost is lower .\n" +
                                        "Refund: £%.2f\nNo amendment fee applies.\nNet refund: £%.2f", Math.abs(difference),
                                Math.abs(difference)));
                        dispose();
                    }
                } else if (difference == 0) {
                    // Same cost - only pay amendment fee
                    if (amendmentFee > 0) {
                        new PaymentFrame(amendmentFee, customerName, vehicleType, luggageAmount, distanceToAirport,
                                newDay, hour, booking.getCustomerEmail(), booking.getCustomerNumber(), booking.getSms(),
                                booking.getPickupLocation(), booking.getDropOffLocation(), newDate,
                                newCost,true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Booking amended. No payment required.");
                        dispose();
                    }
                } else {
                    // New cost is higher, so pay the difference + amendment fee
                    double amountToPay = difference + amendmentFee;
                    JOptionPane.showMessageDialog(this, String.format("New cost is higher.\n" +
                                    "Additional amount to pay: £%.2f\nAmendment fee: £%.2f\nTotal amount to pay: £%.2f",
                            difference, amendmentFee, amountToPay));
                    new PaymentFrame(amountToPay, customerName, vehicleType, luggageAmount, distanceToAirport,
                            newDay, hour, booking.getCustomerEmail(), booking.getCustomerNumber(), booking.getSms(),
                            booking.getPickupLocation(), booking.getDropOffLocation(), newDate,
                            newCost,true);
                    dispose();
                }


            } catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all fields correctly!");
                return;
            }
        });

        // Cancel booking
        cancelButton.addActionListener(e -> {
            try{
                int selectedIndex = bookingBox.getSelectedIndex();
                Booking booking = Main.bookings.get(selectedIndex);

                // Check whether booking has passed
                if (LocalDate.now().isAfter(booking.getBookingDate())) {
                    JOptionPane.showMessageDialog(this,
                            "Cannot cancel a booking that has already passed!");
                    return;
                }

                double refund = FeeCalculator.calculateCancellationRefund(booking.getBookingDate(),
                        booking.getTimeOfDay(), booking.getCost());

                LocalDateTime bookingDateTime = booking.getBookingDate().atTime(booking.getTimeOfDay(), 0);
                LocalDateTime currentDateTime = LocalDateTime.now();
                long hoursBetween = java.time.temporal.ChronoUnit.HOURS.between(currentDateTime, bookingDateTime);

                // Remove booking
                Main.bookings.remove(selectedIndex);

                BookingConfirmation.cancellationMessage(
                        booking.getCustomerName(),
                        booking.getCustomerEmail(),
                        booking.getCustomerNumber(),
                        booking.getSms(),
                        booking.getPickupLocation(),
                        booking.getDropOffLocation(),
                        booking.getBookingDate(),
                        String.valueOf(booking.getTimeOfDay())
                );

                JOptionPane.showMessageDialog(this, String.format("Booking cancelled.\n" +
                        "Hours until booking: %d\nRefund amount: £%.2f", hoursBetween, refund));

                dispose();

            } catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please select a booking to cancel!");
                return;
            }
        });

        setVisible(true);
    }

}
