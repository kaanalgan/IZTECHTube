package iztechtube.videobrowser;

import iztechtube.usersession.SessionManager;
import iztechtube.updatehandler.VideoDataHandler;
import iztechtube.video.Video;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BrowseVideosController {

    SessionManager session;
    BrowseVideosView browseVideosView;
    VideoDataHandler videoHandler;
    List<Video> videoList;

    public BrowseVideosController(BrowseVideosView browseVideosView, SessionManager session) {
        this.session = session;
        this.browseVideosView = browseVideosView;
        this.videoHandler = new VideoDataHandler();
        this.videoList = new ArrayList<>(videoHandler.getDataMap().values());

        Map<Integer, Video> videoMap = videoHandler.getDataMap();
        browseVideosView.setVideolist(new ArrayList<>(videoMap.values()));
        browseVideosView.addOpenVideoActionListener(new OpenVideoActionListener());
        browseVideosView.addMainMenuActionListener(new MainMenuActionListener());
    }

    private class OpenVideoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedVideoIndex = browseVideosView.getSelectedListIndex();
            if(selectedVideoIndex < 0){
                browseVideosView.displayMessage("Select a video first!");
            }else{
                session.openVideo(videoList.get(selectedVideoIndex), null);
            }
        }
    }

    private class MainMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            session.openMainMenu();
        }
    }

}
