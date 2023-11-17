package UILayer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BusinessLayer.*;


public class LoginUI extends JFrame {
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    Admin adminInstance; 

    public LoginUI() {
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameTextField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        add(usernameLabel);
        add(usernameTextField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); //empty label for spacing
        add(loginButton);
    }

    private void performLogin() {
            String enteredUsername = usernameTextField.getText();
            char[] enteredPasswordChars = passwordField.getPassword();
            String enteredPassword = new String(enteredPasswordChars);

           
            adminInstance= new Admin(1, enteredUsername, enteredPassword);
            Manager managerInstance = new Manager(enteredUsername, enteredPassword);
            SalesAssistant salesAssistantInstance = new SalesAssistant(enteredUsername, enteredPassword);


            if (adminInstance.authenticateFromDB(enteredUsername, enteredPassword)) {
                openAdminUI();
            } 
            else  if (managerInstance.authenticateFromDB(enteredUsername, enteredPassword)){
                openManagerUI();
            }
            else  if (salesAssistantInstance.authenticateFromDB(enteredUsername, enteredPassword)){
                openSalesAssistantUI();
            }

            else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }

    }
    public Admin getLoggedInAdmin() {
        return adminInstance;
    }

    private void openAdminUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminUI().setVisible(true);
            }
        });
    }
    private void openManagerUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManagerDashboard().setVisible(true);
            }
        });
    }
    private void openSalesAssistantUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SalesAssistantUI().setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginUI().setVisible(true);
            }
        });
    }
}
