package iztechtube.userbrowser;

import iztechtube.usersession.SessionManager;
import iztechtube.usersession.User;
import iztechtube.updatehandler.UserDataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseUsersController {

    private final BrowseUsersView browseUsersView;
    private final SessionManager session;
    private final UserDataHandler userHandler;

    public BrowseUsersController(BrowseUsersView browseUsersView, SessionManager session){
        this.userHandler =  new UserDataHandler();
        this.browseUsersView = browseUsersView;
        this.session = session;

        session.getUser().addObserver(userHandler);
        session.getUser().addObserver(browseUsersView);

        //Connect action listeners to the view.
        browseUsersView.addFollowActionListener(new FollowActionListener());
        browseUsersView.addUnfollowActionListener(new UnfollowActionListener());
        browseUsersView.addMainMenuActionListener(new MainMenuActionListener());
    }

    //Event performed whenever the user selects a user and clicks on 'Follow User' button.
    private class FollowActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedUser = browseUsersView.getSelectedUsername();
            if(selectedUser != null){
                User targetUser = userHandler.get(selectedUser);
                session.getUser().follow(targetUser.getUsername());
                targetUser.addFollower(session.getUser().getUsername());
            }
        }
    }

    //Event performed whenever the user selects a user and clicks on 'Unfollow User' button
    private class UnfollowActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedUser = browseUsersView.getSelectedUsername();
            if(selectedUser != null) {
                User targetUser = userHandler.get(browseUsersView.getSelectedUsername());
                session.getUser().unfollow(targetUser.getUsername());
                targetUser.removeFollower(session.getUser().getUsername());
            }
        }
    }

    //Event performed whenver the user clicks on the 'Main Menu' button
    private class MainMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            session.openMainMenu();
        }
    }
}
