import java.util.Calendar;
import  java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class ExpiringPolicy extends Policy {
    private Date expiryDate;
    public SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM dd, yyyy (hh:mma)");
    public ExpiringPolicy(float a, Date d) {
        super(a);
        expiryDate = d;
    }

    public ExpiringPolicy(float a) {
        super(a);
        GregorianCalendar aCalendar = new GregorianCalendar();
        aCalendar.add(Calendar.YEAR, 1);
        expiryDate = aCalendar.getTime();
    }

    public Date getExpiryDate() { return expiryDate; }
    public String toString(){
        if (isExpired())
            return "Expiring" + super.toString() + " expired on: " + dateFormatter.format(expiryDate);
        else
            return "Expiring" + super.toString() + " expires: " + dateFormatter.format(expiryDate);
    }
    public boolean isExpired(){
        Date currentDate = new Date();
        if (expiryDate.before(currentDate)){
            return true;
        }
        else
            return false;
    }
    public float handleClaim(){
        if (this.isExpired())
            return 0.00f;
        else
            return amount;}
}


