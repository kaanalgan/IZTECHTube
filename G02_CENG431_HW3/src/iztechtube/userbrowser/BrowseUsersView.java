package iztechtube.userbrowser;

import iztechtube.FrameManager;
import iztechtube.usersession.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class BrowseUsersView implements Observer {

    private List<String> usernames;
    private User user;
    private FrameManager frame;
    private JList<String> jList;
    private JButton followButton, unfollowButton, mainMenuButton;
    private JPanel panel;
    private JScrollPane scrollPane;

    public BrowseUsersView(FrameManager frame, List<String> usernames, User user){
        this.usernames = usernames;
        this.user = user;
        this.frame = frame;
        showBrowseUsers();
        user.addObserver(this);
    }

    private void showBrowseUsers(){

        panel = new JPanel(new GridLayout(3, 1));
        panel.setLayout(null);

        JLabel title = new JLabel("Users");
        title.setBounds(10, 10, 80, 25);
        panel.add(title);

        scrollPane = new JScrollPane();

        jList = new JList<>();
        scrollPane.setViewportView(jList);

        setJList();

        scrollPane.setBounds(10, 40, 420, 500);
        panel.add(scrollPane);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBounds(310, 560, 120, 35);
        followButton = new JButton("Follow User");
        followButton.setBounds(10, 560, 130, 35);
        unfollowButton = new JButton("Unfollow User");
        unfollowButton.setBounds(160, 560, 130, 35);

        panel.add(followButton);
        panel.add(unfollowButton);
        panel.add(mainMenuButton);

        frame.setNewPanel(panel);
    }

    public void addFollowActionListener(ActionListener actionListener) {
        followButton.addActionListener(actionListener);
    }

    public void addUnfollowActionListener(ActionListener actionListener) {
        unfollowButton.addActionListener(actionListener);
    }

    public void addMainMenuActionListener(ActionListener actionListener) {
        mainMenuButton.addActionListener(actionListener);
    }

    public String getSelectedUsername(){
        int i = jList.getSelectedIndex();
        if(0 <= i && i < usernames.size()){
            return usernames.get(i);
        }else{
            return null;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        user = (User) o;
        setJList();
    }

    private void setJList(){
        String[] jListItems = new String[usernames.size()];
        int i = 0;
        usernames.remove(user.getUsername());
        for(String u : usernames){
            String following = "- Not Following";
            if (user.getFollowing().contains(u)){
                following = "- Following";
            }
            jListItems[i] = "<html><body>"  + u + "   " + following + "<br>" + " " + "<br>" + "</span></body></html>}"; ;
            i++;

        }
        jList.setListData(jListItems);
    }

}


