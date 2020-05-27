package iztechtube.watchlistbrowser;

import iztechtube.usersession.SessionManager;
import iztechtube.updatehandler.UserDataHandler;
import iztechtube.watchlistvideosbrowser.Watchlist;
import iztechtube.exceptions.WatchlistAlreadyPresentException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BrowseWatchlistController {
    private final SessionManager session;
    private final BrowseWatchlistView browseWatchlistView;
    private List<Watchlist> allWatchlists;
    private final UserDataHandler dataHandler;

    public BrowseWatchlistController(BrowseWatchlistView browseWatchlistView, SessionManager session) {
        this.session = session;
        this.browseWatchlistView = browseWatchlistView;
        this.dataHandler = new UserDataHandler();
        this.allWatchlists = getAllWatchlists();

        session.getUser().addObserver(browseWatchlistView);
        session.getUser().addObserver(dataHandler);

        browseWatchlistView.setFollowingsWatchlists(getFollowingsWatchlistItems());

        browseWatchlistView.addCreateWatchlistButton(new CreateWatchlistActionListener());
        browseWatchlistView.addOpenWatchlistButton(new OpenWatchlistActionListener());
        browseWatchlistView.addMainMenuActionListener(new MainMenuActionListener());
    }

    private class CreateWatchlistActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try{
                String title = browseWatchlistView.getUserInput("Watch List title:");
                session.getUser().addWatchlist(new Watchlist(new ArrayList<>(),title));
                allWatchlists = getAllWatchlists();
            }catch (WatchlistAlreadyPresentException e){
                browseWatchlistView.displayMessage("A watchlist with the same title already exists.");
            }catch (IllegalArgumentException ignored) { } //Watchlist name left blank or creation cancelled
        }
    }

    private class OpenWatchlistActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = browseWatchlistView.getSelectedListIndex();
            if(i<0 || i>= allWatchlists.size()){
                return;
            }
            Watchlist watchlist = allWatchlists.get(i);
            session.openWatchlist(watchlist);
        }
    }

    private class MainMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            session.openMainMenu();
        }
    }

    private List<Watchlist> getAllWatchlists() {
        List<Watchlist> allWatchlists = new ArrayList<>();
        allWatchlists.addAll(session.getUser().getWatchlists());
        List<Watchlist> followingsWatchlists = getFollowingsWatchlists();
        allWatchlists.addAll(followingsWatchlists);
        return allWatchlists;
    }

    private List<Watchlist> getFollowingsWatchlists(){
        List<Watchlist> followingsWatchlists = new ArrayList<>();
        for(String username: session.getUser().getFollowing()){
            followingsWatchlists.addAll(dataHandler.get(username).getWatchlists());
        }
        return followingsWatchlists;
    }

    private List<String> getFollowingsWatchlistItems(){
        List<String> followingsWatchlists = new ArrayList<>();
        for(String username: session.getUser().getFollowing()){
           for(Watchlist watchlist : dataHandler.get(username).getWatchlists()){
               followingsWatchlists.add(watchlist.getName() + " -" + username);
           }
        }
        return followingsWatchlists;
    }
}
