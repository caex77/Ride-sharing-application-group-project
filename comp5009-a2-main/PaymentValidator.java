import javax.swing.*;

public class PaymentValidator {

    /** Validates credit card details
     *
     * @param cardNumber The credit card number entered by the user.
     * @param expiryDate The expiry date of the credit card in MM/YY format.
     * @param cvv        The CVV code of the credit card.
     * @return           An error message if validation fails, or null if validation is successful.
     */
    public static String validateCard(String cardNumber, String expiryDate, String cvv) {
        if (cardNumber.isEmpty() || expiryDate.isEmpty() || cvv.isEmpty()) {
            return "Please fill in all the fields!";
        }
        if (!cardNumber.matches("\\d{16}")) {
            return "Card number must contain 16 digits!";
        }
        if (!expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            return "Expiry date must be in MM/YY format!";
        }
        if (!cvv.matches("\\d{3}")) {
            return "CVV must contain 3 digits!";
        }
        return null;
    }

}
