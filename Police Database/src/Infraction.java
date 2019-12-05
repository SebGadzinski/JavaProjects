import java.util.Date;
public class Infraction{
    float amount;
    String description;
    Date dateIssued;
    boolean outstanding;
    Driver driver;
    String statment;
    public Infraction(float a, String b, Date c){
        amount = a;
        description = b;
        dateIssued = c;
        outstanding = true;
        driver = null;
        statment = "[OUTSTANDING]";
    }
    public Infraction(){
        amount = 0.0f;
        description = "Unknown";
        dateIssued = null;
        outstanding = true;
        driver = null;
        statment = "[OUTSTANDING]";
    }

    public String toString() {
        return String.format("$%.2f ", amount) + "Infraction on " + String.format("%tc", dateIssued) + " " + statment;
    }
    public void pay(){// if the infraction gets paid make it not outstanding, ie. false
        outstanding = false;
        statment = "[PAID IN FULL]";

    }
}
