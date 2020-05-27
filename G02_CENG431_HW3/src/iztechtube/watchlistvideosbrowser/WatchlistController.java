package iztechtube.watchlistvideosbrowser;

import iztechtube.exceptions.NoSelectedVideoException;
import iztechtube.exceptions.UnauthorizedUserException;
import iztechtube.usersession.SessionManager;
import iztechtube.updatehandler.VideoDataHandler;
import iztechtube.video.Video;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class WatchlistController {

    private final WatchlistView watchlistView;
    private final Watchlist watchlist;
    private final VideoDataHandler videoHandler;
    private SessionManager session;

    public WatchlistController(WatchlistView watchlistView, Watchlist watchlist, SessionManager session){
        this.watchlistView = watchlistView;
        this.watchlist = watchlist;
        this.session = session;
        this.videoHandler = new VideoDataHandler();

        watchlistView.setVideoNameMap(getVideoNameMap());

        watchlist.addObserver(watchlistView);
        watchlistView.addGoToVideoActionListener(new goToVideoActionListener());
        watchlistView.addBackActionListener(new backActionListener());
        watchlistView.addDeleteVideoActionListener(new deleteVideoActionListener());
    }

    //Event performed whenever the user selects a video and clicks on 'Delete video' button
    private class deleteVideoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(session.getUser().getWatchlists().contains(watchlist)) {
                int videoIndex = watchlistView.getSelectedListIndex();
                if(videoIndex < 0){
                    try {
                        throw new NoSelectedVideoException("You have to select a video first!");
                    } catch (NoSelectedVideoException noSelectedVideoException) {
                        noSelectedVideoException.printStackTrace();
                        watchlistView.displayMessage("You have to select a video first");
                    }
                }else{
                    int videoId = watchlist.getVideos().get(videoIndex);
                    watchlist.remove(videoId);
                }
            }else{
                //Not your watchlist! can't remove
                try {
                    throw new UnauthorizedUserException("You are not authorized to remove a video from that watchlist!");
                } catch (UnauthorizedUserException unauthorizedUserException) {
                    unauthorizedUserException.printStackTrace();
                    watchlistView.displayMessage("You are not authorized to remove a video from that watchlist!");
                }
            }
        }
    }

    //Event performed whenever the user selects a video and clicks on 'Watch' button
    private class goToVideoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = watchlistView.getSelectedListIndex();
            if(i<0 || i>= watchlist.getVideos().size()){
                try {
                    throw new NoSelectedVideoException("You have to select a video first!");
                } catch (NoSelectedVideoException noSelectedVideoException) {
                    noSelectedVideoException.printStackTrace();
                    watchlistView.displayMessage("You have to select a video to watch!");
                }
            }else{
                Integer videoId = watchlist.getVideos().get(i);
                VideoDataHandler videoHandler = new VideoDataHandler();
                Video selectedVideo = videoHandler.get(videoId);
                session.openVideo(selectedVideo, watchlist);
            }
        }
    }

    private class backActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            session.openBrowseWatchlists();
        }
    }

    private Map<Integer,String> getVideoNameMap(){
        Map<Integer,String> videoNameMap = new HashMap<>();
        for(Video video: videoHandler.getDataMap().values()){
            videoNameMap.put(video.getId(), video.getTitle());
        }
        return videoNameMap;
    }
}
