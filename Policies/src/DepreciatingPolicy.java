public class DepreciatingPolicy extends Policy {
    private float rate;
    public DepreciatingPolicy(float a, float r){
        super(a);
        rate = r;
    }
    public float getRate() { return rate; }


    public boolean  isExpired(){
        return amount == 0.00f || amount <= 0.00f;
    }
    public void depreciate(){
        amount -= amount*rate;
    }
    public String toString(){
        return "Depreciating" + super.toString() + String.format(" rate: %1.1f", rate*100)+ "%";
    }
    public float handleClaim(){
        float amountBefore = amount;
        this.depreciate();
        return amountBefore;}
}
