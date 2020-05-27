package iztechtube.mainmenu;

import iztechtube.FrameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView {

    FrameManager frame;
    JPanel panel;
    JButton browseUsersButton;
    JButton browseWatchlistsButton;
    JButton browseVideosButton;
    JButton logoutButton;

    public MenuView(FrameManager frame) {
        this.frame = frame;
        showLoginView();
    }

    public void showLoginView(){
        panel = new JPanel(new GridLayout(3, 1));
        panel.setLayout(null);

        browseUsersButton = new JButton("Browse Users");
        browseUsersButton.setBounds(135, 80, 200, 35);
        panel.add(browseUsersButton);

        browseWatchlistsButton = new JButton("Browse Watchlists");
        browseWatchlistsButton.setBounds(135, 130, 200, 35);
        panel.add(browseWatchlistsButton);

        browseVideosButton = new JButton("Browse Videos");
        browseVideosButton.setBounds(135, 180, 200, 35);
        panel.add(browseVideosButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(135, 230, 200, 35);
        panel.add(logoutButton);

        frame.setNewPanel(panel);
    }

    public void addBrowseUsersActionListener(ActionListener actionListener){
        browseUsersButton.addActionListener(actionListener);
    }

    public void addBrowseWatchlistsActionListener(ActionListener actionListener){
        browseWatchlistsButton.addActionListener(actionListener);
    }

    public void addBrowseVideosActionListener(ActionListener actionListener){
        browseVideosButton.addActionListener(actionListener);
    }

    public void addLogoutActionListener(ActionListener actionListener){
        logoutButton.addActionListener(actionListener);
    }

}
