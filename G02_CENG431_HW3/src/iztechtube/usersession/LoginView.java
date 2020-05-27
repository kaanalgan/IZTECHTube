package iztechtube.usersession;

import iztechtube.FrameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView {

    private JLabel userLabel;
    private JTextField usernameText;
    private JLabel passwordLabel;
    private JPasswordField passwordText;
    private JButton loginButton;
    private JPanel panel;
    private FrameManager frame;

    public LoginView(FrameManager frame) {
        this.frame = frame;
        showLoginView();
    }

    public void showLoginView(){
        panel = new JPanel(new GridLayout(3, 1));
        panel.setLayout(null);

        userLabel = new JLabel("User");
        userLabel.setBounds(80, 250, 80, 25);
        panel.add(userLabel);

        usernameText = new JTextField(20);
        usernameText.setBounds(170, 250, 160, 25);
        panel.add(usernameText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(80, 280, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(170, 280, 160, 25);
        panel.add(passwordText);

        loginButton = new JButton("Login");
        loginButton.setBounds(170, 330, 160, 25);
        panel.add(loginButton);

        frame.setNewPanel(panel);
    }

    public void addLoginActionListener(ActionListener actionListener){
        loginButton.addActionListener(actionListener);
    }

    public String getUsername(){
        return usernameText.getText();
    }

    public char[] getPassword(){
        return passwordText.getPassword();
    }

    public FrameManager getFrame() {
        return frame;
    }

    public void displayMessage(String message){ JOptionPane.showMessageDialog(frame.getFrame(), message); }

    public void securityCheckFailed(boolean emailFailed, boolean passwordFailed){
        StringBuilder failMessage = new StringBuilder();
        if(emailFailed){
            failMessage.append("Username not found!\n");
            usernameText.setBackground(Color.RED);
        }
        else if(passwordFailed){
            failMessage.append("Password is incorrect!");
            passwordText.setBackground(Color.RED);
        }
        displayMessage(failMessage.toString());
    }
}
