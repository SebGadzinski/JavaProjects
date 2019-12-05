public class Movie{
    String title;
    float earnings;

    public Movie(String m){//constructor that takes the name of the movie
        title = m;
        earnings = 0.0f;
    }
    public Movie(){ // general constructor
        title = "unknown";
        earnings = 0.0f;
    }
}
