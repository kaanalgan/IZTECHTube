package iztechtube.mainmenu;

import iztechtube.usersession.SessionManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController {
    private final MenuView menuView;
    private final SessionManager session;

    public MenuController(MenuView menuView, SessionManager session) {
        this.menuView = menuView;
        this.session = session;

        menuView.addBrowseUsersActionListener(new BrowseUsersActionListener());
        menuView.addBrowseWatchlistsActionListener(new BrowseWatchlistsActionListener());
        menuView.addBrowseVideosActionListener(new BrowseVideosActionListener());
        menuView.addLogoutActionListener(new LogoutActionListener());
    }

    private class BrowseUsersActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            session.openBrowseUsers();
        }
    }

    private class BrowseWatchlistsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            session.openBrowseWatchlists();
        }
    }

    private class BrowseVideosActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            session.openBrowseAllVideos();
        }
    }

    private class LogoutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            session.logout();
        }
    }
}
