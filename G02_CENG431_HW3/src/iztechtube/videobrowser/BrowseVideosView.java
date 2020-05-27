package iztechtube.videobrowser;

import iztechtube.FrameManager;
import iztechtube.video.Video;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class BrowseVideosView {

    private final FrameManager frame;
    private final JPanel panel;
    private final JScrollPane scrollPane;
    private final JList<String> videoJList;
    private final JButton openVideoButton;
    private final JButton mainMenuButton;

    public BrowseVideosView(FrameManager frame) {

        this.frame = frame;

        panel = new JPanel(new GridLayout(3, 1));
        panel.setLayout(null);

        JLabel title = new JLabel("All videos");
        title.setBounds(10, 10, 250, 25);
        panel.add(title);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 40, 420, 500);
        panel.add(scrollPane);

        videoJList = new JList<>();
        scrollPane.setViewportView(videoJList);

        openVideoButton = new JButton("Open Video");
        openVideoButton.setBounds(10, 560, 130, 35);
        panel.add(openVideoButton);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBounds(310, 560, 120, 35);
        panel.add(mainMenuButton);

        frame.setNewPanel(panel);
    }


    public void displayMessage(String message) { JOptionPane.showMessageDialog(frame.getFrame(), message); }

    public int getSelectedListIndex(){
        return videoJList.getSelectedIndex();
    }

    public void addOpenVideoActionListener(ActionListener actionListener){
        openVideoButton.addActionListener(actionListener);
    }

    public void addMainMenuActionListener(ActionListener actionListener) {
        mainMenuButton.addActionListener(actionListener);
    }

    public void setVideolist(List<Video> videolist) {
        String[] videoArr = new String[videolist.size()];
        int i = 0;
        for(Video v: videolist){
            videoArr[i] = "<html><body>" + v.getTitle() + "<br>" + " " + "<br>" + "</span></body></html>}";
            i++;
        }
        videoJList.setListData(videoArr);
    }
}
