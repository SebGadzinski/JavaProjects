import java.util.*;

public abstract class Client {
    private static final int MAX_POLICIES_PER_CLIENT = 10;

    private static int NEXT_CLIENT_ID = 1;

    private String name;
    private int id;
    protected Policy[] policies;
    protected int numPolicies;

    public Client(String n) {
        name = n;
        id = NEXT_CLIENT_ID++;
        policies = new Policy[MAX_POLICIES_PER_CLIENT];
        numPolicies = 0;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Policy[] getPolicies() {
        return policies;
    }

    public int getNumPolicies() {
        return numPolicies;
    }

    public String toString() {
        return String.format(this.getClass() + " %06d amount: %s", id, name);
    }

    public float totalCoverage() {
        float trackAmount = 0;
        for (int i = 0; i < numPolicies; i++) {// Gather all the amounts and add them up
            trackAmount += policies[i].amount;
        }
        return trackAmount;
    }

    public Policy addPolicy(Policy p) {
        if (numPolicies < MAX_POLICIES_PER_CLIENT) {//checks and see's if its full not adds the policies
            policies[numPolicies] = p;
            numPolicies += 1;
            return p;
        } else
            return null;
    }

    public Policy openPolicyFor(float amt) {
        Policy current = new Policy(amt);
        return addPolicy(current);
    }

    public Policy openPolicyFor(float amt, float rate) { // Might have to do deprecciating policy
        DepreciatingPolicy current = new DepreciatingPolicy(amt, rate);
        return addPolicy(current);
    }

    public Policy openPolicyFor(float amt, Date expire) {
        ExpiringPolicy current = new ExpiringPolicy(amt, expire);
        return addPolicy(current);
    }

    public Policy getPolicy(int polNum) {
        for (int i = 0; i < numPolicies; i++) {//if its the same policy number then return the policy
            if (policies[i].getPolicyNumber() == polNum)
                return policies[i];
        }
        return null;
    }

    public boolean cancelPolicy(int polNum) {

        for (int i = 0; i < numPolicies; i++) {//if it does equal the policy number take the last policy then make it the policy that you took out
            if (policies[i].getPolicyNumber() == polNum) {
                policies[i] = policies[numPolicies - 1];
                policies[numPolicies - 1] = null;
                numPolicies -= 1;
                return true;
            }

        }
        return false;
    }
        public abstract float makeClaim ( int polNum);



}
