public class Vehicle{
    String make;
    String model;
    int year;
    String color;
    String plate;
    Driver owner;
    boolean reportedStolen;

    public Vehicle(String a, String b, int c, String d, String e) {
        make = a;
        model = b;
        year = c;
        color = d;
        plate = e;
        owner = null;
        reportedStolen = false;
    }

    public Vehicle() {
        make = "Unknown";
        model = "Uknown";
        year = 0;
        color = "Unknown";
        plate = "Unknown";
        owner = null;
        reportedStolen = false;
    }

    public String toString() {
        return "A " + color + " " + year + " " + make +" " + model + " with plate " + plate;
    }
}