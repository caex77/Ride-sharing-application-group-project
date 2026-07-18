Overview
This is my individual contribution to the COMP5009 Software Development Group Project (ride-sharing application). 
I implemented the payment-related functionality: **Pay for New Booking** and **Pay for Amendment and Cancellation**.

Pay for New Booking
- Allows users to pay for a new booking using either a **credit card** or **bank transfer**.
- Calculates the booking fee based on factors such as distance and time of day.

Pay for Amendment and Cancellation
- Allows users to pay for booking amendments and cancellations using the **same payment method** as the original booking.
- Recalculates the booking fee when a booking is amended.
- Calculates the cancellation fee when a booking is cancelled.

How It Works
1. User selects a booking to pay for, amend, or cancel.
2. The system determines the applicable fee using the group's cost/fee formula.
3. Payment is processed via the previously used method (credit card / bank transfer).
4. A confirmation is generated once payment is successful.

Tech Stack
- Java (standalone application with JFrame UI)
- JUnit for testing

Notes
- Fee calculation formulas were defined by the group.
- No live payment gateway integration — payments are simulated for the purposes of this coursework.
