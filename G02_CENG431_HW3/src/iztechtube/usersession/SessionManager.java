package iztechtube.usersession;

import iztechtube.FrameManager;
import iztechtube.mainmenu.MenuController;
import iztechtube.mainmenu.MenuView;
import iztechtube.updatehandler.UserDataHandler;
import iztechtube.userbrowser.BrowseUsersController;
import iztechtube.userbrowser.BrowseUsersView;
import iztechtube.videobrowser.BrowseVideosController;
import iztechtube.video.Video;
import iztechtube.video.VideoController;
import iztechtube.video.VideoView;
import iztechtube.videobrowser.BrowseVideosView;
import iztechtube.watchlistbrowser.BrowseWatchlistController;
import iztechtube.watchlistbrowser.BrowseWatchlistView;
import iztechtube.watchlistvideosbrowser.Watchlist;
import iztechtube.watchlistvideosbrowser.WatchlistController;
import iztechtube.watchlistvideosbrowser.WatchlistView;

public class SessionManager {
    private User user;
    private FrameManager frame;

    public SessionManager(User currentUser, FrameManager frame) {
        this.user = currentUser;
        this.frame = frame;
    }

    public User getUser() {
        return user;
    }

    public void openMainMenu(){
        MenuView menuView = new MenuView(frame);
        MenuController menuController = new MenuController(menuView, this);
    }

    public void openBrowseUsers(){
        UserDataHandler dataHandler = new UserDataHandler();
        BrowseUsersView browseUsersView = new BrowseUsersView(frame, dataHandler.getUsernames(), user);
        BrowseUsersController browseUsersController = new BrowseUsersController(browseUsersView, this);
    }

    public void openBrowseWatchlists(){
        BrowseWatchlistView browseWatchlistView = new BrowseWatchlistView(frame, user);
        BrowseWatchlistController browseWatchlistController = new BrowseWatchlistController(browseWatchlistView, this);
    }

    public void openBrowseAllVideos(){
        BrowseVideosView browseVideosView = new BrowseVideosView(frame);
        BrowseVideosController browseVideosController = new BrowseVideosController(browseVideosView, this);
    }

    public void openWatchlist(Watchlist watchlist){
        WatchlistView watchlistView = new WatchlistView(frame, watchlist);
        WatchlistController watchlistController = new WatchlistController(watchlistView, watchlist, this);
    }

    public void openVideo(Video video, Watchlist watchlist){
        VideoView videoView = new VideoView(frame, video);
        VideoController videoController = new VideoController(video, videoView, watchlist, this);
    }

    public void logout(){
        LoginView loginView = new LoginView(frame);
        LoginController loginController = new LoginController(loginView);
    }

}
