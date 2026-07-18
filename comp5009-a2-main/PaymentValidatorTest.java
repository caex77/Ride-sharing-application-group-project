import org.junit.Test;
import static org.junit.Assert.*;

public class PaymentValidatorTest {

    // Test valid card details
    @Test
    public void testValidCardNumber() {
        assertNull(PaymentValidator.validateCard("1234567812345678", "12/25", "123"));
    }

    // Test empty card number field
    @Test
    public void testEmptyCardNumber() {
        assertEquals("Please fill in all the fields!", PaymentValidator.validateCard("", "12/25", "123"));
    }

    // Test empty expiry date field
    @Test
    public void testEmptyExpiryDate() {
        assertEquals("Please fill in all the fields!", PaymentValidator.validateCard("1234567812345678", "", "123"));
    }

    // Test empty cvv field
    @Test
    public void testEmptyCVV() {
        assertEquals("Please fill in all the fields!", PaymentValidator.validateCard("1234567812345678", "12/25", ""));
    }

    // Test invalid card number field
    @Test
    public void testInvalidCardNumber() {
        assertEquals("Card number must contain 16 digits!", PaymentValidator.validateCard("12345678", "12/25", "123"));
    }

    // Test invalid expiry date field
    @Test
    public void testInvalidExpiryDate() {
        assertEquals("Expiry date must be in MM/YY format!", PaymentValidator.validateCard("1234567812345678", "1225", "123"));
    }

    // Test invalid cvv field
    @Test
    public void testInvalidCVV() {
        assertEquals("CVV must contain 3 digits!", PaymentValidator.validateCard("1234567812345678", "12/25", "12"));
    }

}