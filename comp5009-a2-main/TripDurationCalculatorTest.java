import org.junit.Test;
import static org.junit.Assert.*;

public class TripDurationCalculatorTest {

    // Test basic trip duration
    @Test
    public void testBasicTripDuration() {
        double result = TripDurationCalculator.calculateDuration(10, 12, 0, "None");
        assertEquals(20.0, result, 0.01);
    }

    // Test peak hour takes longer than off peak
    @Test
    public void testPeakHourTakesLonger() {
        double offPeak = TripDurationCalculator.calculateDuration(10, 12, 0, "None");
        double peak = TripDurationCalculator.calculateDuration(10, 8, 0, "None");

        assertTrue(peak > offPeak);
    }

    // Test evening peak hour also takes longer
    @Test
    public void testEveningPeakHourTakesLonger() {
        double offPeak = TripDurationCalculator.calculateDuration(10, 12, 0, "None");
        double eveningPeak = TripDurationCalculator.calculateDuration(10, 17, 0, "None");

        assertTrue(eveningPeak > offPeak);
    }

    // Test more pickups increases duration
    @Test
    public void testMorePickupsIncreaseDuration() {
        double noPickups = TripDurationCalculator.calculateDuration(10, 12, 0, "None");
        double withPickups = TripDurationCalculator.calculateDuration(10, 12, 3, "None");

        assertTrue(withPickups > noPickups);
    }

    // Test each pickup adds exactly 10 minutes
    @Test
    public void testEachPickupAddsTenMinutes() {
        double resultWithTwo = TripDurationCalculator.calculateDuration(10, 12, 2, "None");
        double resultWithZero = TripDurationCalculator.calculateDuration(10, 12, 0, "None");

        assertEquals(resultWithZero + 20.0, resultWithTwo, 0.01);
    }

    // Test small luggage adds 5 minutes
    @Test
    public void testSmallLuggageAddsFiveMinutes() {
        double base = TripDurationCalculator.calculateDuration(10, 12, 0, "None");
        double result = TripDurationCalculator.calculateDuration(10, 12, 0, "Small");

        assertEquals(base + 5.0, result, 0.01);
    }

    // Test large luggage adds 10 minutes
    @Test
    public void testLargeLuggageAddsTenMinutes() {
        double base = TripDurationCalculator.calculateDuration(10, 12, 0, "None");
        double result = TripDurationCalculator.calculateDuration(10, 12, 0, "Large");

        assertEquals(base + 10.0, result, 0.01);
    }

    // Test large luggage takes longer than small luggage
    @Test
    public void testLargeLuggageTakesLongerThanSmall() {
        double small = TripDurationCalculator.calculateDuration(10, 12, 0, "Small");
        double large = TripDurationCalculator.calculateDuration(10, 12, 0, "Large");

        assertTrue(large > small);
    }

    // Test longer distance takes longer
    @Test
    public void testLongerDistanceTakesLonger() {
        double shortTrip = TripDurationCalculator.calculateDuration(5, 12, 0, "None");
        double longTrip = TripDurationCalculator.calculateDuration(20, 12, 0, "None");

        assertTrue(longTrip > shortTrip);
    }

    // Test zero distance with pickups only returns pickup time
    @Test
    public void testZeroDistanceWithPickups() {
        double result = TripDurationCalculator.calculateDuration(0, 12, 2, "None");

        assertEquals(20.0, result, 0.01);
    }

    // Test hour just outside morning peak does not get peak multiplier
    @Test
    public void testJustOutsideMorningPeakNotSlower() {
        double outsidePeak = TripDurationCalculator.calculateDuration(10, 10, 0, "None");
        double offPeak = TripDurationCalculator.calculateDuration(10, 12, 0, "None");

        assertEquals(offPeak, outsidePeak, 0.01);
    }

    // Test combined peak hour and multiple pickups and large luggage
    @Test
    public void testWorstCaseScenario() {
        double best = TripDurationCalculator.calculateDuration(10, 12, 0, "None");
        double worst = TripDurationCalculator.calculateDuration(10, 8, 3, "Large");

        assertTrue(worst > best);
    }
}