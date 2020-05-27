package iztechtube.video;

import iztechtube.FrameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class VideoView implements Observer {
    private FrameManager frame;
    private JPanel panel;
    private Video video;
    private JTextField commentField;
    private JButton dislikeButton;
    private JButton likeButton;
    private JButton commentButton;
    private JButton addToWatchlistButton;
    private JButton backButton;
    private JLabel audience;
    private JLabel date;
    private JLabel likeCount;
    private JLabel dislikeCount;
    private JList<String> commentJList;
    JScrollPane scrollPane;

    public VideoView(FrameManager frame, Video video) {
        this.video = video;
        this.frame = frame;
        showVideoView();
    }

    public void showVideoView(){
        panel = new JPanel(new GridLayout(3, 1));
        panel.setLayout(null);

        JLabel videoTitle = new JLabel(video.getTitle());
        videoTitle.setBounds(10, 10, 80, 25);
        panel.add(videoTitle);

        JTextArea content = new JTextArea(video.getContent());
        content.setEditable(false);
        content.setBounds(10, 40, 420, 200);
        panel.add(content);

        audience = new JLabel(("Audience: " + video.getAudience().name()));
        audience.setBounds(30, 250, 150, 25);
        panel.add(audience);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = new JLabel(("Date: " + dateFormat.format(video.getDate())));
        date.setBounds(280, 250, 200, 25);
        panel.add(date);

        likeCount = new JLabel((video.getLikes() + ""));
        likeCount.setBounds(100, 290, 80, 25);
        panel.add(likeCount);

        dislikeCount = new JLabel((video.getDislikes() + ""));
        dislikeCount.setBounds(230, 290, 80, 25);
        panel.add(dislikeCount);

        dislikeButton = new JButton("Dislike");
        dislikeButton.setBounds(140, 290, 80, 25);
        panel.add(dislikeButton);

        likeButton = new JButton("Like");
        likeButton.setBounds(10, 290, 80, 25);
        panel.add(likeButton);

        addToWatchlistButton = new JButton("Add to watchlist");
        addToWatchlistButton.setBounds(290, 290, 140, 25);
        panel.add(addToWatchlistButton);

        commentField = new JTextField(20);
        commentField.setBounds(10, 340, 320, 25);
        panel.add(commentField);

        commentButton = new JButton("Comment");
        commentButton.setBounds(340, 340, 90, 25);
        panel.add(commentButton);

        backButton = new JButton("Back");
        backButton.setBounds(350, 5, 80, 25);
        panel.add(backButton);

        commentJList = new JList<>();

        scrollPane = new JScrollPane(commentJList);
        scrollPane.setBounds(10, 380, 420, 250);
        panel.add(scrollPane);

        setCommentList();

        frame.setNewPanel(panel);
    }

    public void displayMessage(String message){ JOptionPane.showMessageDialog(frame.getFrame(), message); }

    public void addDislikeActionListener(ActionListener actionListener) {
        dislikeButton.addActionListener(actionListener);
    }

    public void addLikeActionListener(ActionListener actionListener) {
        likeButton.addActionListener(actionListener);
    }

    public void addCommentActionListener(ActionListener actionListener) {
        commentButton.addActionListener(actionListener);
    }

    public void addAddToWatchlistActionListener(ActionListener actionListener){
        addToWatchlistButton.addActionListener(actionListener);
    }

    public void addBackActionListener(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    public String getCommentText(){
        return commentField.getText();
    }


    public String inputFromAListOfValues(String title, String[] choices, String message){
        return (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
    }

    public Video getVideo(){ return video; }

    private void setCommentList(){
        String[] commentsArr = new String[video.getComments().size()];
        int i = 0;
        for(Comment comment : reverseList(video.getComments())){
            commentsArr[i] = "<html><body>" + comment.getUsername() + "<br>" + comment.getComment()
                    + "<br>" + " " + "<br>" + "</span></body></html>}";
            i++;
        }
        commentJList.setListData(commentsArr);
    }

    public void cleanCommentField(){
        commentField.setText("");
    }

    @Override
    public void update(Observable o, Object arg) {
        video = (Video) o;
        likeCount.setText(video.getLikes() + "");
        dislikeCount.setText(video.getDislikes() + "");
        setCommentList();
    }

    private Comment[] reverseList(List<Comment> commentList){
        List<Comment> reversed = new ArrayList<>(commentList);
        Collections.reverse(reversed);
        Comment[] comments = {};
        comments = reversed.toArray(comments);
        return comments;
    }


}
