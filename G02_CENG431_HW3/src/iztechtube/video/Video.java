package iztechtube.video;


import java.util.*;


public class Video extends Observable {


    private int id;
    private String title;
    private String content;
    private Date date;
    private IntendedAudience audience;
    private int likes;
    private int dislikes;
    private List<Comment> comments;

    public Video(int id, String title, String content, Date date, IntendedAudience audience, int likes, int dislikes, List<Comment> comments){
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.audience = audience;
        this.likes = likes;
        this.dislikes = dislikes;
        this.comments = comments;
    }

    public Video(){}

    public void setLikes(int likes) {
        this.likes = likes;
        setChanged();
        notifyObservers();
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
        setChanged();
        notifyObservers();
    }

    public void addComment(Comment comment){
        comments.add(comment);
        setChanged();
        notifyObservers();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public IntendedAudience getAudience() {
        return audience;
    }
}
