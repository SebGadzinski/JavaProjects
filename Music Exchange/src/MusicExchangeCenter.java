import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.*;

public class MusicExchangeCenter {
    public ArrayList<User> users;
    public HashMap royalties;
    private ArrayList<Song> downloadedSongs;

    public MusicExchangeCenter(){
        users = new ArrayList<User>();
        royalties = new HashMap();
        downloadedSongs = new ArrayList<Song>();
    }

    public ArrayList<Song> getDownloadedSongs() {
        return downloadedSongs;
    }

    public ArrayList<User> onlineUsers(){
        ArrayList<User> onlineUsers = new ArrayList<User>();
        for(User u : users){
            if(u.isOnline())
                onlineUsers.add(u);
        }
        return onlineUsers;
    }
    public ArrayList<Song> allAvailableSongs(){
        ArrayList<User> onlinepeeps = onlineUsers();
        ArrayList<Song> userssongs = new ArrayList<Song>();
        for(User u : onlinepeeps){
            for(Song s : u.getSongList()) {
                    userssongs.add(s);
            }

        }
        return userssongs;
    }
    public Song getSong(String title, String ownerName){
        User currentUser= userWithName(ownerName);
        if (currentUser != null) {

            for (Song s: currentUser.getSongList()){
                if (s.getTitle().equals(title)){
                    return s;
                }
            }
            return null;
        }
        return  null;
    }

    @Override
    public String toString() {
        return "Music Exchange Center (" + onlineUsers().size() + " users on-line, "+ allAvailableSongs().size() + " songs available)";
    }
    public User userWithName(String s){
        if (users.size() == 0)
            return  null;
        for(User t: users){
            if (t.getUserName().equals(s)){
                return t;
            }
        }
            return null;
    }
    public void registerUser(User x){
        if (userWithName(x.getUserName()) == null){ users.add(x);}
    }
    public ArrayList<Song>  availableSongsByArtist(String artist){
        ArrayList<Song> userssongs = allAvailableSongs();
        ArrayList<Song> artistsSongs = new ArrayList<Song>();

        for (Song i : userssongs){

            if(i.getArtist().equals(artist)){
                artistsSongs.add(i);

            }
        }
        return artistsSongs;
    }
    public ArrayList<Song>  availableSongsByArtist2(String artist){
        ArrayList<Song> userssongs = getDownloadedSongs();
        ArrayList<Song> artistsSongs = new ArrayList<Song>();

        for (Song i : userssongs){

            if(i.getArtist().equals(artist)){
                artistsSongs.add(i);

            }
        }
        return artistsSongs;
    }

    public void displayRoyalties(){
        Float count;
        ArrayList<String> quick = new ArrayList<>();
        quick.add("Sleepfest");
        quick.add("Clip");
        quick.add("Jaw");
        quick.add("Long Road");
        quick.add("Yeah");
        quick.add("UFO");
        System.out.println("Amount   Artist\n---------------");
        for (String s : quick) {
            count = .25f * availableSongsByArtist2(s).size();
            System.out.println(String.format("$%.2f   %-7s", count, s));
            count = 0.00f;
        }
    }
    public TreeSet<Song> uniqueDownloads(){
        TreeSet<Song> uniqueList = new TreeSet<Song>();
        uniqueList.addAll(getDownloadedSongs());
        return uniqueList;
    }
    public ArrayList<Pair<Integer, Song>> songsByPopularity(){
        ArrayList<Song> currentlist = new ArrayList<Song>(uniqueDownloads().size());
        currentlist.addAll(uniqueDownloads());
        ArrayList<Pair<Integer, Song>> popularSOngs = new ArrayList<Pair<Integer , Song>>();
        int n = 0;
        for (Song u : currentlist){
            n = 0;
            for (Song s : downloadedSongs) {
                if (s.getTitle().equals(u.getTitle())){
                    n++;
                }
            }

            popularSOngs.add(new Pair<>(n,u));
        }
        Collections.sort(popularSOngs, new Comparator<Pair<Integer, Song>>() {
            public int compare(Pair<Integer, Song> p1, Pair<Integer, Song> p2) {
                return (p2.getKey() - p1.getKey());
            }
        });
    return popularSOngs;

    }




    }






