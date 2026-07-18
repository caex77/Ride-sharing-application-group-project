import org.junit.Test;
import static org.junit.Assert.*;

public class TariffCalculatorTest {

    // Test basic weekday price
    @Test
    public void testBasicWeekdayPrice() {
        double result = TariffCalculator.calculateCost("Saloon", "None", 10, "Monday", 12);
        assertEquals(25.0, result, 0.01);
    }

    // Test weekend costs more than weekday
    @Test
    public void testWeekendCostsMoreThanWeekday() {
        double weekday = TariffCalculator.calculateCost("Saloon", "None", 10, "Monday", 12);
        double weekend = TariffCalculator.calculateCost("Saloon", "None", 10, "Saturday", 12);
        assertTrue(weekend > weekday);
    }

    // Test peak hour costs more than off peak
    @Test
    public void testPeakHourCostsMoreThanOffPeak() {
        double offPeak = TariffCalculator.calculateCost("Saloon", "None", 10, "Monday", 12);
        double peak = TariffCalculator.calculateCost("Saloon", "None", 10, "Monday", 8);
        assertTrue(peak > offPeak);
    }

    // Test large luggage costs more than no luggage
    @Test
    public void testLuggageIncreasesPrice() {
        double noLuggage = TariffCalculator.calculateCost("Saloon", "None", 10, "Monday", 12);
        double largeLuggage = TariffCalculator.calculateCost("Saloon", "Large", 10, "Monday", 12);
        assertTrue(largeLuggage > noLuggage);
    }

    // Test minivan costs more than saloon
    @Test
    public void testMinivanCostsMoreThanSaloon() {
        double saloon = TariffCalculator.calculateCost("Saloon", "None", 10, "Monday", 12);
        double minivan = TariffCalculator.calculateCost("Minivan", "None", 10, "Monday", 12);
        assertTrue(minivan > saloon);
    }

    // Test longer distance costs more
    @Test
    public void testLongerDistanceCostsMore() {
        double shortTrip = TariffCalculator.calculateCost("Saloon", "None", 5, "Monday", 12);
        double longTrip = TariffCalculator.calculateCost("Saloon", "None", 20, "Monday", 12);
        assertTrue(longTrip > shortTrip);
    }
}