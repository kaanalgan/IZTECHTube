package iztechtube.watchlistbrowser;

import iztechtube.FrameManager;
import iztechtube.usersession.User;
import iztechtube.watchlistvideosbrowser.Watchlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class BrowseWatchlistView implements Observer {
    private JPanel panel;
    private JButton mainMenuButton;
    private JButton backButton;
    private JButton createWatchlistButton;
    private JButton openWatchlistButton;
    private JScrollPane scrollPane;
    private JList<String> jWatchlists;
    private FrameManager frame;
    private User user;
    private List<String> followingsWatchlists;

    public BrowseWatchlistView(FrameManager frame, User user) {
        this.frame = frame;
        this.user = user;
        this.followingsWatchlists = new ArrayList<>();

        panel = new JPanel(new GridLayout(3, 1));
        panel.setLayout(null);

        JLabel title = new JLabel("Watchlists");
        title.setBounds(10, 10, 80, 25);
        panel.add(title);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 40, 420, 500);
        panel.add(scrollPane);

        jWatchlists = new JList<>();
        scrollPane.setViewportView(jWatchlists);
        setWatchlists();

        openWatchlistButton = new JButton("Open Watchlist");
        openWatchlistButton.setBounds(10, 560, 130, 35);
        panel.add(openWatchlistButton);

        createWatchlistButton = new JButton("Create Watchlist");
        createWatchlistButton.setBounds(160, 560, 130, 35);
        panel.add(createWatchlistButton);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBounds(310, 560, 120, 35);
        panel.add(mainMenuButton);

        frame.setNewPanel(panel);
    }

    private void setWatchlists() {
        List<String> allWatchlists = new ArrayList<>();
        for(Watchlist wl: user.getWatchlists()){
            allWatchlists.add("<html><body>" + wl.getName() + " -my" + "<br>" + " " + "<br>" + "</span></body></html>}");
        }
        for(String wl: followingsWatchlists){
            allWatchlists.add("<html><body>" + wl + "<br>" + " " + "<br>" + "</span></body></html>}");
        }
        String[] watchlistArr = {};
        jWatchlists.setListData(allWatchlists.toArray(watchlistArr));
    }

    //Get user input from a popup
    public String getUserInput(String message){
        return JOptionPane.showInputDialog(message);
    }

    public void displayMessage(String message){ JOptionPane.showMessageDialog(frame.getFrame(), message); }

    public void addOpenWatchlistButton(ActionListener actionListener) {
        openWatchlistButton.addActionListener(actionListener);
    }

    public void addCreateWatchlistButton(ActionListener actionListener) {
        createWatchlistButton.addActionListener(actionListener);
    }

    public void addMainMenuActionListener(ActionListener actionListener) {
        mainMenuButton.addActionListener(actionListener);
    }

    public int getSelectedListIndex(){
        return jWatchlists.getSelectedIndex();
    }

    public void setFollowingsWatchlists(List<String> watchlists){
        this.followingsWatchlists = watchlists;
        setWatchlists();
    }

    @Override
    public void update(Observable o, Object arg) {
        user = (User) o;
        setWatchlists();
    }
}
