package iztechtube.watchlistvideosbrowser;

import iztechtube.FrameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

public class WatchlistView implements Observer {

    private Watchlist watchlist;
    private Map<Integer, String> videoNameMap;
    private JButton watchVideoButton, backButton, removeVideoButton;
    private FrameManager frame;
    private JScrollPane scrollPane;
    private JList<String> jVideosList;
    private JPanel panel;

    public WatchlistView(FrameManager frame, Watchlist watchlist) {
        this.frame = frame;
        this.watchlist = watchlist;
        this.videoNameMap = null;

        panel = new JPanel(new GridLayout(3, 1));
        panel.setLayout(null);

        JLabel videoTitle = new JLabel("Watchlist: " + watchlist.getName());
        videoTitle.setBounds(10, 10, 250, 25);
        panel.add(videoTitle);

        jVideosList = new JList<String>();
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 40, 420, 500);
        panel.add(scrollPane);

        watchVideoButton = new JButton("Watch");
        watchVideoButton.setBounds(10, 560, 130, 35);
        panel.add(watchVideoButton);

        removeVideoButton = new JButton("Delete video");
        removeVideoButton.setBounds(160, 560, 130, 35);
        panel.add(removeVideoButton);

        backButton = new JButton("Back");
        backButton.setBounds(310, 560, 120, 35);
        panel.add(backButton);

        frame.setNewPanel(panel);
    }

    public void showWatchlist(){
        String[] videoListArr = new String[watchlist.getVideos().size()];
        int i = 0;
        for(Integer id: watchlist.getVideos()){
            videoListArr[i] = "<html><body>id: " + id + " title: " + videoNameMap.get(id) + "<br>" + " <br></span></body></html>}";
            i++;
        }
        jVideosList = new JList<>(videoListArr);
        scrollPane.setViewportView(jVideosList);
    }

    public void displayMessage(String msg){ JOptionPane.showMessageDialog(frame.getFrame(), msg); }

    public void setVideoNameMap(Map<Integer, String> videoNameMap){
        this.videoNameMap = videoNameMap;
        showWatchlist();
    }

    @Override
    //Whenever the watchlist changes, update the view
    public void update(Observable o, Object arg) {
        this.watchlist = (Watchlist) o;
        showWatchlist();
    }

    public void addDeleteVideoActionListener(ActionListener actionListener) {
        removeVideoButton.addActionListener(actionListener);
    }

    public void addGoToVideoActionListener(ActionListener actionListener) {
        watchVideoButton.addActionListener(actionListener);
    }

    public void addBackActionListener(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    public int getSelectedListIndex(){
        return jVideosList.getSelectedIndex();
    }

}
