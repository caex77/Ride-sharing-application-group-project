import java.time.LocalDate;

public class Booking {
    //information stored about the booking
    private String bookingRef;
    private String customerName;
    private String customerEmail;
    private String customerNumber;
    private Boolean sms;
    private String vehicleType;
    private String luggageAmount;
    private int distanceToAirport;
    private String dayOfWeek;
    private LocalDate bookingDate;
    private int timeOfDay;
    private String pickupLocation;
    private String dropOffLocation;
    private double tripDuration;
    private double cost;

    public Booking(String bookingRef, String customerName, String customerEmail, String customerNumber, Boolean sms,
                   String vehicleType, String luggageAmount, int distanceToAirport,
                   String dayOfWeek, LocalDate bookingDate, int timeOfDay, String pickupLocation,
                   String dropOffLocation,  double tripDuration,  double cost) {
        this.bookingRef = bookingRef;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerNumber = customerNumber;
        this.sms = sms;
        this.vehicleType = vehicleType;
        this.luggageAmount = luggageAmount;
        this.distanceToAirport = distanceToAirport;
        this.dayOfWeek = dayOfWeek;
        this.bookingDate = bookingDate;
        this.timeOfDay = timeOfDay;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.tripDuration = tripDuration;
        this.cost = cost;
    }



    //getters for the booking information
    public String getBookingRef() {
        return bookingRef;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public String getCustomerNumber() {
        return customerNumber;
    }
    public Boolean getSms() {
        return sms;
    }
    public String getVehicleType() {
        return vehicleType;
    }
    public String getLuggageAmount() {
        return luggageAmount;
    }
    public int getDistanceToAirport() {
        return distanceToAirport;
    }
    public String getDayOfWeek() {
        return dayOfWeek;
    }
    public LocalDate getBookingDate() {
        return bookingDate;
    }
    public int getTimeOfDay() {
        return timeOfDay;
    }
    public String getPickupLocation() {
        return pickupLocation;
    }
    public String getDropOffLocation() {
        return dropOffLocation;
    }
    public double getTripDuration() {
        return tripDuration;
    }
    public double getCost() {
        return cost;
    }

    // setters for booking
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
    public void setSms(Boolean sms) {
        this.sms = sms;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    public void setLuggageAmount(String luggageAmount) {
        this.luggageAmount = luggageAmount;
    }
    public void setDistanceToAirport(int distanceToAirport) {
        this.distanceToAirport = distanceToAirport;
    }
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
    public void setTimeOfDay(int timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }
    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }
    public void setTripDuration(double tripDuration) {
        this.tripDuration = tripDuration;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }


}