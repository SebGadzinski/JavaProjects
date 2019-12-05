public class Theatre {
    Movie moviePlaying; // current movie playing
    int seatsSold;
    int capacity;
    public Theatre(){
        moviePlaying = null; // when created there is no movie playing
        seatsSold = 0;
        capacity = 0;
    }
    public Theatre(int n){// constructor that takes the capacity of a theatre
        moviePlaying = null;
        seatsSold = 0;
        capacity = n;
    }
    public boolean isFull(){ // function isFull is meant to see if there is no more space within the theatre
       if (seatsSold >= capacity) { // if the seats sold is equal to or greater than the capacity that means its full
           return true;
       }
       else { // if not its not false meaning not full
           return false;
       }
    }

}
