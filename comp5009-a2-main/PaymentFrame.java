import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class PaymentFrame extends JFrame {

    private JTextField cardNumberField;
    private JTextField expiryDateField;
    private JTextField cvvField;
    private String customerName;
    private String vehicle;
    private String luggage;
    private int distance;
    private String day;
    private int hour;
    private String email;
    private String phoneNumber;
    private boolean wantsSms;
    private String pickup;
    private String dropoff;
    private LocalDate bookingDate;
    private double newCost;
    private Boolean isAmendment;


    /** This constructor initializes the payment frame with the specified amount to be paid and creates a new booking
     * when payment has gone through.
     * It sets up the GUI components for displaying the amount, selecting the payment method,
     * and entering card details if the credit card option is selected. It also includes buttons
     * for confirming payment and proceeding to a mobile app for bank transfer. The layout is
     * designed to be user-friendly and responsive to user interactions.
     *
     * @param amount    The cost of the booking that the user needs to pay.
     */
    public PaymentFrame(double amount, String customerName, String vehicle, String luggage, int distance, String day, int hour , String email, String phoneNumber, boolean wantsSms, String pickup, String dropoff, LocalDate bookingDate, double newCost, Boolean isAmendment) {
        this.customerName = customerName;
        this.vehicle = vehicle;
        this.luggage = luggage;
        this.distance = distance;
        this.day = day;
        this.hour = hour;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.wantsSms = wantsSms;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.bookingDate = bookingDate;
        this.newCost = newCost;
        this.isAmendment = isAmendment;


        setTitle("Payment Frame");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Displays the amount to be paid
        JLabel amountLabel = new JLabel("   Amount Due:");
        amountLabel.setPreferredSize(new  Dimension(150, 25));
        JLabel amountValue = new JLabel(String.format("£%.2f", amount));

        // Payment Method Selection
        JLabel paymentMethodTypeLabel = new JLabel("Payment Method Type:");
        paymentMethodTypeLabel.setPreferredSize(new  Dimension(150, 25));
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        radioPanel.setPreferredSize(new  Dimension(400, 25));
        JRadioButton cardButton = new JRadioButton("   Credit Card");
        JRadioButton bankTransferButton = new JRadioButton("   Bank Transfer");
        cardButton.setSelected(true);
        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(cardButton);
        paymentGroup.add(bankTransferButton);
        radioPanel.add(cardButton);
        radioPanel.add(bankTransferButton);

        JPanel radioPanelWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 9, 0));
        radioPanelWrapper.add(paymentMethodTypeLabel);
        radioPanelWrapper.add(radioPanel);

        // Card Details Fields
        JLabel cardNumberLabel = new JLabel("   Card Number:");
        cardNumberLabel.setPreferredSize(new  Dimension(150, 25));
        cardNumberField = new JTextField(20);
        cardNumberField.setPreferredSize(new  Dimension(60, 30));
        JPanel cardNumberFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
        cardNumberFieldPanel.add(cardNumberField);

        JLabel expiryDateLabel = new JLabel("   Expiry Date (MM/YY):");
        expiryDateLabel.setPreferredSize(new  Dimension(150, 25));
        expiryDateField = new JTextField(6);
        expiryDateField.setPreferredSize(new  Dimension(20, 30));
        JPanel expiryDateFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
        expiryDateFieldPanel.add(expiryDateField);

        JLabel cvvLabel = new JLabel("   CVV:");
        cvvLabel.setPreferredSize(new  Dimension(150, 25));
        cvvField = new JTextField(5);
        cvvField.setPreferredSize(new  Dimension(20, 30));
        JPanel cvvFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
        cvvFieldPanel.add(cvvField);

        JButton confirmButton = confirmButton(amount);
        JPanel confirmButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        confirmButtonPanel.add(confirmButton);

        // Bank Transfer Details
        JButton proceedButton = proceedButton(amount);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridLayout(0,2,0,2));
        mainPanel.add(amountLabel);
        mainPanel.add(amountValue);
        mainPanel.add(radioPanelWrapper);

        // Card Panel
        JPanel cardPanel = new JPanel(new GridLayout(4, 2, 0, 0));
        cardPanel.add(cardNumberLabel);
        cardPanel.add(cardNumberFieldPanel);
        cardPanel.add(expiryDateLabel);
        cardPanel.add(expiryDateFieldPanel);
        cardPanel.add(cvvLabel);
        cardPanel.add(cvvFieldPanel);
        cardPanel.add(confirmButtonPanel);

        // Bank Panel
        JPanel bankPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        bankPanel.add(proceedButton);

        // Initially show card details and hide bank transfer details
        bankPanel.setVisible(false);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
        wrapperPanel.add(mainPanel);
        wrapperPanel.add(cardPanel);
        wrapperPanel.add(bankPanel);

        // Toggle visibility based on selection
        cardButton.addActionListener(e -> {
            cardPanel.setVisible(true);
            bankPanel.setVisible(false);
            revalidate();
            repaint();
        });

        bankTransferButton.addActionListener(e -> {
            cardPanel.setVisible(false);
            bankPanel.setVisible(true);
            revalidate();
            repaint();
        });

        add(wrapperPanel);

        setVisible(true);
    }

    /**  This method validates the credit card details entered by the user and simulates the payment process.
     *
     *   @param amount   The cost of the booking.
     *   @return         A JButton that initiates the payment validation and processing when clicked.
     */
    private JButton confirmButton(double amount) {
        JButton confirmButton = new JButton("Confirm Payment");
        confirmButton.addActionListener(e -> {
            String error = PaymentValidator.validateCard(
                    cardNumberField.getText().trim(),
                    expiryDateField.getText().trim(),
                    cvvField.getText().trim()
            );

            if (error != null) {
                JOptionPane.showMessageDialog(this, error, "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                JOptionPane.showMessageDialog(this, "Processing payment...");
                Thread.sleep(1500);
                JOptionPane.showMessageDialog(this, String.format("Payment of £%.2f approved!", amount));

                if (isAmendment) {
                    BookingConfirmation.amendmentMessage(customerName, email, phoneNumber, wantsSms,
                            pickup, dropoff, bookingDate,String.valueOf(hour),vehicle, newCost);
                } else {
                    String bookingRef = BookingConfirmation.newBookingMessage(customerName, email, phoneNumber, wantsSms,
                            pickup, dropoff, bookingDate, String.valueOf(hour), vehicle, amount);
                    Booking booking = new Booking
                            (bookingRef, customerName, email, phoneNumber, wantsSms, vehicle, luggage, distance, day, bookingDate,
                                    hour, pickup, dropoff, 0, amount
                            );
                    Main.bookings.add(booking);
                }
                dispose();
            }  catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(this, "Payment interrupted!");
            }
        });
        return confirmButton;
    }

    /**  This method simulates the process of directing the user to their bank app for payment approval
     *   by showing messages and simulating a delay.
     *
     *   @param amount   The cost of the booking.
     *   @return         A JButton that initiates the simulated bank app process when clicked.
     */
    private JButton proceedButton(double amount) {
        JButton proceedButton = new JButton("Proceed to Mobile App");
        proceedButton.addActionListener(e -> {
            try {
                JOptionPane.showMessageDialog(this, "Directing to your bank app...");
                Thread.sleep(1500);
                JOptionPane.showMessageDialog(this, String.format("Payment of £%.2f approved!", amount));

                if (isAmendment) {
                    BookingConfirmation.amendmentMessage(customerName, email, phoneNumber, wantsSms,
                            pickup, dropoff, bookingDate,String.valueOf(hour),vehicle, newCost);
                } else {
                    String bookingRef = BookingConfirmation.newBookingMessage(customerName, email, phoneNumber, wantsSms,
                            pickup, dropoff, bookingDate, String.valueOf(hour), vehicle, amount);
                    Booking booking = new Booking
                            (bookingRef, customerName, email, phoneNumber, wantsSms, vehicle, luggage, distance, day, bookingDate,
                                    hour, pickup, dropoff, 0, amount
                            );
                    Main.bookings.add(booking);
                }
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An error occurred while proceeding to the mobile app.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        return proceedButton;
    }
}