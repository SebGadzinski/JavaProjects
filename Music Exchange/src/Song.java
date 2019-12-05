public class Song implements  Comparable<Song>{
    private String      title;
    private String      artist;
    private int         duration;
    public User         owner ;
    public Song()  {
        this("", "", 0, 0);
    }

    public boolean equals(Song obj) {

        return title.equals(obj.title);
    }
    public int hashCode() {
        return title.hashCode();
    }
    public int compareTo(Song m) {
        return title.compareTo(m.title);
    }


    public Song(String t, String a, int m, int s)  {
        title = t;
        artist = a;
        duration = m * 60 + s;
        owner = null;
    }

    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public int getDuration() { return duration; }

    public int getMinutes() {
        return duration / 60;
    }

    public int getSeconds() {
        return duration % 60;
    }

    public String toString()  {
        return("\"" + title + "\" by " + artist + " " + (duration / 60) + ":" + (duration%60));
    }
}