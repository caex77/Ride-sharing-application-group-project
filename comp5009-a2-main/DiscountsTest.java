import static org.junit.jupiter.api.Assertions.*;

//This whole class was made by Bishr Aminu but i had to move it to this file in order to export
class DiscountsTest {


    public static void main(String[] args) {

        // Test 1: Monday at 8am (day discount + time discount)
        System.out.println("Test 1");
        System.out.println("Discount: " +
                Discounts.calculateDiscountPercent("Monday", 8) + "%");
        System.out.println("Final Price: £" +
                Discounts.applyDiscount(100, "Monday", 8));
        System.out.println(Discounts.getDiscountMessage("Monday", 8));
        System.out.println();

        // Test 2: Friday at 8am (time discount only)
        System.out.println("Test 2");
        System.out.println("Discount: " +
                Discounts.calculateDiscountPercent("Friday", 8) + "%");
        System.out.println("Final Price: £" +
                Discounts.applyDiscount(100, "Friday", 8));
        System.out.println(Discounts.getDiscountMessage("Friday", 8));
        System.out.println();

        // Test 3: Tuesday at 2pm (day discount only)
        System.out.println("Test 3");
        System.out.println("Discount: " +
                Discounts.calculateDiscountPercent("Tuesday", 14) + "%");
        System.out.println("Final Price: £" +
                Discounts.applyDiscount(100, "Tuesday", 14));
        System.out.println(Discounts.getDiscountMessage("Tuesday", 14));
        System.out.println();

        // Test 4: Saturday at 2pm (no discount)
        System.out.println("Test 4");
        System.out.println("Discount: " +
                Discounts.calculateDiscountPercent("Saturday", 14) + "%");
        System.out.println("Final Price: £" +
                Discounts.applyDiscount(100, "Saturday", 14));
        System.out.println(Discounts.getDiscountMessage("Saturday", 14));
    }
}
