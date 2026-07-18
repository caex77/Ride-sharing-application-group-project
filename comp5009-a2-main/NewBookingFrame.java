import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class NewBookingFrame extends JFrame {

    public NewBookingFrame() {
        setTitle("Ride Share - Make New Booking");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(15, 2, 10, 10));

        // Customer name
        JLabel nameLabel = new JLabel("  Customer Name:");
        JTextField nameField = new JTextField();

        // Customer email
        JLabel emailLabel = new JLabel("  Customer Email:");
        JTextField emailField = new JTextField();

        // Customer number
        JLabel numberLabel = new JLabel("  Customer Number:");
        JTextField numberField = new JTextField();

        // Airport dropdown
        JLabel airportLabel = new JLabel("  Airport:");
        String[] airports = {"Heathrow", "Gatwick", "Stansted", "Luton", "Manchester"};
        JComboBox<String> airportBox = new JComboBox<>(airports);

        // Luggage dropdown
        JLabel luggageLabel = new JLabel("  Amount of luggage:");
        String[] luggage = {"None", "Small", "Large"};
        JComboBox<String> luggageBox = new JComboBox<>(luggage);

        // Pickup location
        JLabel pickupLabel = new JLabel("  Pickup Location:");
        JTextField pickupField = new JTextField();

        // Day of week dropdown
        JLabel dayLabel = new JLabel("  Day of booking:");
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        // Time of day
        JLabel timeLabel = new JLabel("  Hour of day (0-23):");
        JTextField timeField = new JTextField();

        // Result label
        JLabel resultLabel = new JLabel("  Estimated cost: £--");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Distance
        JLabel distanceLabel = new JLabel("  Distance to airport (miles):");
        JTextField distanceField = new JTextField();

        // Number of pickups
        JLabel pickupsLabel = new JLabel("  Number of pickups:");
        JTextField pickupsField = new JTextField();

        // SMS Checkbox
        JLabel smsLabel = new JLabel("  Send SMS?");
        JCheckBox smsCheckBox = new JCheckBox();

        // Duration result label
        JLabel durationLabel = new JLabel("  Estimated duration: -- mins");
        durationLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Confirm booking button
        JButton confirmButton = new JButton("Confirm Booking");
        confirmButton.setVisible(false);

        // Calculate button
        JButton calcButton = new JButton("Calculate Trip Duration");
        calcButton.addActionListener(e -> {
            try {
                double distance = Double.parseDouble(distanceField.getText());
                int hour = Integer.parseInt(timeField.getText());
                int pickups = Integer.parseInt(pickupsField.getText());
                String luggageSize = (String) luggageBox.getSelectedItem();

                double duration = TripDurationCalculator.calculateDuration(
                        distance, hour, pickups, luggageSize);


                durationLabel.setText(String.format("  Estimated duration: %.0f mins", duration));
                confirmButton.setVisible(true);

            } catch (NumberFormatException ex) {
                durationLabel.setText("  Please enter valid numbers!");
            }
        });

        final double[] calculatedCost = {0.0};

        // Confirm booking action
        confirmButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String email = emailField.getText();
                String number = numberField.getText();
                String vehicle = "Saloon";
                String luggageSize = (String) luggageBox.getSelectedItem();
                int distance = Integer.parseInt(distanceField.getText());
                java.util.Date selectedDate = (java.util.Date) dateSpinner.getValue();
                LocalDate bookingDate = new java.sql.Date(selectedDate.getTime()).toLocalDate();
                String day = bookingDate.getDayOfWeek().getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH);
                int hour = Integer.parseInt(timeField.getText());
                String pickup = pickupField.getText();
                String dropOff = (String) airportBox.getSelectedItem();
                double duration = TripDurationCalculator.calculateDuration(
                        distance, hour, Integer.parseInt(pickupsField.getText()), luggageSize);
                double amount = TariffCalculator.calculateCost(vehicle, luggageSize, distance, day, hour);

                double discountedCost = Discounts.applyDiscount(amount, day, hour);
                String discountMessage = Discounts.getDiscountMessage(day, hour);
                resultLabel.setText(String.format("  Estimated cost: £%.2f - %s", discountedCost, discountMessage));
                calculatedCost[0] = discountedCost;

                // Create and save booking
                String bookingRef = BookingConfirmation.generateBookingReference();

                Booking booking = new Booking(bookingRef, name, email, number, smsCheckBox.isSelected(), vehicle,
                        luggageSize, distance, day, bookingDate, hour, pickup, dropOff, duration, amount);
                Main.bookings.add(booking);

                JOptionPane.showMessageDialog(this,
                        "Booking confirmed for " + name + "!\n" +
                        "Airport: " + dropOff + "\n" +
                        "Date: " + bookingDate + "\n" +
                        "Estimated duration: " + String.format("%.0f", duration) + " mins",
                        "Booking Confirmed",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields correctly!");
            }
        });

        // Add everything to frame
        add(nameLabel);      add(nameField);
        add(emailLabel);     add(emailField);
        add(numberLabel);    add(numberField);
        add(airportLabel);   add(airportBox);
        add(luggageLabel);   add(luggageBox);
        add(pickupLabel);    add(pickupField);
        add(dayLabel);       add(dateSpinner);
        add(timeLabel);      add(timeField);
        add(distanceLabel);  add(distanceField);
        add(pickupsLabel);   add(pickupsField);
        add(smsLabel);      add(smsCheckBox);
        add(calcButton);     add(durationLabel);
        add(resultLabel);    add(new JLabel(""));
        add(confirmButton);  add(new JLabel(""));

        setVisible(true);
    }
}