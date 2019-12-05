public class IndividualClient extends Client {
    public IndividualClient(String n) {
        super(n);

    }

    @Override
    public float makeClaim(int polNum) {// first find the policy with the policy number then see what instance it is from
        Policy current;
        current = getPolicy(polNum);
        if (current instanceof DepreciatingPolicy){
            ((DepreciatingPolicy) current).depreciate();//polymorphism
            if (current.amount <= 0){
                cancelPolicy(polNum);
                return 0;
            }
            return current.amount;

        }
        if (current instanceof ExpiringPolicy){
            if (((ExpiringPolicy)current).isExpired()){//polymorphism
                return 0;
            }
            else{
                return current.amount;
            }
        }
        else if (current instanceof Policy){
            float amnt = current.amount;
            cancelPolicy(polNum);
            return amnt;
        }
        return 0;


    }


}
