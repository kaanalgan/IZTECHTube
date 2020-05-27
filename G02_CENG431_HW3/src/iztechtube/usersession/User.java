package iztechtube.usersession;

import iztechtube.exceptions.WatchlistAlreadyPresentException;
import iztechtube.watchlistvideosbrowser.Watchlist;

import java.util.*;


public class User extends Observable {

    private String username;

    private String password;

    private List<String> followings;

    private List<String> followers;

    private List<Integer> likedVideos;

    private List<Integer> dislikedVideos;


    private List<Watchlist> watchlists;

    public User(String username, String password, List<String> following, List<String> followers, List<Integer> likedVideos, List<Integer> dislikedVideos, List<Watchlist> watchlists) {
        this.username = username;
        this.password = password;
        this.followings = following;
        this.followers = followers;
        this.likedVideos = likedVideos;
        this.dislikedVideos = dislikedVideos;
        this.watchlists = watchlists;
    }

    public User(User user){
        this(user.getUsername(), user.getPassword(), user.getFollowing(), user.getFollowers(), user.getLikedVideos(), user.getDislikedVideos(), user.getWatchlists());
    }

    public User(){
        this("no-name", "123456", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getFollowing() {
        List<String> copyFollowings = new ArrayList<>(followings.size());
        copyFollowings.addAll(followings);
        return copyFollowings;
    }


    public List<String> getFollowers(){
        List<String> copyFollowers = new ArrayList<>(followers.size());
        copyFollowers.addAll(followers);
        return copyFollowers;
    }

    public boolean removeFromDislikes(int videoId){
        if(dislikedVideos.contains(videoId)){
            dislikedVideos.remove((Integer) videoId);
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }

    public boolean removeFromLikes(int videoId){
        if(likedVideos.contains(videoId)){
            likedVideos.remove((Integer) videoId);
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }

    public boolean addToLikes(int videoId){
        if(!likedVideos.contains(videoId)){
            likedVideos.add(videoId);
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }

    public boolean addToDislikes(int videoId){
        if(!dislikedVideos.contains(videoId)){
            dislikedVideos.add(videoId);
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }

    public void follow(String user){
        if(!followings.contains(user)){
            followings.add(user);
            setChanged();
            notifyObservers();
        }
    }

    public List<Watchlist> getWatchlists() {
        return watchlists;
    }

    public void setWatchlist(Watchlist watchlist){
        if(watchlists == null){
            throw new IllegalArgumentException("Watchlist cannot be null");
        }
        for(int i=0; i<watchlists.size(); i++){
            if(watchlists.get(i).getName().equals(watchlist.getName())){
                watchlists.set(i, watchlist);
                setChanged();
                notifyObservers();
                break;
            }
        }
    }

    public void unfollow(String user) {
        if(followings.contains(user)){
            followings.remove(user);
            setChanged();
            notifyObservers();
        }
    }

    public void addWatchlist(Watchlist watchlist) throws WatchlistAlreadyPresentException {
        if(watchlist == null){
            throw new IllegalArgumentException("Watchlist to add cannot be null.");
        }
        if(!getWatchlistNames().contains(watchlist.getName())){
            watchlists.add(watchlist);
            setChanged();
            notifyObservers();
        }else{
            throw new WatchlistAlreadyPresentException("Given watchlist is already .");
        }

    }

    public void addFollower(String user) {
        if(!followers.contains(user)){
            followers.add(user);
            setChanged();
            notifyObservers();
        }
    }

    public void removeFollower(String user) {
        if(followers.contains(user)){
            followers.remove(user);
            setChanged();
            notifyObservers();
        }
    }

    public List<Integer> getLikedVideos(){
        List<Integer> copyLikedVideos = new ArrayList<>(likedVideos.size());
        copyLikedVideos.addAll(likedVideos);
        return copyLikedVideos;
    }

    public List<Integer> getDislikedVideos(){
        List<Integer> copyDislikedVideos = new ArrayList<>(dislikedVideos.size());
        copyDislikedVideos.addAll(dislikedVideos);
        return copyDislikedVideos;
    }

    private Set<String> getWatchlistNames(){
        Set<String> names = new HashSet<>();
        for(Watchlist watchlist : watchlists){
            names.add(watchlist.getName());
        }
        return names;
    }

}
