import java.util.ArrayList;

public class User {
    private String userName;
    private boolean online;
    private ArrayList<Song> songList;

    public User()  { this(""); }

    public User(String u)  {
        userName = u;
        online = false;
        songList = new ArrayList<Song>();
    }

    public String getUserName() { return userName; }
    public boolean isOnline() { return online; }
    public ArrayList<Song> getSongList() { return songList; }

    public String toString()  {
        String s = "" + userName + ": " + songList.size();
        if (!online) s += "not ";
        return s + "(online)";
    }
    public void addSong(Song s){
        Song t = new Song(s.getTitle(), s.getArtist(), s.getMinutes(), s.getSeconds());
        if (s.owner == null){
            s.owner = this;
            this.getSongList().add(s);
        }
        else{
            t.owner = this;
            this.getSongList().add(t);
        }

    }
    public int totalSongTime(){
        int totalamount=0;
        for(Song s: songList){
            totalamount += s.getSeconds();
        }
        return totalamount;
    }
    public void register(MusicExchangeCenter m){
       m.registerUser(this);
    }
    public void  logon(MusicExchangeCenter m) {
        if(m.users.contains(this))
            m.userWithName(this.getUserName()).online = true;
    }
    public void  logoff(MusicExchangeCenter m) {
        if(m.users.contains(this))
            m.userWithName(this.getUserName()).online = false;
    }
    public ArrayList<String> requestCompleteSonglist(MusicExchangeCenter m){
        ArrayList<Song> userssongs = m.allAvailableSongs();
        ArrayList<String> completeSonglist = new ArrayList<>();
        int n = 0;
        completeSonglist.add("    TITLE                          ARTIST            TIME   OWNER\n");
        for (Song s: userssongs){
            n++;
            completeSonglist.add(String.format("%2d. %-30s %-17s %d:%02d   %-13s ", n, s.getTitle(),s.getArtist(), s.getMinutes(), s.getSeconds(), s.owner.userName));
        }
        return completeSonglist;
    }
    public ArrayList<String> requestSonglistByArtist(MusicExchangeCenter m, String artist){
        ArrayList<Song> userssongs = m.availableSongsByArtist(artist);
        ArrayList<String> completeSonglist = new ArrayList<String>();
        int n = 0;
        completeSonglist.add("    TITLE                          ARTIST            TIME   OWNER\n");
        for (Song s: userssongs){
            n++;
            completeSonglist.add(String.format("%2d. %-30s %-17s %d:%02d   %-13s ", n, s.getTitle(),s.getArtist(), s.getMinutes(), s.getSeconds(), s.owner.userName));
        }
        return completeSonglist;
    }
    public void  downloadSong(MusicExchangeCenter m, String title, String ownerName){

            Song s = new Song();
            s = m.getSong(title,ownerName);
            if (s != null && m.userWithName(ownerName).isOnline()) {
                addSong(s);
                m.getDownloadedSongs().add(s);
            }

        }



    // Various Users for test purposes
    public static User DiscoStew() {
        User  discoStew = new User("Disco Stew");
        discoStew.addSong(new Song("Hey Jude", "The Beatles", 4, 35));
        discoStew.addSong(new Song("Barbie Girl", "Aqua", 3, 54));
        discoStew.addSong(new Song("Only You Can Rock Me", "UFO", 4, 59));
        discoStew.addSong(new Song("Paper Soup Cats", "Jaw", 4, 18));
        return discoStew;
    }

    public static User SleepingSam() {
        User sleepingSam = new User("Sleeping Sam");
        sleepingSam.addSong(new Song("Meadows", "Sleepfest", 7, 15));
        sleepingSam.addSong(new Song("Calm is Good", "Waterfall", 6, 22));
        return sleepingSam;
    }

    public static User RonnieRocker() {
        User ronnieRocker = new User("Ronnie Rocker");
        ronnieRocker.addSong(new Song("Rock is Cool", "Yeah", 4, 17));
        ronnieRocker.addSong(new Song("My Girl is Mean to Me", "Can't Stand Up", 3, 29));
        ronnieRocker.addSong(new Song("Only You Can Rock Me", "UFO", 4, 52));
        ronnieRocker.addSong(new Song("We're Not Gonna Take It", "Twisted Sister", 3, 9));
        return ronnieRocker;
    }

    public static User CountryCandy() {
        User countryCandy = new User("Country Candy");
        countryCandy.addSong(new Song("If I Had a Hammer", "Long Road", 4, 15));
        countryCandy.addSong(new Song("My Man is a 4x4 Driver", "Ms. Lonely", 3, 7));
        countryCandy.addSong(new Song("This Song is for Johnny", "Lone Wolf", 4, 22));
        return countryCandy;
    }

    public static User PeterPunk() {
        User peterPunk = new User("Peter Punk");
        peterPunk.addSong(new Song("Bite My Arms Off", "Jaw", 4, 12));
        peterPunk.addSong(new Song("Where's My Sweater", "The Knitters", 3, 41));
        peterPunk.addSong(new Song("Is that My Toenail ?", "Clip", 4, 47));
        peterPunk.addSong(new Song("Anvil Headache", "Clip", 4, 34));
        peterPunk.addSong(new Song("My Hair is on Fire", "Jaw", 3, 55));
        return peterPunk;
    }
}