public class CompanyClient extends Client {
    public CompanyClient(String n){
        super(n);
    }

    @Override
    public float makeClaim(int polNum) {
        Policy current;
        current = getPolicy(polNum);
        if (current == null)
            return 0;
        return current.handleClaim();
    }
}
