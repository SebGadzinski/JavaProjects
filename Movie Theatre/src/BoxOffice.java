public class BoxOffice {
    Theatre theatreA = new Theatre(); //create 2 new theatres
    Theatre theatreB = new Theatre();

    public BoxOffice(int a, int b) { //create a constructor
       theatreB.capacity = b;
       theatreA.capacity = a;
    }


    public void openMovie(String d, Theatre c){

        c.moviePlaying = new Movie();//movie playing is always a new new when you open one
        c.moviePlaying.title = d; // sets it to the movie of what they opened
        theatreA.seatsSold = 0; //complete new theatre so it has nobody in it
        theatreB.seatsSold = 0;
    }
    public void sellTicket(Patron n, String c){

        if (c.equals(theatreA.moviePlaying.title)){ // if the ticket says for movie A and if the capacity is not reached
            if ((theatreA.capacity - theatreA.seatsSold) > 0){// then allow them through
                theatreA.seatsSold += 1;
                n.ticket = new Ticket(theatreA);//validate there ticket
                Pay(n.age,theatreA.moviePlaying );//Pay for the ticket
            }
            else
                System.out.println("Movie is sold out");// if the capacity is full tell them the movie is sold out
        }
        else if (c.equals(theatreB.moviePlaying.title)){
            if ((theatreB.capacity - theatreB.seatsSold) >0){
                theatreB.seatsSold += 1;
                n.ticket = new Ticket(theatreB);
                Pay(n.age, theatreB.moviePlaying);
            }
            else
                System.out.println("Movie is sold out");

        }
        else{
            System.out.println("Movie is not currently playing");// if the movie exist in any of the current theatres
        }                                                        // then say its not playing at the moment
    }
    public void returnTicket(Patron n){
        if (n.ticket != null) { // checks if ticket is validated

            if (n.ticket.theatre == theatreB) { //if its for theatre b minus a seat sold for that theatre
                theatreB.seatsSold -= 1;
                n.ticket = null; //make the ticket Invalid
                Refund(n.age, theatreB.moviePlaying);//Refund (really just subtracting the earnings made from the movie

            } else if (n.ticket.theatre == theatreA) {
                theatreA.seatsSold -= 1;
                n.ticket = null;
                Refund(n.age, theatreA.moviePlaying);
            }
        }
        else
            System.out.println("INVLAID"); // if the ticket is invalid say tell the employee and customer
    }
    public Movie bestMovie(){
        if (theatreA.moviePlaying.earnings > theatreB.moviePlaying.earnings){// whichever movie has the highest earnings
            return theatreA.moviePlaying;
        }
        else
            return theatreB.moviePlaying;
    }
    public float Pay(int c, Movie n){// pay function adds money to the movies earnings depending on the age of the patron
        if(c >= 65) { // if older or equal to 65 pay 5.75
            n.earnings += 5.75f;
            return 5.75f;
        }
        else if(c < 12) { // if younger than 12 pay 6.25
            n.earnings += 6.25f;
            return 6.25f;
        }
        else {  // if your not older or equal to 65 or not younger than 12 then you pay 12.50
            n.earnings += 12.50f;
            return 12.50f;
        }
    }
    public void Refund(int c, Movie n){// same concept as the Pay method but instead takin out of the movies earnings
        if(c >= 65) {
            n.earnings -= 5.75f;
        }
        else if(c < 12) {
            n.earnings -= 6.25f;
        }
        else {
            n.earnings -= 12.50f;
        }
    }

}
