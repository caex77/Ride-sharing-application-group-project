import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class FeeCalculatorTest {

    // Test amendment fee for amendment made more than three days from booking date
    @Test
    public void testAmendmentFeeMoreThanThreeDays() {
        LocalDate fourDays = LocalDate.now().plusDays(4);
        assertEquals(0.0, FeeCalculator.calculateAmendmentFee(fourDays), 0.01);
    }

    // Test amendment fee for amendment made exactly three days from booking date
    @Test
    public void testAmendmentFeeEqualToThreeDays() {
        LocalDate threeDays = LocalDate.now().plusDays(3);
        assertEquals(5.0, FeeCalculator.calculateAmendmentFee(threeDays), 0.01);
    }

    // Test amendment fee for amendment made less than three days from booking date
    @Test
    public void testAmendmentFeeLessThanThreeDays() {
        LocalDate twoDays = LocalDate.now().plusDays(2);
        assertEquals(5.0, FeeCalculator.calculateAmendmentFee(twoDays), 0.01);
    }

    // Test cancellation refund for cancellation made more than three days from booking date
    @Test
    public void testCancellationRefundMoreThanThreeDays() {
        LocalDate fourDays = LocalDate.now().plusDays(4);
        assertEquals(100, FeeCalculator.calculateCancellationRefund(fourDays, 12, 100.0), 0.01);
    }

    //Test cancellation refund for cancellation made exactly three days from booking date
    @Test
    public void testCancellationRefundExactThreeDays() {
        LocalDate threeDays = LocalDate.now().plusDays(3);
        assertEquals(75.0, FeeCalculator.calculateCancellationRefund(threeDays, 12, 100.0), 0.01);
    }

    // Test cancellation refund for cancellation made exactly two days from booking date
    @Test
    public void testCancellationRefundExactTwoDays() {
        LocalDate twoDays = LocalDate.now().plusDays(2);
        assertEquals(50.0, FeeCalculator.calculateCancellationRefund(twoDays, 12, 100.0), 0.01);
    }

    // Test cancellation refund for cancellation made exactly one day from booking date
    @Test
    public void testCancellationRefundExactOneDay() {
        LocalDate oneDay = LocalDate.now().plusDays(1);
        assertEquals(25.0, FeeCalculator.calculateCancellationRefund(oneDay, 12, 100.0), 0.01);
    }

    // Test cancellation refund for cancellation made same day of booking date
    @Test
    public void testCancellationRefundSameDay() {
        LocalDate today = LocalDate.now();
        assertEquals(0.0, FeeCalculator.calculateCancellationRefund(today, 12, 100.0), 0.01);
    }

}