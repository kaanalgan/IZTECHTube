package iztechtube.usersession;

import iztechtube.updatehandler.UserDataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginController {

    private final LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        loginView.addLoginActionListener(new LoginActionListener());
    }

    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            checkCredentials(loginView.getUsername(), loginView.getPassword());
        }
    }

    private void checkCredentials(String username, char[] password) {

        UserDataHandler userHandler = new UserDataHandler();
        User user = userHandler.get(username);
        if(user != null && user.getPassword().equals(String.valueOf(password))){
            SessionManager session = new SessionManager(user,loginView.getFrame());
            session.openMainMenu();
        }
        else{
            boolean usernameNotFound = (user == null);
            if(usernameNotFound){
                loginView.securityCheckFailed(true, false);
            }else{
                loginView.securityCheckFailed(false, true);
            }


        }
    }



}
