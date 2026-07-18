import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class Main {

    //creates an array list for bookings and info
    static ArrayList<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {

        // Main window
        JFrame frame = new JFrame("Ride Share - Browse Services & Tariffs");
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(18, 2, 10, 10));

        // Vehicle type dropdown
        JLabel vehicleLabel = new JLabel("  Vehicle type:");
        String[] vehicles = {"Saloon", "Estate", "Minivan"};
        JComboBox<String> vehicleBox = new JComboBox<>(vehicles);

        // Luggage dropdown
        JLabel luggageLabel = new JLabel("  Amount of luggage:");
        String[] luggage = {"None", "Small", "Large"};
        JComboBox<String> luggageBox = new JComboBox<>(luggage);

        // Name input
        JLabel nameLabel = new JLabel ("  Customer Name:");
        JTextField nameField = new JTextField();

        // email input
        JLabel emailLabel = new JLabel("  Customer Email:");
        JTextField emailField = new JTextField();

        // phone number input
        JLabel phoneLabel = new JLabel("  Customer Number:");
        JTextField phoneField = new JTextField();

        // send SMS checkbox
        JLabel smsLabel = new JLabel("  Send SMS?");
        JCheckBox smsCheckBox = new JCheckBox();

        // pickup address input
        JLabel pickupLabel = new JLabel("  Pick-up Location:");
        JTextField pickupField = new JTextField();

        // drop-off address input
        JLabel dropOffLabel = new JLabel("  Drop-off Location:");
        JTextField dropOffField = new JTextField();

        // Distance input
        JLabel distanceLabel = new JLabel("  Distance to airport (miles):");
        JTextField distanceField = new JTextField();

        // Day of week dropdown
        JLabel dayLabel = new JLabel("  Day of booking:");
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        // Time of day input
        JLabel timeLabel = new JLabel("  Hour of day (0-23):");
        JTextField timeField = new JTextField();

        // Result label
        JLabel resultLabel = new JLabel("  Estimated cost: £--");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Proceed to Payment Button set to invisible until price is calculated
        JButton payButton = new JButton("Proceed to Payment");
        payButton.setVisible(false);

        //Amend Booking
        JButton amendButton = new JButton("Amend existing booking");

        //Cancel Booking
        JButton cancelButton = new JButton("Cancel existing booking");


        // Calculate button
        final double[] calculatedCost = {0.0};
        JButton calcButton = new JButton("Calculate Price");
        calcButton.addActionListener(e -> {
            try {
                String vehicle = (String) vehicleBox.getSelectedItem();
                String luggageSize = (String) luggageBox.getSelectedItem();
                double distance = Double.parseDouble(distanceField.getText());
                int hour = Integer.parseInt(timeField.getText());

                // get day from spinner
                java.util.Date selectedDate = (java.util.Date) dateSpinner.getValue();
                LocalDate bookingDate = new java.sql.Date(selectedDate.getTime()).toLocalDate();
                String day = bookingDate.getDayOfWeek().toString();

                double cost = TariffCalculator.calculateCost(vehicle, luggageSize, distance, day, hour);
                double discountedCost = Discounts.applyDiscount(cost, day, hour);
                String discountMessage = Discounts.getDiscountMessage(day, hour);

                resultLabel.setText(String.format("  Estimated cost: £%.2f - %s", discountedCost, discountMessage));
                calculatedCost[0] = discountedCost;
                payButton.setVisible(true);

            } catch (NumberFormatException ex) {
                resultLabel.setText("  Please enter valid numbers!");
            }
        });

        // Add everything to the window
        frame.add(vehicleLabel);    frame.add(vehicleBox);
        frame.add(luggageLabel);    frame.add(luggageBox);
        frame.add(nameLabel);       frame.add(nameField);
        frame.add(distanceLabel);   frame.add(distanceField);
        frame.add(dayLabel);        frame.add(dateSpinner);
        frame.add(timeLabel);       frame.add(timeField);
        frame.add(new JLabel(""));  frame.add(calcButton);
        frame.add(resultLabel);     frame.add(new JLabel(""));
        frame.add(payButton);       frame.add(new JLabel(""));
        frame.add(amendButton);    frame.add(new JLabel(""));
        frame.add(cancelButton);    frame.add(new JLabel(""));
        frame.add(emailLabel);      frame.add(emailField);
        frame.add(phoneLabel);      frame.add(phoneField);
        frame.add(smsLabel);        frame.add(smsCheckBox);
        frame.add(pickupLabel);     frame.add(pickupField);
        frame.add(dropOffLabel);    frame.add(dropOffField);

        // Payment button action - opens payment frame to pay for booking
        payButton.addActionListener(e -> {
            String customerName = nameField.getText();
            String vehicleType = (String) vehicleBox.getSelectedItem();
            String luggageAmount = (String) luggageBox.getSelectedItem();
            int distanceToAirport = Integer.parseInt(distanceField.getText());
            int timeOfDay = Integer.parseInt(timeField.getText());
            String customerEmail = emailField.getText();
            String customerNumber = phoneField.getText();
            boolean sendSMS = smsCheckBox.isSelected();
            String pickupLocation = pickupField.getText();
            String dropOffLocation = dropOffField.getText();

            // get date and day from spinner
            java.util.Date selectedDate = (java.util.Date) dateSpinner.getValue();
            LocalDate bookingDate = new java.sql.Date(selectedDate.getTime()).toLocalDate();
            String dayOfWeek = bookingDate.getDayOfWeek().getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH);


            new PaymentFrame(calculatedCost[0],customerName, vehicleType, luggageAmount, distanceToAirport, dayOfWeek,
                    timeOfDay, customerEmail, customerNumber, sendSMS, pickupLocation, dropOffLocation, bookingDate,
                    calculatedCost[0], false);
        });

        amendButton.addActionListener(e -> {
            new AmendBookingFrame();
        });

        cancelButton.addActionListener(e -> {
            new CancelBookingFrame();
        });

        JButton newBookingButton = new JButton("Make New Booking");
        frame.add(newBookingButton);
        frame.add(new JLabel(""));

        newBookingButton.addActionListener(e -> {
            new NewBookingFrame();
        });
        frame.setVisible(true);
    }
}