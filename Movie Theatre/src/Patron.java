public class Patron {
    int age;
    Ticket ticket;
    public Patron(int n){ // constructor that takes the age of the patron
        ticket = null;   // patron starts with a null ticket
        age = n;
    }
}
